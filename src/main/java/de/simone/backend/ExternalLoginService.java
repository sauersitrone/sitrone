package de.simone.backend;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import de.simone.TranslationProvider;
import de.simone.UIUtils;
import dev.samstevens.totp.code.CodeGenerator;
import dev.samstevens.totp.code.DefaultCodeGenerator;
import dev.samstevens.totp.code.DefaultCodeVerifier;
import dev.samstevens.totp.qr.QrData;
import dev.samstevens.totp.qr.QrGenerator;
import dev.samstevens.totp.qr.ZxingPngQrGenerator;
import dev.samstevens.totp.time.SystemTimeProvider;
import dev.samstevens.totp.time.TimeProvider;
import dev.samstevens.totp.util.Utils;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

@Transactional
@ApplicationScoped
public class ExternalLoginService {

    private static final int PERIOD = 30;

    @Inject
    UsersService usersService;
    @Inject
    ChatsService notificationsService;

    @Inject
    MailingsService mailingsService;

    /**
     * return AuthenticationResult with the user entity
     * 
     * @param userNameOrEmail - user name or email to find
     * @return the user
     */
    public AuthenticationResult getUser(String userNameOrEmail) {
        User user;
        // email or username
        User userMail = User.find("email = ?1", userNameOrEmail).firstResult();
        User userName = User.find("userName = ?1", userNameOrEmail).firstResult();

        user = userMail == null ? userName : userMail;
        if (user == null)
            return new AuthenticationResult(AuthenticationResult.FAILED_USER_EMAIL_DONT_EXIST);

        AuthenticationResult result = checkLockedOrDisabled(user); // NOSONAR
        if (!result.isSuccess)
            return result;

        return new AuthenticationResult(AuthenticationResult.SUCCESS, user);
    }

    public AuthenticationResult autenticate(String userNameOrEmail, String password, int uiid) {

        AuthenticationResult result = getUser(userNameOrEmail);
        if (!result.isSuccess)
            return result;

        User user = result.user;

        // if frontend send userSecret as password, the auth process come from passkey
        if (!user.verify(password) && !user.userSecret.equals(password)) {
            user.failedAttemptsCount++;
            User.getEntityManager().merge(user);
            return new AuthenticationResult(AuthenticationResult.FAILED_USER_PASS);
        }

        if (user.isTemporalPass) {
            user.addAction(User.UPDATE_PASS);
        }

        if (!user.isEmailVerified) {
            user.addAction(User.VERIFY_EMAIL);
        }

        if (!user.isMfaExempt) {
            if (user.isTotpRegistred) {
                if (user.sessionId == -1) {
                    System.out.println("ExternalLoginService.autenticate()");
                    user.sessionId = uiid;
                    user.addAction(User.VERIFY_OTP);
                }
            } else {
                user.addAction(User.CONFIGURE_OTP);
            }
        }

        // if the user dont hat a associatet accound, force creation
        // if (user.account == null) {
        //     user.addAction(User.REGISTRE_ACCOUNT);
        // }

        // final or temporal succsess
        user.failedAttemptsCount = 0;
        user.persist();

        List<String> iActions = user.getActions();
        if (iActions.isEmpty()) {
            // final success return jwt token
            user.lastSignIn = LocalDateTime.now();
            user.clearActions();
            user.sessionId = -1;
            User.getEntityManager().merge(user);
            result = new AuthenticationResult(AuthenticationResult.SUCCESS_FINAL, user);
        } else
            // dont return token
            result = new AuthenticationResult(AuthenticationResult.SUCCESS, user);

        return result;
    }

