package models.secretary.response;

import models.general.ApiResponse;
import models.general.Course;
import java.util.List;

public class GetCoursesResponse extends ApiResponse {

    private  List<Course> courses;

    public List<Course> getCourses() {
        return this.courses;
    }

}
