package models.login.response;

import models.general.ApiResponse;

public class LoginResponse extends ApiResponse {

    private String token;

    public String getToken() {
        return this.token;
    }

}