    /**
     * create a standar user in the system.
     * 
     * @param user     - partial user entity
     * @param password - plain text password
     * 
     * @return AuthenticationResult.SUCCESS with the complete user instance created
     */
    public AuthenticationResult createNewUser(User user, String password) {
        // check passwort length
        int minPassLength = GlobalProperty.getInstance().minPasswortLength;
        if (password.length() < minPassLength) {
            AuthenticationResult result = new AuthenticationResult(AuthenticationResult.FAILED_PASSWORD_POLICY);
            result.payload = TranslationProvider.getTranslation(AuthenticationResult.FAILED_PASSWORD_POLICY,
                    minPassLength);
            return result;
        }

        user.setPassword(password);
        user.status = User.INCOMPLETE;

        Response response = usersService.save(user, false);

        if (response.getStatus() >= 400)
            return new AuthenticationResult(AuthenticationResult.FAILED_USER_EMAIL_EXIST);

        // send the confirmation code email
        sendConfirmationCode(user, ResetOption.EMAIL_ACTION);

        return new AuthenticationResult(AuthenticationResult.SUCCESS, user);
    }

    /**
     * return a QR code for configure 1TP on FreeOTP or Google Authenticator. this
     * code is
     * 
     * @param user - the user
     * @return the code
     */
    public String getQRCodeForTotp(User user) {
        try {
            QrData data = new QrData.Builder().label(user.email).secret(user.userSecret)
                    .issuer(GlobalProperty.getInstance().appName).build();

            QrGenerator generator = new ZxingPngQrGenerator();
            byte[] imageData = generator.generate(data);
            String mimeType = generator.getImageMimeType();
            String dataUri = Utils.getDataUriForImage(imageData, mimeType);
            return dataUri;
        } catch (Exception e) {
            Log.error("", e);
            return null;
        }
    }

    public AuthenticationResult sendConfirmationCode(User user, String action) {
        AuthenticationResult result2 = checkLockedOrDisabled(user);
        if (!result2.isSuccess)
            return result2;

        GlobalProperty globalProperty = GlobalProperty.getInstance();
        user.setConfirmationCode(globalProperty.externalTOTPLifeSpan);

        if (action.equals(ResetOption.EMAIL_ACTION)) {
            Mailing mailing = globalProperty.confirmationCodeEmail;
            mailingsService.sendMail(mailing, user, globalProperty);
            result2 = new AuthenticationResult(AuthenticationResult.SUCCESS_CONFIRMATION_SENDED);
        }

        if (action.equals(ResetOption.PHONE_ACTION)) {
            Log.info("your Goodfunds confirmation code is " + user.confirmationCode);
            result2 = new AuthenticationResult(AuthenticationResult.SUCCESS_CONFIRMATION_SENDED);
        }
        return result2;
    }

    public AuthenticationResult sendResetCredentials(User user, String duration, Set<String> resetActions) {
        AuthenticationResult result = checkLockedOrDisabled(user);
        if (!result.isSuccess)
            return result;

        try {
            // user.setMagicLink(duration, resetActions);
            GlobalProperty globalProperty = GlobalProperty.getInstance();
            Mailing mailing = globalProperty.resetCredentialsMail;
            mailingsService.sendMail(mailing, user, globalProperty);
            return new AuthenticationResult(AuthenticationResult.SUCCESS);
        } catch (Exception e) {
            Log.error("", e);
            return new AuthenticationResult(AuthenticationResult.FAILED);
        }
    }

    public AuthenticationResult confirmIdentity(User user, String code) {
        int period = UIUtils.getSeconds(GlobalProperty.getInstance().externalTOTPLifeSpan);
        AuthenticationResult result = verifyTotpImpl(user, code, period);
        if (result.isSuccess) {
            // TODO:
        }
        return result;
    }

    /**
     * check the validity of the external code (code sended as part of email or
     * phone). if the code is valid, this method update the user field
     * {@link User#isEmailVerified}
     * 
     * @param user - the user
     * @param code - the code
     * 
     * @see ExternalLoginService#verifyTotp(Long, String, int)
     * 
     * @return {@link AuthenticationResult#SUCCESS} if the code is valid
     */
    public AuthenticationResult confirmEmail(User user, String code) {
        int period = UIUtils.getSeconds(GlobalProperty.getInstance().externalTOTPLifeSpan);
        AuthenticationResult result = verifyTotpImpl(user, code, period);
        if (result.isSuccess) {
            user.isEmailVerified = true;
            user.removeAction(User.VERIFY_EMAIL);
            User.getEntityManager().merge(user);
        }
        return result;
    }

