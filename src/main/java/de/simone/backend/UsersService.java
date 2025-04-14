package de.simone.backend;

import org.apache.commons.lang3.StringUtils;

import de.simone.SecurityUtils;
import jakarta.annotation.security.PermitAll;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@Path("/Users")
@ApplicationScoped
public class UsersService extends TAService<User> {

    @Inject
    MailingsService mailingsService;

    public UsersService() {
        super(User.class);
    }

    @Override
    @PermitAll
    public User get(@PathParam(value = "id") Long id) throws WebApplicationException {
        User user = getImpl(id);
        // Assign a user role. the idee is: all access to this method are relatet to
        // managment. that is new/edit actions.
        // if (user.isNewEntity()) {
        //     Account account = SecurityUtils.getAccount();
        //     user.addAccount(account.id);
        //     user.role = Role.find("roleName = ?1 AND ownerId =?2", Role.USER, account.id).singleResult();
        //     user.addRole(user.role);
        // }
        return user;
    }

    @Override
    public Response delete(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Transactional
    public Response delete(Long id, Long fromAccount) {
        return deleteImpl(id);
    }

    @Override
    @Transactional
    public Response save(User entity) {
        return save(entity, true);
    }

    @Transactional
    public Response save(User entity, boolean withLog) {
        Response response;

        // check userName/email uniquess
        if (!isUserUnique(entity.userName, entity.email, entity.id)) {
            return getBadRequestResponse("Response.user.exist", "userName", entity.userName,
                    "email", entity.email);

        }

        // edit
        if (!entity.isNewEntity()) {
            // if the user is free from MFA, remove posible pendin task
            if (entity.isMfaExempt) {
                entity.removeAction(User.CONFIGURE_OTP);
                entity.removeAction(User.VERIFY_OTP);
            }
            // email is confirmed
            if (entity.isEmailVerified) {
                entity.removeAction(User.VERIFY_EMAIL);
            }

            // if the user is enabled, reset the fail counter
            if (entity.isEnabled) {
                entity.failedAttemptsCount = 0;
            }

            if (withLog)
                AuditLog.logUpdate(entity);

            User.getEntityManager().merge(entity);
            return getUpdatedResponse(entity.id);
        }

        // new
        // if no logged user, the user creation is from createUser in goodmfa
        User loggedUser = SecurityUtils.getLoggedUser();
        if (loggedUser != null) {
            // TODO: 240425 test: is really the owner be set for user??
            // entity.setOwner(loggedUser.account);

            // if someone create a new user in his/her organization:
            // the account, is the current acount
            // entity.account = loggedUser.account;
            // entity.addAccount(loggedUser.account.id);
            // the status ist verified (assuming the person who create the user know the
            // other person O.o)
            entity.status = User.VERIFIED;
        }

        if (withLog)
            AuditLog.logInsert(entity);

        entity.persist();
        response = getCreatedResponse(entity.id);

        return response;
    }

    @Transactional
    public Response sendInvitation(User sender, String toEmail) {
        // check email is da
        if (StringUtils.isBlank(toEmail))
            return getBadRequestResponse("Response.invitation.emailBlank");

        // check if user with email = toEmail exist
        User targetUser = User.find("email = ?1", toEmail).firstResult();
        if (targetUser == null) {
            return getBadRequestResponse("Response.invitation.emailNotFound", "EMail", toEmail);
        }

        // update the invitation field with the requester
        // targetUser.invitationRequestUrl = User.getInvitationRequestUrl(sender, targetUser.id);

        GlobalProperty globalProperty = GlobalProperty.getInstance();
        Mailing mailing = globalProperty.inviteUserMail;
        Response response2 = mailingsService.sendMail(mailing, targetUser);

        // if send email is ok, ok
        return response2;
    }

    /**
     * check if the user name and email are unique in User & PlatformUser tables .
     * this method verify:
     * <li>if the user is new entity (id = null) this method return true iff there
     * is no other element id db with the same name AND email
     * <li>if the user is edit (id != null) this method return null if there is no
     * element with name, email or the only element with the same userName and email
     * is the same entity
     * 
     * @param userName - the user name
     * @param email    - the email
     * @param id       - the entitiy (User or PlatformUser) id. id=null means new
     *                 entity (previous to save)
     * @return unique or not
     */
    public static boolean isUserUnique(String userName, String email, Long id) {
        // for new entity
        if (id == null) {
            long countUserEmail = User.count("email = ?1", email);
            long countUser = User.count("userName = ?1", userName);
            return countUserEmail == 0 && countUser == 0;
        } else {
            Long countUser = User.count("id != ?1 AND (email = ?2 OR userName = ?3)", id, email, userName);
            return countUser == 0;
        }
    }

    @Transactional
    public Response setNewPassword(User user, String newPassword, boolean isTemporal) {
        // check passwort length
        GlobalProperty globalProperty = GlobalProperty.getInstance();
        if (newPassword != null && newPassword.length() < globalProperty.minPasswortLength)
            return getBadRequestResponse("Response.password.length", "minPassLength", globalProperty.minPasswortLength);

        user.setPassword(newPassword);
        user.isTemporalPass = isTemporal;
        user.removeAction(User.UPDATE_PASS);
        if (isTemporal)
            user.addAction(User.UPDATE_PASS);

            AuditLog.logResetPassword(user);
        User.getEntityManager().merge(user);
        return getUpdatedResponse(user.id);
    }

    @Override
    public Response duplicate(Long id) {
        throw new UnsupportedOperationException("user can't be duplacated");
    }

    @Transactional
    public static void setTaskCompleted(String task) {
        User user = SecurityUtils.getLoggedUser();
        user.setTaskCompleted(task);
        User.getEntityManager().persist(user);
    }

    @Transactional
    public static boolean addOnTimeTip(String tip) {
        User user = SecurityUtils.getLoggedUser();
        boolean appended = user.addOneTimeTip(tip);
        User.getEntityManager().persist(user);
        return appended;
    }

}
