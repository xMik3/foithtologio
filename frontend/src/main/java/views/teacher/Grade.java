
package views.teacher;

import api.TeacherInterface;
import client.ApiClient;
import com.google.gson.Gson;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
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
    JPanel errorp;
    JPanel controlp;
    JPanel p;
    JPanel numberp;
    
    TeachView teacherview;
    TeacherInterface teacherInterface;
    
    JLabel errorLabel;
    JLabel dummy;
    
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
        
        p = new JPanel();
        p.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        titlep = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlep.setBackground(new Color(80, 80, 80));
        
        errorp = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        controlp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        cnfrm = new JButton("Confirm");
        cnfrm.setFont(new Font("Arial", Font.PLAIN, 20));
        Dimension buttonsize = cnfrm.getSize();
        
        number = new JTextField(4);
        number.setFont(new Font("Arial", Font.PLAIN, 20));
        number.setMaximumSize(buttonsize);
        
        numberp = new JPanel();
        numberp.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        mssg = new JLabel("Assign a grade to" + " " + student.getNAME() + " " + student.getSURNAME() + " " + "for" + " " + course.getName());
        mssg.setFont(new Font("Arial", Font.PLAIN, 20));
        
        title = new JLabel("Grade Student");
        title.setFont(new Font("", Font.PLAIN, 24));
        
        dummy = new JLabel();
        
        errorLabel = new JLabel();
        errorLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        errorLabel.setForeground(Color.RED);
        
        
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
                    
                }else{
                        errorLabel.setText("Invalid Input");
                
                    }
                }
            );
         
         titlep.add(title);
         
         numberp.add(mssg);
         numberp.add(number);
         
         errorp.add(errorLabel);
         
         controlp.add(cnfrm);
         
         gbc.fill = GridBagConstraints.BOTH;
         
         gbc.weightx = 1;
         
         gbc.gridx = 0;
         gbc.gridy = 0;
         gbc.weighty = 0.15;
         gbc.insets = new Insets(5,5,5,5);
         p.add(titlep,gbc);
         
         gbc.gridx = 0;
         gbc.gridy = 1;
         gbc.weighty = 0.55;
         gbc.insets = new Insets(5,5,5,5);
         p.add(numberp,gbc);
         
         gbc.gridx = 0;
         gbc.gridy = 2;
         gbc.weighty = 0.15;
         gbc.insets = new Insets(5,5,5,5);
         p.add(errorp,gbc);
         
         gbc.gridx = 0;
         gbc.gridy = 3;
         gbc.weighty = 0.15;
         gbc.insets = new Insets(5,5,5,5);
         p.add(controlp,gbc);
         
         add(p);
         setVisible(true);
         setSize(700,300);
         setResizable(false);
        
    }
    
}
