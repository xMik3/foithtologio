package models.secretary.request;

import models.secretary.request.CreateCourseRequest;

public class EditCourseRequest extends CreateCourseRequest{

    private String courseID;

    public EditCourseRequest(String courseID,String courseName, int courseSemester) {
        super(courseName, courseSemester);
        this.courseID = courseID;
    }

}