    /**
     * central methot for totp code verification. This method only verify the
     * validity of the code
     * and update internals fiels of the user passes as argument. to remove user and
     * internal
     * accions form the auth workflow, use the removeIfSuccess parameters
     * 
     * @param userId           - user id
     * @param code             - the code to check
     * @param period           - the valid time period asociated with the code to
     *                         verify
     * @param removeIfSuccsess - array of accions to remove if the verification is
     *                         successfully
     * 
     * @return {@link AuthenticationResult#SUCCESS} if all is ok
     */
    private AuthenticationResult verifyTotpImpl(User user, String code, int period) {
        AuthenticationResult result = checkLockedOrDisabled(user);
        if (!result.isSuccess)
            return result;

        TimeProvider timeProvider = new SystemTimeProvider();
        CodeGenerator codeGenerator = new DefaultCodeGenerator();
        DefaultCodeVerifier verifier = new DefaultCodeVerifier(codeGenerator, timeProvider);
        verifier.setTimePeriod(period);
        boolean successful = verifier.isValidCode(user.userSecret, code);
        System.out.println("ExternalLoginService.verifyTotp() >>" + code + "<< " + user.userSecret + " "
                + user.userName + " " + user.email + " " + period + " = " + successful);
        return successful ? new AuthenticationResult(AuthenticationResult.SUCCESS)
                : new AuthenticationResult(AuthenticationResult.FAILED_TOTP);
    }

    public AuthenticationResult configureTotp(User user, String code) {
        AuthenticationResult result = verifyTotpImpl(user, code, PERIOD);
        if (result.isSuccess) {
            user.isTotpRegistred = true;
            user.removeAction(User.CONFIGURE_OTP);
            User.getEntityManager().merge(user);
        }
        return result;
    }

    public AuthenticationResult verifyTotp(User user, String code) {
        AuthenticationResult result = verifyTotpImpl(user, code, PERIOD);
        if (result.isSuccess) {
            user.removeAction(User.VERIFY_OTP);
            User.getEntityManager().merge(user);
        }
        return result;
    }

    public AuthenticationResult getResetOptions(User user) {
        AuthenticationResult result = checkLockedOrDisabled(user);
        if (!result.isSuccess)
            return result;

        List<ResetOption> list = new ArrayList<>();

        if (!(user.email == null || user.email.isBlank()))
            list.add(new ResetOption(ResetOption.EMAIL_ACTION, user.email, "E-mail",
                    "Bestätigungscode an " + user.email + " senden."));

        if (!(user.phone == null || user.phone.isBlank()))
            list.add(new ResetOption(ResetOption.PHONE_ACTION, user.phone, "Phone",
                    "Bestätigungscode an " + user.phone + " senden."));

        return new AuthenticationResult(AuthenticationResult.SUCCESS_RESET_OPTIONS, null, list);
    }

    public AuthenticationResult checkLockedOrDisabled(User user) {
        if (user == null)
            return new AuthenticationResult(AuthenticationResult.FAILED);

        if (user.failedAttemptsCount >= GlobalProperty.getInstance().maxFailAttempts) {
            user.isEnabled = false;
            User.getEntityManager().merge(user);
            return new AuthenticationResult(AuthenticationResult.FAILED_ACCOUNT_LOCKED);
        }

        if (!user.isEnabled)
            return new AuthenticationResult(AuthenticationResult.FAILED_ACCOUNT_LOCKED);

        // all pass
        return new AuthenticationResult(AuthenticationResult.SUCCESS);
    }
}
