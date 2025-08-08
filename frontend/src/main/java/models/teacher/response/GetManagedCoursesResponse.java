package models.teacher.response;

import models.general.Response;
import models.general.Course;
import java.util.List;

public class GetManagedCoursesResponse extends Response{

    private List<Course> courses;

    public List<Course> getCourses() {
        return this.courses;
    }

}
