package models.teacher.response;

import models.general.ApiResponse;
import models.teacher.ManagedStudent;
import java.util.List;

public class GetManagedStudentsResponse extends ApiResponse {

    private List<ManagedStudent> students;

    public List<ManagedStudent> getStudents() {
        return this.students;
    }

}
