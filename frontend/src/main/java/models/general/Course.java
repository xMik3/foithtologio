package models.general;

public class Course {

    private String ID;
    private String Name;
    private int Semester;
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

    public String getTeacherName() {
        return this.TeacherName;
    }

    public String getTeacherSurname() {
        return this.TeacherSurname;
    }

    public Course(String ID, String Name, int Semester, String TeacherName, String TeacherSurname) {
        this.ID = ID;
        this.Name = Name;
        this.Semester = Semester;
        this.TeacherName = TeacherName;
        this.TeacherSurname = TeacherSurname;
    }
}
