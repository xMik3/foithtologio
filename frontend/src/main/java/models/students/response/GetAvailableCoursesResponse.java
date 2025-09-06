package models.students.response;

import models.general.ApiResponse;
import models.students.AvailableCourse;

import java.util.ArrayList;

public class GetAvailableCoursesResponse extends ApiResponse {

    private ArrayList<AvailableCourse> courses;

    public ArrayList<AvailableCourse> getAvailableCourses() {
        return this.courses;
    }

}
