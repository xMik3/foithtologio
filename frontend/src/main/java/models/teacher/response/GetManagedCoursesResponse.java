package models.teacher.response;

import models.general.ApiResponse;
import models.general.Course;
import models.teacher.ManagedCourse;

import java.util.List;

public class GetManagedCoursesResponse extends ApiResponse {

    private List<ManagedCourse> managedCourses;

    public List<ManagedCourse> getManagedCourses() {
        return this.managedCourses;
    }

}
