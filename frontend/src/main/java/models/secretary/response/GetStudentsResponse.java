package models.secretary.response;

import models.general.Response;
import models.general.Student;
import java.util.List;

public class GetStudentsResponse extends Response {

    private  List<Student> students;

    public List<Student> getStudents() {
        return this.students;
    }

}
