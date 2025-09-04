package api;

import models.general.ApiResponse;
import models.secretary.request.*;
import models.secretary.response.GetCoursesResponse;
import models.secretary.response.GetCourseResponse;

import models.secretary.response.GetStudentResponse;
import models.secretary.response.GetStudentsResponse;

import models.secretary.response.GetTeachersResponse;
import models.secretary.response.GetTeacherResponse;


import retrofit2.Call;
import retrofit2.http.*;

public interface SecretaryInterface {

    @PUT("/courses")
    Call<ApiResponse> addCourse(@Header("Authorization") String token, CreateCourseRequest request);

    @GET("/courses")
    Call<GetCoursesResponse> getCourses(@Header("Authorization") String token);

    @GET("/courses/{courseID}")
    Call<GetCourseResponse> getCourse(@Header("Authorization") String token,@Path("courseID") String courseID);

    @PATCH("/courses/{courseID}")
    Call<ApiResponse> editCourse(@Header("Authorization") String token, @Path("courseID") String courseID, EditCourseRequest request);

    @DELETE("/courses/{courseID}")
    Call<ApiResponse> deleteCourse(@Header("Authorization") String token, @Path("courseID") String courseID);

    @PUT("/courses/{courseID}/{teacherID}")
    Call<ApiResponse> assignTeacher(@Header("Authorization") String token, @Path("courseID") String courseID, @Path("teacherID") String teacherID);


    @PUT("/teachers")
    Call<ApiResponse> addTeacher(@Header("Authorization") String token, CreateTeacherRequest request);

    @GET("/teachers")
    Call<GetTeachersResponse> getTeachers(@Header("Authorization") String token);

    @GET("/teachers/{teacherID}")
    Call<GetTeacherResponse> getTeacher(@Header("Authorization") String token,@Path("teacherID") String teacherID);

    @PATCH("/teachers/{teacherID}")
    Call<ApiResponse> editTeacher(@Header("Authorization") String token, @Path("teacherID") String teacherID, EditTeacherRequest request);

    @DELETE("/teacher/{teacherID}")
    Call<ApiResponse> deleteTeacher(@Header("Authorization") String token, @Path("teacherID") String teacherID);


    @PUT("/students")
    Call<ApiResponse> addStudent(@Header("Authorization") String token, CreateStudentRequest request);

    @GET("/students/year/{year}")
    Call<GetStudentsResponse> getStudents(@Header("Authorization") String token,@Path("year") int year);

    @GET("/students/{studentID}")
    Call<GetStudentsResponse> getStudents(@Header("Authorization") String token,@Path("studentID") String studentID);

    @PATCH("/students/{studentID}")
    Call<ApiResponse> editStudent(@Header("Authorization") String token, @Path("studentID") String studentID, EditStudentRequest request);

    @DELETE("/students/{studentID}")
    Call<ApiResponse> deleteStudent(@Header("Authorization") String token, @Path("studentID") String studentID);
}
