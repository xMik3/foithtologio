package api;

import models.general.Response;
import models.students.response.GetRegisteredCoursesResponse;

import retrofit2.Call;
import retrofit2.http.*;


public interface StudentInterface {

    @GET("/registeredCourses")
    Call<GetRegisteredCoursesResponse> getRegisteredCourses(@Header("Authorization") String token);

    @PATCH("/registeredCourses/{courseID}")
    Call<Response> registerCourse(@Path("courseID") String courseID, @Header("Authorization") String token);

    @DELETE("registeredCourses/{courseID")
    Call<Response> deleteCourse(@Path("courseID") String courseID, @Header("Authorization") String token);

}
