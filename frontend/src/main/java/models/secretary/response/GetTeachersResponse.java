package models.secretary.response;

import models.general.ApiResponse;
import models.general.Teacher;

import java.util.ArrayList;

public class GetTeachersResponse extends ApiResponse {

    private ArrayList<Teacher> teachers;

    public ArrayList<Teacher> getTeachers() {
        return this.teachers;
    }

}
