package models.secretary.request;

public class CreateTeacherRequest {

        private String teacherName;
        private String teacherSurname;
        private String teacherPWD;

        public CreateTeacherRequest(String teacherName, String teacherSurname, String teacherPWD){
            this.teacherName = teacherName;
            this.teacherSurname = teacherSurname;
            this.teacherPWD = teacherPWD;
        }

}



