package models.general;

public class Teacher {

    private String ID;
    private String Name;
    private String Surname;

    public String getID() {
        return this.ID;
    }

    public String getName() {
        return this.Name;
    }

    public String getSurname() {
        return this.Surname;
    }

    public Teacher(String ID,String Name,String Surname){
        this.ID=ID;
        this.Name=Name;
        this.Surname=Surname;
    }

}
