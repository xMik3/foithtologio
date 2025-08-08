package models.students.response;

import models.general.Response;
import models.students.RegisteredCourse;

import java.util.List;

public class GetRegisteredCoursesResponse extends Response {

    private List<RegisteredCourse> courses;

    public List<RegisteredCourse> getRegisteredCourses() {
        return this.courses;
    }

}
