package api;

import models.login.request.LoginRequest;
import models.login.response.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginInterface {
    @POST("/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
}