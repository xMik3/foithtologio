package models.login.request;

public class LoginRequest {
    private String userID;
    private String userPWD;
    private String userType;

    public LoginRequest(String userID, String userPWD, String userType) {
        this.userID = userID;
        this.userPWD = userPWD;
        this.userType = userType;
    }
}
