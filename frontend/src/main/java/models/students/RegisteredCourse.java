package models.students;

public class RegisteredCourse {

    private String CID;
    private String CNAME;
    private String TNAME;
    private String TSURNAME;
    private Double GRADE;

    public String getCID() {
        return this.CID;
    }

    public String getCNAME() {
        return this.CNAME;
    }

    public String getTSURNAME() {
        return this.TSURNAME;
    }

    public String getTNAME() {
        return this.TNAME;
    }

    public Double getGRADE() {
        return this.GRADE;
    }
}
