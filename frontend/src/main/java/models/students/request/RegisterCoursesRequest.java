package models.students.request;

import java.util.ArrayList;

public class RegisterCoursesRequest {

    private ArrayList<String> courses;

    public RegisterCoursesRequest(ArrayList<String> courses) {
        this.courses = courses;
    }

}
