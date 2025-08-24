package models.secretary.response;

import models.general.ApiResponse;
import models.general.Teacher;
import java.util.List;

public class GetTeachersResponse extends ApiResponse {

    private  List<Teacher> teachers;

    public List<Teacher> getTeachers() {
        return this.teachers;
    }

}
