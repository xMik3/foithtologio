package models.secretary.request;

import models.secretary.request.EditStudentRequest;

public class EditTeacherRequest extends CreateUserRequest{

    private String teacherID;

    public EditTeacherRequest(String teacherID,String userName,String userSurname,String userPWD) {
        super(userName,userSurname,userPWD);
        this.teacherID = teacherID;
    }

}
