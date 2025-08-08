package models.secretary.request;

public class CreateCourseRequest {

    private String courseName;
    private int courseSemester;

    public CreateCourseRequest(String courseName, int courseSemester) {
        this.courseName = courseName;
        this.courseSemester = courseSemester;
    }

}
