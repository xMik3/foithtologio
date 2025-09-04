package models.secretary.response;

import models.general.ApiResponse;
import models.general.Course;
import java.util.List;

public class GetCourseResponse extends ApiResponse {

    private  Course course;

    public Course getCourse() {
        return this.course;
    }

}