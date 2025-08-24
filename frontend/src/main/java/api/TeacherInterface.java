package api;

import models.general.ApiResponse;
import models.teacher.request.GradeRequest;
import models.teacher.response.GetManagedCoursesResponse;
import models.teacher.response.GetManagedStudentsResponse;

import retrofit2.Call;
import retrofit2.http.*;

public interface TeacherInterface {

    @GET("/managedCourses")
    Call<GetManagedCoursesResponse> getManagedCourses(@Header("Authorization") String token);

    @GET("/managedCourses/{courseID}/students")
    Call<GetManagedStudentsResponse> getManagedStudents(@Header("Authorization") String token, @Path("courseID") String courseID);

    @PATCH("/managedCourses/{courseID}/students/{studentID}")
    Call<ApiResponse> gradeStudent(@Header("Authorization") String token, @Path("courseID") String courseID, @Path("studentID") String studentID, @Body GradeRequest request);

}
