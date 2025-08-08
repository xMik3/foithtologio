package models.secretary.response;

import models.general.Response;
import models.general.Teacher;
import java.util.List;

public class GetTeachersResponse extends Response {

    private  List<Teacher> teachers;

    public List<Teacher> getTeachers() {
        return this.teachers;
    }

}
