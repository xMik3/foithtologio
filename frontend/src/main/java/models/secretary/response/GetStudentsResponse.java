package models.secretary.response;

import models.general.ApiResponse;
import models.general.Student;

import java.util.ArrayList;

public class GetStudentsResponse extends ApiResponse {

    private ArrayList<Student> students;

    public ArrayList<Student> getStudents() {
        return this.students;
    }

}
