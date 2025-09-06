package models.teacher.response;

import models.general.ApiResponse;
import models.teacher.ManagedStudent;

import java.util.ArrayList;

public class GetManagedStudentsResponse extends ApiResponse {

    private ArrayList<ManagedStudent> students;

    public ArrayList<ManagedStudent> getStudents() {
        return this.students;
    }

}
