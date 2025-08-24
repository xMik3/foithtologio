package models.teacher.response;

import models.general.ApiResponse;
import models.general.Course;
import java.util.List;

public class GetManagedCoursesResponse extends ApiResponse {

    private List<Course> courses;

    public List<Course> getCourses() {
        return this.courses;
    }

}
