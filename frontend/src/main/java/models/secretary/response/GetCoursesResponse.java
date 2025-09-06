package models.secretary.response;

import models.general.ApiResponse;
import models.general.Course;

import java.util.ArrayList;

public class GetCoursesResponse extends ApiResponse {

    private ArrayList<Course> courses;

    public ArrayList<Course> getCourses() {
        return this.courses;
    }

}
