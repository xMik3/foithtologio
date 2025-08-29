
package views.secretary;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

import api.SecretaryInterface;
import models.secretary.request.CreateCourseRequest;
import models.general.ApiResponse;
import client.ApiClient;

import org.jdesktop.swingx.prompt.PromptSupport;
import com.google.gson.Gson;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addcrs extends JFrame {
     
     private JPanel[] panels;
     private JTextField name;
     private JTextField semester;

     private JLabel Laddcrs;
     private JLabel Lname;
     private JLabel Lsemester;
     private JLabel requesterrorlabel;
     private JLabel errorLabel;
     
     private JButton cnfrm;
     
            public addcrs(String title){
            super(title);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            Gson gson = new Gson();
            SecretaryInterface secInterface = ApiClient.getClient().create(SecretaryInterface.class);
            
            
             try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
            UIManager.put("TextComponent.arc", 25);
            UIManager.put( "Button.arc", 999 );
            } catch (Exception e) {
            e.printStackTrace();
        }
             panels = new JPanel[9];
             for (int i = 0; i < panels.length; i++) {
             panels[i] = new JPanel();
             }  
             
             name = new JTextField(20);
             semester = new JTextField(20);
             
             
             Laddcrs = new JLabel("Add Course");
             Laddcrs.setFont(new Font("Arial", Font.PLAIN, 30));
             
             Lname = new JLabel("Name:");
             Lname.setFont(new Font("Arial", Font.PLAIN, 20));
             
             Lsemester = new JLabel("Semester:");
             Lsemester.setFont(new Font("Arial", Font.PLAIN, 20));
             
             
             cnfrm = new JButton("Confirm");
             cnfrm.setFont(new Font("Arial", Font.PLAIN, 20));
             
             panels[0].setLayout(new GridLayout(6,2));
             panels[1].add(Laddcrs);
             panels[3].add(Lname);
             panels[4].add(name);
             panels[5].add(Lsemester);
             panels[6].add(semester);
             panels[8].add(cnfrm);
             
             for(int i=1;i<9;i++){
                 panels[0].add(panels[i]);
            }
    
             cnfrm.addActionListener
                (
                    l -> 
                    {
                        String requestname = name.getText();
                        if(requestname.length()<=0){
                            requesterrorlabel.setText("Courses' Name Can Not Be Empty");
                        }
                        
                        int isemester = Integer.parseInt(semester.getText());
                        
                        
            CreateCourseRequest crtcrsRequest = new CreateCourseRequest(name.getText(), isemester);
            Call<ApiResponse> call = secInterface.addCourse(ApiClient.getToken(),crtcrsRequest);
            
            call.enqueue(new Callback<ApiResponse>() {

                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                    if (response.isSuccessful()) {
                        ApiResponse crtcrsresponse = response.body();
                        

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
                    }
                );
            
            add(panels[0]);
            setVisible(true);
            panels[0].setVisible(true);
            setSize(1200,700);
    }
            
            public static void main(String args[]){
                new addcrs("bruh");
            }
            
}
