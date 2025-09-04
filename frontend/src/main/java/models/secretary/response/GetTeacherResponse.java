package models.secretary.response;

import models.general.ApiResponse;
import models.general.Teacher;
import java.util.List;

public class GetTeacherResponse extends ApiResponse {

    private  Teacher teacher;

    public Teacher getTeacher() {
        return this.teacher;
    }

}
