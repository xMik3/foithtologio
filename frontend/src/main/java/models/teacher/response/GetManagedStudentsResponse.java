package models.teacher.response;

import models.general.Response;
import models.teacher.ManagedStudent;
import java.util.List;

public class GetManagedStudentsResponse extends Response {

    private List<ManagedStudent> students;

    public List<ManagedStudent> getStudents() {
        return this.students;
    }

}
