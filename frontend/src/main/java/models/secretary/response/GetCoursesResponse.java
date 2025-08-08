package models.secretary.response;

import models.general.Response;
import models.general.Course;
import java.util.List;

public class GetCoursesResponse extends Response {

    private  List<Course> courses;

    public List<Course> getCourses() {
        return this.courses;
    }

}
