package models.general;

public class Student {

    private String ID;
    private String Name;
    private String Surname;
    private int Semester;
    private int AvailableCourses;
    private int EnrollmentYear;


    public String getID() {
        return this.ID;
    }

    public String getName() {
        return this.Name;
    }

    public String getSurname() {
        return this.Surname;
    }

    public int getSemester() {
        return this.Semester;
    }

    public int getAvailableCourses() {
        return this.AvailableCourses;
    }

    public int getEnrollmentYear() {
        return this.EnrollmentYear;
    }

}
