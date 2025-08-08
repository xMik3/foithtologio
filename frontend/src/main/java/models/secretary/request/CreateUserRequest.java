package models.secretary.request;

public class CreateUserRequest {

    private String userName;
    private String userSurname;
    private String userPWD;

    public CreateUserRequest(String userName, String userSurname, String userPWD){
        this.userName = userName;
        this.userSurname = userSurname;
        this.userPWD = userPWD;
    }

}
