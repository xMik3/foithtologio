package views.general;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

import api.LoginInterface;
import models.login.request.LoginRequest;
import models.login.response.LoginResponse;
import models.general.ApiResponse;
import client.ApiClient;

import views.secretary.SecretaryView;

import org.jdesktop.swingx.prompt.PromptSupport;
import com.google.gson.Gson;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import views.student.StudView;
import views.teacher.TeachView;

public class Login extends JFrame{

    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JComboBox typeSelection;
    private final JLabel errorLabel;
    private final JButton loginButton;

    public Login(String title){
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LoginInterface loginInterface = ApiClient.getClient().create(LoginInterface.class);
        Gson gson = new Gson();

        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
            UIManager.put("TextComponent.arc", 25);
            UIManager.put( "Button.arc", 999 );
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel icon = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/temporaryIcon.png"))));
        String[] options = {"Student","Teacher","Secretary"};
        typeSelection = new JComboBox(options);
        typeSelection.setFont(new Font("Arial", Font.PLAIN, 25));

        JPanel leftUpPanel = new JPanel(new BorderLayout());
        leftUpPanel.add(icon,BorderLayout.CENTER);
        leftUpPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));

        JPanel leftDownPanel = new JPanel(new BorderLayout());
        leftDownPanel.add(typeSelection,BorderLayout.CENTER);
        leftDownPanel.setBorder(BorderFactory.createEmptyBorder(30, 140, 210, 140));

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setLayout(new GridLayout(2,1,0,25));
        leftPanel.add(leftUpPanel, BorderLayout.CENTER);
        leftPanel.add(leftDownPanel, BorderLayout.SOUTH);

        usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 25));
        PromptSupport.setPrompt("Enter Username", usernameField);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 25));
        PromptSupport.setPrompt("Enter Password", passwordField);

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.PLAIN, 25));
        loginButton.setText("Login");

        loginButton.addActionListener(e -> {

            String userType = typeSelection.getSelectedItem().toString();

            String userID = usernameField.getText();
            if(validateUserID(userID)){
                return;
            }

            String userPWD = new String(passwordField.getPassword());
            if(validateUserPWD(userPWD)){
                return;
            }

            LoginRequest loginRequest = new LoginRequest(userID, userPWD, userType);
            Call<LoginResponse> call = loginInterface.login(loginRequest);
            call.enqueue(new Callback<LoginResponse>() {

                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                    if (response.isSuccessful()) {
                        LoginResponse loginResponse = response.body();
                        ApiClient.setToken("Bearer " + loginResponse.getToken());

                        if(userType.equals("Secretary")){

                            JFrame secretaryWindow = new SecretaryView("Secretary");

                            secretaryWindow.setLocationRelativeTo(null);
                            secretaryWindow.setVisible(true);

                            dispose();
                        } else if (userType.equals("Student"))
                        {
                            JFrame studentWindow = new StudView("Student");
                            
                            studentWindow.setLocationRelativeTo(null);
                            studentWindow.setVisible(true);

                            dispose();
                        } else if (userType.equals("Teacher"))
                        {
                            JFrame teacherWindow = new TeachView("Teacher");
                            
                            teacherWindow.setLocationRelativeTo(null);
                            teacherWindow.setVisible(true);

                            dispose();
                        }

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
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    errorLabel.setText("*Something went wrong. Please try again.");
                }

            });
        });

        errorLabel = new JLabel("");
        errorLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        errorLabel.setForeground(Color.red);

        JPanel rightMiddlePanel = new JPanel(new BorderLayout());
        rightMiddlePanel.setLayout(new GridLayout(2,1,0,25));
        rightMiddlePanel.add(usernameField, BorderLayout.CENTER);
        rightMiddlePanel.add(passwordField, BorderLayout.SOUTH);
        rightMiddlePanel.setBorder(BorderFactory.createEmptyBorder(100, 0, 55, 0));

        JPanel rightDownPanel = new JPanel(new BorderLayout());
        rightDownPanel.add(loginButton,BorderLayout.NORTH);
        rightDownPanel.setBorder(BorderFactory.createEmptyBorder(0, 140, 230, 140));

        JPanel rightPanel = new JPanel(new  BorderLayout());
        rightPanel.setLayout(new GridLayout(2,1,0,0));
        rightPanel.add(rightMiddlePanel, BorderLayout.CENTER);
        rightPanel.add(rightDownPanel, BorderLayout.SOUTH);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 0, 100));
        panel.setLayout(new GridLayout(1,2,100,0));
        panel.add(leftPanel, BorderLayout.WEST);
        panel.add(rightPanel,BorderLayout.EAST);
        panel.setBounds(0,0,1200,700);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1200,700));
        layeredPane.add(panel,JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(errorLabel,JLayeredPane.PALETTE_LAYER);

        add(layeredPane);

        setSize(1200, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

    }

    private boolean validateUserID(String userID){
        if(userID.length()!=6){
            errorLabel.setBounds(400,550,600,50);
            errorLabel.setText("*User ID Must Be 6 Characters Long.");
            return true;
        }

        return false;
    }

    private boolean validateUserPWD(String userPWD){

        if(userPWD.length()<4){
            errorLabel.setBounds(350,550,600,50);
            errorLabel.setText("*Password Must Be Longer Than 4 Characters.");
            return true;
        }
        else if(userPWD.length()>20){
            errorLabel.setBounds(350,550,600,50);
            errorLabel.setText("*Password Must Be Shorter Than 20 Characters.");
            return true;
        }
        else{
            errorLabel.setText("");
            return false;
        }

    }


    public static void main(String[] args) {
        new Login("Login");

    }

}