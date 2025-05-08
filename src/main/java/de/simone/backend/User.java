
package de.simone.backend;

import java.time.*;
import java.util.*;

import com.fasterxml.jackson.annotation.*;
import com.opencsv.bean.*;

import dev.samstevens.totp.code.*;
import dev.samstevens.totp.secret.*;
import dev.samstevens.totp.time.*;
import io.quarkus.elytron.security.common.*;
import io.quarkus.logging.*;
import io.quarkus.security.jpa.*;
import jakarta.enterprise.context.control.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import net.andreinc.jbvext.annotations.misc.*;

@Entity
@UserDefinition
@Table(name = "Users", indexes = {
        @Index(name = "User_userName", columnList = "userName") })
@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends TAEntity implements UserDomain {

    public static final String APP_MASTER_ROLE = "Sitrone.master";
    public static final String ANONYMOUS = "User.anonymous";

    public static final String ADMIN_USER_NAME = "Sitrone";
    public static final Long ADMIN_USER_ID = 130L;

    public static final String UNVERIFIED = "UNVERIFIED";
    public static final String INCOMPLETE = "INCOMPLETE";
    public static final String VERIFIED = "VERIFIED";

    public static final String CARER = "CARER";
    public static final String RELATIVE = "RELATIVE";

    public static final String VERIFY_EMAIL = "VERIFY_EMAIL";
    public static final String VERIFY_OTP = "VERIFY_OTP";
    public static final String CONFIGURE_OTP = "CONFIGURE_OTP";
    public static final String UPDATE_PASS = "UPDATE_PASS";

    // the same values as Lumo class
    public static final String LIGHT = "light";
    public static final String DARK = "dark";

    @OrderColumn
    @ElementCollection(fetch = FetchType.EAGER)
    public List<String> adults = new ArrayList<>();

    @NotNull
    public Long currentAdultId;

    @OneToOne
    public Role role;

    @ManyToMany(fetch = FetchType.EAGER) // no cascade, and can be empty at some point
    public Set<Role> associatedRoles = new HashSet<>();

    @NotNull
    @OneToOne
    public MessagingProvider messagingProvider;

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = SIZE_NAMES)
    public String firstName;

    @NotEmpty
    @Size(max = SIZE_NAMES)
    public String lastName;

    @Size(max = SIZE_255)
    public String avatar;

    @Email
    @NotBlank
    @NotEmpty
    @Size(max = SIZE_DESCRIPTIONS)
    public String email;

    @NotBlank
    @NotEmpty
    @Size(max = SIZE_NAMES)
    public String phone;

    @Username
    @NotBlank
    @NotEmpty
    @Size(max = SIZE_NAMES)
    public String userName;

    @NotBlank
    @NotEmpty
    @Size(max = SIZE_DESCRIPTIONS)
    private String password;

    @NotNull
    @OneOfStrings({ EN, DE })
    public String preferredLanguage = DE;

    @NotNull
    @OneOfStrings({ DARK, LIGHT })
    public String preferredTheme = LIGHT;

    @NotBlank
    @NotEmpty
    @Size(max = SIZE_DESCRIPTIONS)
    public String userSecret;

    @NotNull
    @OneOfStrings({ UNVERIFIED, VERIFIED, INCOMPLETE })
    public String status = INCOMPLETE;

    @NotNull
    @OneOfStrings({ CARER, RELATIVE })
    public String type = CARER;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<HanniTask> tasks = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> internalActions = new ArrayList<>();

    @NotNull
    @CsvBindByName
    @OneOfStrings({ Address.WITHOUT, Address.DAME, Address.MISTER })
    public String salutation = Address.WITHOUT;

    @NotBlank
    @NotEmpty
    @CsvBindByName
    @Size(max = SIZE_DESCRIPTIONS)
    public String street;

    @NotBlank
    @NotEmpty
    @CsvBindByName
    @Size(max = 5)
    public String postcode;

    @NotBlank
    @NotEmpty
    @CsvBindByName
    @Size(max = SIZE_NAMES)
    public String city;

    @NotNull
    @NotBlank
    @CsvBindByName
    @Size(max = SIZE_2)
    public String country = "DE";

    @Transient
    public String confirmationCode;

    @Transient
    public String magicLink;

    @Transient
    public String magicLinkDuration;

    public String invitationRequestUrl;
    public boolean isEnabled;
    public boolean isTemporalPass;
    public boolean isEmailVerified;
    public boolean isMfaExempt;
    public boolean isTotpRegistred;
    public boolean isLive;
    public LocalDateTime lastSignIn;
    public int sessionId;
    public int failedAttemptsCount;
    public int rowsPerPage = ListDataRequest.DEFAULT_LIMIT;

    public static User getGoodFundUser() {
        return User.find("userName = ?1", ADMIN_USER_NAME).singleResult();
    }

    public User() {
        // assign a secret to the user
        SecretGenerator secretG = new DefaultSecretGenerator();
        userSecret = secretG.generate();

        // initialy the user is only protected with user/pass.
        isMfaExempt = true;
        isEnabled = true;

        // build the roles field with the the list of end points
    }

    /**
     * Return the list of end point associated with the role assigned to the user.
     * if the user hast no role, {@link #ANONYMOUS} is assigned. is the
     * responsability of the external aplication to give anonimous role suport.
     * 
     * @return the list
     */
    @Roles
    @ActivateRequestContext
    public List<String> getEndPoints() {
        if (isAdmin())
            return Arrays.asList(User.APP_MASTER_ROLE);

        if (role == null)
            return Arrays.asList(User.ANONYMOUS);

        List<String> roles = new ArrayList<>();
        List<EndPoint> endPoints = EndPoint.listAll();
        for (EndPoint endPoint : endPoints) {
            if (Role.SELECTED.equals(role.selectionMethod) && role.endPoints.contains(endPoint.target))
                roles.add(endPoint.target);
            if (Role.NOT_SELECTED.equals(role.selectionMethod) && !role.endPoints.contains(endPoint.target))
                roles.add(endPoint.target);
        }
        return roles;
    }

    public void addRole(Role role) {
        if (!associatedRoles.contains(role))
            associatedRoles.add(role);
    }

    /**
     * Qute interface
     * 
     * @return - the mime/image
     */
    @JsonIgnore
    public String getEmbeddedAvatar() {
        return super.getImage(avatar);
    }

    public boolean isAdmin() {
        return ADMIN_USER_NAME.equals(userName);
    }

    @Password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = BcryptUtil.bcryptHash(password, 4);
    }

    public boolean verify(String password) {
        return BcryptUtil.matches(password, this.password);
    }

    public void removeAction(String userAction) {
        internalActions.remove(userAction);
    }

    public boolean containAction(String userAction) {
        return internalActions.contains(userAction);
    }

    public void clearActions() {
        internalActions.clear();
    }

    public String getFullName() {
        return Address.getFullName(firstName, lastName);
    }

    public List<String> getActions() {
        return internalActions;
    }

    public void addAction(String userAction) {
        if (!internalActions.contains(userAction)) {
            internalActions.add(userAction);
        }
    }

    /**
     * set the {@link #confirmationCode} parameter for this instance
     * 
     * @param duration - the duration of the code
     */
    public void setConfirmationCode(String duration) {
        try {
            Duration d = Duration.parse(duration);
            int period = (int) d.getSeconds();
            TimeProvider timeProvider = new SystemTimeProvider();
            CodeGenerator codeGenerator = new DefaultCodeGenerator();
            this.confirmationCode = codeGenerator.generate(userSecret, timeProvider.getTime() / period);
            // DefaultCodeVerifier verifier = new DefaultCodeVerifier(codeGenerator,
            // timeProvider);
            // verifier.setTimePeriod(period);
            // boolean successful = verifier.isValidCode(totpSecret + user.userName, code);
            // System.out.println("SecurityExternal.getConfirmationCode() " + code + " " +
            // successful);
        } catch (Exception e) {
            Log.error("", e);
        }
    }

    public List<HanniTask> getTasks() {
        return tasks;
    }

    public void cleanTasks() {
        tasks.clear();
    }

    public boolean isTaskCompleted(String task) {
        Optional<HanniTask> optional = tasks.stream().filter(t -> t.task.equals(task)).findFirst();
        if (optional.isEmpty())
            return true;
        return optional.get().completed;
    }

    public void setTaskCompleted(String task) {
        Optional<HanniTask> optional = tasks.stream().filter(t -> t.task.equals(task)).findFirst();
        if (optional.isEmpty())
            return;

        HanniTask progress = optional.get();
        progress.completed = true;
    }

    public boolean addOneTimeTip(String tip) {
        boolean isPresent = tasks.stream().anyMatch(e -> e.task.equals(tip));
        if (isPresent)
            return false;

        HanniTask tip2 = new HanniTask();
        tip2.task = tip;
        tip2.type = HanniTask.TIP_TYPE;
        tip2.completed = true;
        tasks.add(tip2);
        return true;
    }

    /**
     * add a new task to this account if no exit. this method also return the new
     * added
     * task or the previons task (if the same task was previous added)
     * 
     * @param task - taskid to add.
     * @return a HanniTask
     */
    public HanniTask addTask(String task) {
        Optional<HanniTask> optional = tasks.stream().filter(e -> e.task.equals(task)).findAny();
        if (optional.isPresent())
            return optional.get();

        HanniTask hanniTask = new HanniTask(task);
        tasks.add(hanniTask);
        return hanniTask;
    }

}
