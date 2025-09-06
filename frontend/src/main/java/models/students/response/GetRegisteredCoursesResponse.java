package models.students.response;

import models.general.ApiResponse;
import models.students.RegisteredCourse;

import java.util.ArrayList;
import java.util.List;

public class GetRegisteredCoursesResponse extends ApiResponse {

    private ArrayList<RegisteredCourse> courses;

    public ArrayList<RegisteredCourse> getRegisteredCourses() {
        return this.courses;
    }

}
