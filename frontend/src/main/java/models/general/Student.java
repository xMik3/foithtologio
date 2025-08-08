package models.general;

public class Student {

    private String SID;
    private String NAME;
    private String SURNAME;
    private int SEMESTER;
    private String PASSWORD;
    private int AVAILCOURSES;


    public String getSID() {
        return this.SID;
    }

    public String getNAME() {
        return this.NAME;
    }

    public String getSURNAME() {
        return this.SURNAME;
    }

    public int getSEMESTER() {
        return this.SEMESTER;
    }

    public String getPASSWORD() {
        return this.PASSWORD;
    }

    public int getAVAILCOURSES() {
        return this.AVAILCOURSES;
    }

}
