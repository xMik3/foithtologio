package models.secretary.request;

public class EditCourseRequest extends CreateCourseRequest{

    public EditCourseRequest(String courseName, int courseSemester, String teacherID) {
        super(courseName, courseSemester,teacherID);
    }

}
