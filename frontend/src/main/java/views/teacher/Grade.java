
package views.teacher;

import api.TeacherInterface;
import client.ApiClient;
import com.google.gson.Gson;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import models.general.ApiResponse;
import models.teacher.ManagedCourse;
import models.teacher.ManagedStudent;
import models.teacher.request.GradeRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Grade extends JFrame{
    
    JButton cnfrm;
    
    JLabel mssg;
    JLabel title;
    
    JTextField number;
    
    JPanel titlep;
    JPanel bodyp;
    JPanel controlp;
    JPanel p;
    
    TeachView teacherview;
    TeacherInterface teacherInterface;
    
    JLabel errorLabel;
    
    Gson gson;
    
    public Grade(TeachView teacherview, ManagedCourse course, ManagedStudent student ) {
        
        this.teacherview = teacherview;

        gson = new Gson();
        
        
        try {
                UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
            } catch (Exception e) {
                e.printStackTrace();
            }

            
            teacherInterface = ApiClient.getClient().create(TeacherInterface.class);
        
        p = new JPanel(new BorderLayout());
        titlep = new JPanel(new BorderLayout());
        titlep.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titlep.setBackground(new Color(80, 80, 80));
        
        bodyp = new JPanel(new BorderLayout());
        bodyp.setBackground(new Color(80, 80, 80));
        bodyp.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        controlp = new JPanel(new BorderLayout());
        controlp.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        cnfrm = new JButton("Confirm");
        cnfrm.setFont(new Font("Arial", Font.PLAIN, 20));
        
        number = new JTextField();
        number.setFont(new Font("Arial", Font.PLAIN, 20));
        
        mssg = new JLabel("Assign a grade to" + "" + student.getNAME() + "" + student.getSURNAME() + "" + "for" + "" + course.getName());
        mssg.setFont(new Font("Arial", Font.PLAIN, 20));
        
        title = new JLabel("Grade Student");
        title.setFont(new Font("", Font.PLAIN, 24));
        
         cnfrm.addActionListener
                (
                    l -> 
                    {   String input = number.getText().trim();
                    
                    
                    if (input.matches("\\d+(\\.\\d{1,2})?")) {
                        
                        double dnumber = Double.parseDouble(input);
                        GradeRequest gradeRequest = new GradeRequest(dnumber);
                        
                        Call<ApiResponse> call = teacherInterface.gradeStudent(ApiClient.getToken(),course.getID(),student.getID(),gradeRequest);
            
                        call.enqueue(new Callback<ApiResponse>() {

                    @Override
                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                            if (response.isSuccessful()) {
                            ApiResponse getcrsesResponse = response.body();
                            
                            teacherview.setGrade(dnumber);
                            System.out.println(dnumber);
                            dispose();

                } else {
                        errorLabel.setBounds(500,550,600,50);
                            try {
                                 ApiResponse loginResponse = gson.fromJson(response.errorBody().string(), ApiResponse.class);
                                 errorLabel.setText("*"+ loginResponse.getMessage());
                            } catch (IOException ex) {
                                 errorLabel.setText("*"+ex.getMessage());
                                    }
                                }

                            }

                            @Override
                            public void onFailure(Call<ApiResponse> call, Throwable t) {
                                errorLabel.setText("*Something went wrong. Please try again.");
                            }

                        });
                    
                }else{errorLabel.setText("Invalid Input");
                
                    }
                }
            );
         
         titlep.add(title,BorderLayout.WEST);
         bodyp.add(mssg,BorderLayout.WEST);
         bodyp.add(number,BorderLayout.CENTER);
         controlp.add(cnfrm,BorderLayout.EAST);
         
         p.add(titlep, BorderLayout.NORTH);
         p.add(bodyp, BorderLayout.CENTER);
         p.add(controlp, BorderLayout.SOUTH);
         
         add(p);
         setVisible(true);
         setSize(700,300);
        
    }
    
}
