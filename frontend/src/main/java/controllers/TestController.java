package controllers;

import api.LoginInterface;
import api.StudentInterface;
import client.ApiClient;
import client.TokenManager;

import models.login.request.LoginRequest;
import models.login.response.LoginResponse;
import models.students.RegisteredCourse;
import models.students.response.GetRegisteredCoursesResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class Controller {

    public static void main(String[] args) {

        LoginInterface loginInterface = ApiClient.getClient("http://localhost:3000").create(LoginInterface.class);
        LoginRequest request = new LoginRequest("000001", "hello", "student");

        Call<LoginResponse> call = loginInterface.login(request);
        call.enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful()) {
                    TokenManager.setToken(response.body().getToken());
                } else {
                    System.out.println("not successful");
                    return;
                }

                StudentInterface studentInterface = ApiClient.getClient("http://localhost:3000").create(StudentInterface.class);
                String tokenRequest = "Bearer " + TokenManager.getToken();

                Call<GetRegisteredCoursesResponse> call2 = studentInterface.getRegisteredCourses(tokenRequest);
                call2.enqueue(new Callback<GetRegisteredCoursesResponse>() {

                    @Override
                    public void onResponse(Call<GetRegisteredCoursesResponse> call, Response<GetRegisteredCoursesResponse> response) {

                        if(response.isSuccessful()){
                            List<RegisteredCourse> courses = response.body().getRegisteredCourses();

                            System.out.println(response.body().getMessage());

                            for (RegisteredCourse course : courses) {
                                System.out.println(course.getCNAME());
                            }
                        }
                        else{
                            System.out.println("not successful");
                            return;
                        }

                    }

                    @Override
                    public void onFailure(Call<GetRegisteredCoursesResponse> call, Throwable t) {
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