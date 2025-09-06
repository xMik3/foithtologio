package api;

import models.general.ApiResponse;
import models.students.response.GetAvailableCoursesResponse;
import models.students.response.GetRegisteredCoursesResponse;
import models.students.request.RegisterCoursesRequest;

import retrofit2.Call;
import retrofit2.http.*;


public interface StudentInterface {

    @GET("/registeredCourses")
    Call<GetRegisteredCoursesResponse> getRegisteredCourses(@Header("Authorization") String token);

    @PUT("/registeredCourses")
    Call<ApiResponse> registerCourse(@Header("Authorization") String token,@Body RegisterCoursesRequest request);

    @DELETE("registeredCourses/{courseID")
    Call<ApiResponse> deleteCourse(@Path("courseID") String courseID, @Header("Authorization") String token);

    @GET("/availableCourses")
    Call<GetAvailableCoursesResponse> getAvailableCourses(@Header("Authorization") String token);

}
