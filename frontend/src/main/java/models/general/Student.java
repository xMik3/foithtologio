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

    public Student(String ID,String Name,String Surname,int Semester,int AvailableCourses,int EnrollmentYear) {
        this.ID=ID;
        this.Name=Name;
        this.Surname=Surname;
        this.Semester=Semester;
        this.AvailableCourses=AvailableCourses;
        this.EnrollmentYear=EnrollmentYear;
    }

}
