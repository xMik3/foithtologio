
package views.secretary;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

import api.SecretaryInterface;
import models.secretary.request.CreateUserRequest;
import models.general.ApiResponse;
import client.ApiClient;

import org.jdesktop.swingx.prompt.PromptSupport;
import com.google.gson.Gson;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addusr extends JFrame {
     
     private JPanel[] panels;
     private JTextField name;
     private JTextField pswd;
     private JTextField surname;
     
     private JLabel Lname;
     private JLabel Lpswd;
     private JLabel Lsurname;
     private JLabel Laddusr;
     private JLabel requesterrorlabel;
     private JLabel errorLabel;
     
     private JButton cnfrm;
     
            public addusr(String title){
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
             panels = new JPanel[11];
             for (int i = 0; i < panels.length; i++) {
             panels[i] = new JPanel();
             }  
             
             name = new JTextField(20);
             surname = new JTextField(20);
             pswd = new JTextField(20);
             
             Laddusr = new JLabel("Add User");
             Laddusr.setFont(new Font("Arial", Font.PLAIN, 30));
             
             Lname = new JLabel("Name:");
             Lname.setFont(new Font("Arial", Font.PLAIN, 20));
             
             Lsurname = new JLabel("Semester:");
             Lsurname.setFont(new Font("Arial", Font.PLAIN, 20));
             
             Lpswd = new JLabel("Semester:");
             Lpswd.setFont(new Font("Arial", Font.PLAIN, 20));
             
             cnfrm = new JButton("Confirm");
             cnfrm.setFont(new Font("Arial", Font.PLAIN, 20));
             
             panels[0].setLayout(new GridLayout(6,2));
             panels[1].add(Laddusr);
             panels[3].add(Lname);
             panels[4].add(name);
             panels[5].add(Lsurname);
             panels[6].add(surname);
             panels[7].add(Lpswd);
             panels[8].add(pswd);
             panels[10].add(cnfrm);
             
             for(int i=1;i<11;i++){
                 panels[0].add(panels[i]);
            }
    
             cnfrm.addActionListener
                (
                    l -> 
                    {
                        String requestname = name.getText();
                        if(requestname.length()<=0){
                            requesterrorlabel.setText("User's Name Can Not Be Empty");
                        }
                        
                        
            CreateUserRequest crtusrRequest = new CreateUserRequest(name.getText(), surname.getText(),pswd.getText());
            Call<ApiResponse> call = secInterface.addStudent(ApiClient.getToken(),crtusrRequest);
            
            call.enqueue(new Callback<ApiResponse>() {

                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                    if (response.isSuccessful()) {
                        ApiResponse crtUsrResponse = response.body();
                        

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
            setSize(600,800);
    }
            
            public static void main(String args[]){
                new addusr("bruh");
            }
            
}
