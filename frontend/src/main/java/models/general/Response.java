package models.general;

public class Response {

    private String status;
    private String message;

    private boolean isSuccessful(){
        return this.status.equals("success");
    }

    public String getMessage() {
        return this.message;
    }

}
