package controllers;

import api.LoginInterface;
import api.SecretaryInterface;
import client.ApiClient;
import client.TokenManager;

import models.general.Course;
import models.login.request.LoginRequest;
import models.login.response.LoginResponse;
import models.secretary.response.GetCoursesResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class TestController {

    public static void main(String[] args) {

        LoginInterface loginInterface = ApiClient.getClient("http://localhost:3000").create(LoginInterface.class);
        LoginRequest request = new LoginRequest("000000", "kimas", "secretary");

        Call<LoginResponse> call = loginInterface.login(request);
        call.enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful()) {
                    TokenManager.setToken("Bearer " + response.body().getToken());
                } else {
                    System.out.println(response.body().getMessage());
                    return;
                }

                SecretaryInterface secretaryInterface = ApiClient.getClient("http://localhost:3000").create(SecretaryInterface.class);

                Call<GetCoursesResponse> call2 = secretaryInterface.getCourses(TokenManager.getToken());
                call2.enqueue(new Callback<GetCoursesResponse>() {

                    @Override
                    public void onResponse(Call<GetCoursesResponse> call, Response<GetCoursesResponse> response) {

                        if(response.isSuccessful()){
                            List<Course> courses = response.body().getCourses();

                            for (Course course : courses) {
                                System.out.println(course.getNAME());
                            }
                        }
                        else{
                            System.out.println(response.body().getMessage());
                            return;
                        }

                    }

                    @Override
                    public void onFailure(Call<GetCoursesResponse> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }

                });

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                System.out.println(t.getMessage());
            }

        });


    }
}