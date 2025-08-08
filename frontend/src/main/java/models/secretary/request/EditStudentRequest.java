package models.secretary.request;

import models.secretary.request.CreateUserRequest;

public class EditStudentRequest extends CreateUserRequest{

    private String studentID;

    public EditStudentRequest(String studentID,String userName,String userSurname,String userPWD) {
        super(userName,userSurname,userPWD);
        this.studentID = studentID;
    }

}
