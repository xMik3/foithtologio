package api;

import models.general.ApiResponse;
import models.secretary.request.*;
import models.secretary.response.*;


import retrofit2.Call;
import retrofit2.http.*;
import views.secretary.AddTeacher;

public interface SecretaryInterface {

    @PUT("/courses")
    Call<AddCourseResponse> addCourse(@Header("Authorization") String token, @Body CreateCourseRequest request);

    @GET("/courses")
    Call<GetCoursesResponse> getCourses(@Header("Authorization") String token);

    @GET("/courses/{courseID}")
    Call<GetCourseResponse> getCourse(@Header("Authorization") String token,@Path("courseID") String courseID);

    @PATCH("/courses/{courseID}")
    Call<ApiResponse> editCourse(@Header("Authorization") String token, @Path("courseID") String courseID,@Body EditCourseRequest request);

    @DELETE("/courses/{courseID}")
    Call<ApiResponse> deleteCourse(@Header("Authorization") String token, @Path("courseID") String courseID);


    @PUT("/teachers")
    Call<AddTeacherResponse> addTeacher(@Header("Authorization") String token, @Body CreateTeacherRequest request);

    @GET("/teachers")
    Call<GetTeachersResponse> getTeachers(@Header("Authorization") String token);

    @GET("/teachers/{teacherID}")
    Call<GetTeacherResponse> getTeacher(@Header("Authorization") String token,@Path("teacherID") String teacherID);

    @PATCH("/teachers/{teacherID}")
    Call<ApiResponse> editTeacher(@Header("Authorization") String token, @Path("teacherID") String teacherID,@Body EditTeacherRequest request);

    @DELETE("/teachers/{teacherID}")
    Call<ApiResponse> deleteTeacher(@Header("Authorization") String token, @Path("teacherID") String teacherID);


    @PUT("/students")
    Call<AddStudentResponse> addStudent(@Header("Authorization") String token, @Body CreateStudentRequest request);

    @GET("/students/year/{year}")
    Call<GetStudentsResponse> getStudents(@Header("Authorization") String token,@Path("year") int year);

    @GET("/students/{studentID}")
    Call<GetStudentsResponse> getStudent(@Header("Authorization") String token,@Path("studentID") String studentID);

    @PATCH("/students/{studentID}")
    Call<ApiResponse> editStudent(@Header("Authorization") String token, @Path("studentID") String studentID,@Body EditStudentRequest request);

    @DELETE("/students/{studentID}")
    Call<ApiResponse> deleteStudent(@Header("Authorization") String token, @Path("studentID") String studentID);
}
