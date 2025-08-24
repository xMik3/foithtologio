package models.secretary.response;

import models.general.ApiResponse;
import models.general.Student;
import java.util.List;

public class GetStudentsResponse extends ApiResponse {

    private  List<Student> students;

    public List<Student> getStudents() {
        return this.students;
    }

}
