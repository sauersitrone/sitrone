package de.simone.backend;

public class AuthenticationResult {

    public static final String SUCCESS = "SUCCESS";
    public static final String SUCCESS_FINAL = "SUCCESS_FINAL";
    public static final String SUCCESS_RESET_OPTIONS = "SUCCESS_RESET_OPTIONS";
    public static final String SUCCESS_CONFIRMATION_SENDED = "SUCCESS_CONFIRMATION_SENDED";
    public static final String FAILED = "FAILED";
    public static final String FAILED_USER_PASS = "FAILED_USER_PASS";
    public static final String FAILED_TOTP = "FAILED_TOTP";
    public static final String FAILED_USER_EMAIL_EXIST = "FAILED_USER_EMAIL_EXIST";
    public static final String FAILED_USER_EMAIL_DONT_EXIST = "FAILED_USER_EMAIL_DONT_EXIST";
    public static final String FAILED_ACCOUNT_EXIST = "FAILED_ACCOUNT_EXIST";
    public static final String FAILED_PASSWORD_POLICY = "FAILED_PASSWORD_POLICY";
    public static final String FAILED_ACCOUNT_LOCKED = "FAILED_ACCOUNT_LOCKED";

    public String result;
    public Object payload;
    public boolean isSuccess;
    public User user;

    public AuthenticationResult() {
        this(null, null);
    }

    public AuthenticationResult(String result) {
        this(result, null, null);
    }

    public AuthenticationResult(String result, User user) {
        this(result, user, null);
    }

    public AuthenticationResult(String result, User user, Object payload) {
        this.result = result;
        this.payload = payload;
        this.user = user;
        isSuccess = result != null && result.contains(SUCCESS);
    }
}
