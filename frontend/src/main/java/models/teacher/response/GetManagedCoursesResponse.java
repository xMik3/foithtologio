package models.teacher.response;

import models.general.ApiResponse;
import models.teacher.ManagedCourse;

import java.util.ArrayList;

public class GetManagedCoursesResponse extends ApiResponse {

    private ArrayList<ManagedCourse> managedCourses;

    public ArrayList<ManagedCourse> getManagedCourses() {
        return this.managedCourses;
    }

}
