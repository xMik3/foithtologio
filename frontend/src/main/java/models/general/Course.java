package models.general;

public class Course {

    private String ID;
    private String Name;
    private int Semester;
    private String TeacherID;
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

    public String getTeacherID() {
        return this.TeacherID;
    }

    public Course(String ID, String Name, int Semester,String TeacherID, String TeacherName, String TeacherSurname) {
        this.ID = ID;
        this.Name = Name;
        this.Semester = Semester;
        this.TeacherID = TeacherID;
        this.TeacherName = TeacherName;
        this.TeacherSurname = TeacherSurname;
    }

}
