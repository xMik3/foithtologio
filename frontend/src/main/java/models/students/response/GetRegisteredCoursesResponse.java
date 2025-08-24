package models.students.response;

import models.general.ApiResponse;
import models.students.RegisteredCourse;

import java.util.List;

public class GetRegisteredCoursesResponse extends ApiResponse {

    private List<RegisteredCourse> courses;

    public List<RegisteredCourse> getRegisteredCourses() {
        return this.courses;
    }

}
