package models.login.response;

import models.general.Response;

public class LoginResponse extends Response {

    private String token;

    public String getToken() {
        return this.token;
    }

}
