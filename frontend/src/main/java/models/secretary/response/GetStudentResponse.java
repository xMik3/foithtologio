package models.secretary.response;

import models.general.ApiResponse;
import models.general.Student;
import java.util.List;

public class GetStudentResponse extends ApiResponse {

    private  Student student;

    public Student getStudent() {
        return this.student;
    }

}
