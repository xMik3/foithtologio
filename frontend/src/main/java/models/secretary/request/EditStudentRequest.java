package models.secretary.request;

import models.secretary.request.CreateStudentRequest;

public class EditStudentRequest {

    String studentName;
    String studentSurname;
    String studentPWD;

    public EditStudentRequest(String studentName,String studentSurname,String studentPWD) {
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.studentPWD = studentPWD;
    }

}
