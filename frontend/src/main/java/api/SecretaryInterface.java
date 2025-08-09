package api;

import models.general.Response;
import models.secretary.request.*;
import models.secretary.response.GetCoursesResponse;

import models.secretary.response.GetStudentsResponse;
import models.secretary.response.GetTeachersResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface SecretaryInterface {

    @GET("/courses")
    Call<GetCoursesResponse> getCourses(@Header("Authorization") String token);

    @PUT("/courses")
    Call<Response> addCourse(@Header("Authorization") String token, CreateCourseRequest request);

    @PATCH("/courses/{courseID}")
    Call<Response> editCourse(@Header("Authorization") String token, @Path("courseID") String courseID, EditCourseRequest request);

    @DELETE("/courses/{courseID}")
    Call<Response> deleteCourse(@Header("Authorization") String token, @Path("courseID") String courseID);

    @PUT("/courses/{courseID}/{teacherID}")
    Call<Response> assignTeacher(@Header("Authorization") String token, @Path("courseID") String courseID, @Path("teacherID") String teacherID);


    @GET("/teachers")
    Call<GetTeachersResponse> getTeachers(@Header("Authorization") String token);

    @PUT("/teachers")
    Call<Response> addTeacher(@Header("Authorization") String token, CreateUserRequest request);

    @PATCH("/teachers/{teacherID}")
    Call<Response> editTeacher(@Header("Authorization") String token, @Path("teacherID") String teacherID, EditTeacherRequest request);

    @DELETE("/teacher/{teacherID}")
    Call<Response> deleteTeacher(@Header("Authorization") String token, @Path("teacherID") String teacherID);


    @GET("/students")
    Call<GetStudentsResponse> getStudents(@Header("Authorization") String token);

    @PUT("/students")
    Call<Response> addStudent(@Header("Authorization") String token, CreateUserRequest request);

    @PATCH("/students/{studentID}")
    Call<Response> editStudent(@Header("Authorization") String token, @Path("studentID") String studentID, EditStudentRequest request);

    @DELETE("/students/{studentID}")
    Call<Response> deleteStudent(@Header("Authorization") String token, @Path("studentID") String studentID);
}
