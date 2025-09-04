package models.secretary.request;

import models.secretary.request.EditStudentRequest;

public class EditTeacherRequest extends CreateTeacherRequest{

    public EditTeacherRequest(String teacherName,String teacherSurname,String teacherPWD) {
        super(teacherName,teacherSurname,teacherPWD);
    }

}
