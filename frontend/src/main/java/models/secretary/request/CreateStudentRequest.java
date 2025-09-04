package models.secretary.request;

public class CreateStudentRequest {

    private String studentName;
    private String studentSurname;
    private String studentPWD;
    private int studentEnrollmentYear;

    public CreateStudentRequest(String studentName, String studentSurname, String studentPWD, int studentEnrollmentYear) {
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.studentPWD = studentPWD;
        this.studentEnrollmentYear = studentEnrollmentYear;
    }

}

