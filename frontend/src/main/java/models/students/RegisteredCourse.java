package models.students;

public class RegisteredCourse {

    private String ID;
    private String Name;
    private int Semester;
    private Double Grade;
    private String TeacherName;
    private String TeacherSurname;

    public String getID() {
        return this.ID;
    }

    public String getName() {
        return this.Name;
    }

    public int getSemester() {
        return this.Semester;
    }

    public Double getGrade() {
        return this.Grade;
    }

    public String getTeacherName() {
        return this.TeacherName;
    }

    public String getTeacherSurname() {
        return this.TeacherSurname;
    }

}
