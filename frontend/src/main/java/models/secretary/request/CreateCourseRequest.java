package models.secretary.request;

public class CreateCourseRequest {

    private String courseName;
    private int courseSemester;
    private String teacherID;

    public CreateCourseRequest(String courseName, int courseSemester, String teacherID) {
        this.courseName = courseName;
        this.courseSemester = courseSemester;
        this.teacherID = teacherID;
    }

}
