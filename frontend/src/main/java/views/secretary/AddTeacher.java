package views.secretary;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


import api.SecretaryInterface;
import models.general.ApiResponse;
import client.ApiClient;

import com.google.gson.Gson;
import models.general.Teacher;
import models.secretary.request.CreateTeacherRequest;
import models.secretary.response.AddTeacherResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTeacher extends JPanel {

    private JTextField name;
    private JTextField surname;
    private JTextField password;

    private JButton confirm;

    private Teacher teacher;
    private boolean successful = false;

    public AddTeacher(SecretaryInterface secretaryInterface) {

        Gson gson = new Gson();

        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
        } catch (Exception e) {
            e.printStackTrace();
        }

        name = new JTextField(20);
        name.setFont(new Font("Arial", Font.PLAIN, 20));

        surname = new JTextField(20);
        surname.setFont(new Font("Arial", Font.PLAIN, 20));

        password = new JTextField(20);
        password.setFont(new Font("Arial", Font.PLAIN, 20));


        JLabel addTeacherLabel = new JLabel("Add Teacher");
        addTeacherLabel.setFont(new Font("Arial", Font.PLAIN, 34));

        JLabel nameLabel = new JLabel("Name : ");
        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 24));

        JLabel surnameLabel = new JLabel("Surname : ");
        surnameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        surnameLabel.setFont(new Font("Arial", Font.PLAIN, 24));

        JLabel passwordLabel = new JLabel("Password : ");
        passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 24));

        confirm = new JButton("Confirm");
        confirm.setFont(new Font("Arial", Font.PLAIN, 24));



        JPanel mainPanel = new JPanel(new GridBagLayout());


        JPanel upperPanel = new JPanel();
        upperPanel.add(addTeacherLabel);

        GridBagConstraints upperPanelConstraints = new GridBagConstraints();


        upperPanelConstraints.gridx = 0;
        upperPanelConstraints.gridy = 0;

        upperPanelConstraints.weighty = 0;
        upperPanelConstraints.weightx = 1;

        upperPanelConstraints.fill = GridBagConstraints.BOTH;

        mainPanel.add(upperPanel, upperPanelConstraints);



        JPanel middlePanel = new JPanel(new GridBagLayout());
        middlePanel.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));

        JPanel middleLeftPanel = new JPanel(new GridLayout(3,1,0,20));

        middleLeftPanel.add(nameLabel);
        middleLeftPanel.add(surnameLabel);
        middleLeftPanel.add(passwordLabel);

        GridBagConstraints middleLeftPanelConstraints = new GridBagConstraints();

        middleLeftPanelConstraints.gridx = 0;
        middleLeftPanelConstraints.gridy = 0;

        middleLeftPanelConstraints.weighty = 1;
        middleLeftPanelConstraints.weightx = 0.3;

        middleLeftPanelConstraints.fill = GridBagConstraints.BOTH;

        middlePanel.add(middleLeftPanel, middleLeftPanelConstraints);


        JPanel middleRightPanel = new JPanel(new GridLayout(3,1,0,20));

        middleRightPanel.add(name);
        middleRightPanel.add(surname);
        middleRightPanel.add(password);

        GridBagConstraints middleRightPanelConstraints = new GridBagConstraints();

        middleRightPanelConstraints.gridx = 1;
        middleRightPanelConstraints.gridy = 0;

        middleRightPanelConstraints.weighty = 1;
        middleRightPanelConstraints.weightx = 0.7;

        middleRightPanelConstraints.fill = GridBagConstraints.BOTH;

        middlePanel.add(middleRightPanel, middleRightPanelConstraints);



        GridBagConstraints middlePanelConstraints = new GridBagConstraints();

        middlePanelConstraints.gridx = 0;
        middlePanelConstraints.gridy = 1;

        middlePanelConstraints.weighty = 1;
        middlePanelConstraints.weightx = 1;

        middlePanelConstraints.fill = GridBagConstraints.BOTH;

        mainPanel.add(middlePanel, middlePanelConstraints);



        JPanel lowerPanel = new JPanel();
        lowerPanel.add(confirm);

        GridBagConstraints lowerPanelConstraints = new GridBagConstraints();

        lowerPanelConstraints.gridx = 0;
        lowerPanelConstraints.gridy = 2;

        lowerPanelConstraints.weighty = 0;
        lowerPanelConstraints.weightx = 1;

        lowerPanelConstraints.fill = GridBagConstraints.BOTH;

        mainPanel.add(lowerPanel, lowerPanelConstraints);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30,40,80,60));
        mainPanel.setBounds(0,0,800,400);

        JLabel errorLabel = new JLabel("");
        errorLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        errorLabel.setForeground(Color.RED);


        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0,0,800,400);
        layeredPane.add(mainPanel,JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(errorLabel,JLayeredPane.PALETTE_LAYER);

        setLayout(null);
        setPreferredSize(new Dimension(800, 400));
        add(layeredPane);

        confirm.addActionListener(l -> {

                    String requestName = name.getText();
                    if(requestName.length()<4){
                        errorLabel.setBounds(120,320,800,50);
                        errorLabel.setText("Teacher's name can not be less than four characters.");
                        return;
                    }
                    else if(requestName.length()>30){
                        errorLabel.setBounds(120,320,800,50);
                        errorLabel.setText("Teacher's name can not be more than thirty characters.");
                        return;
                    }

                    String requestSurname = surname.getText();
                    if(requestSurname.length()<4){
                        errorLabel.setBounds(120,320,800,50);
                        errorLabel.setText("Teacher's surname can not be less than four characters.");
                        return;
                    }
                    else if(requestSurname.length()>30){
                        errorLabel.setBounds(120,320,800,50);
                        errorLabel.setText("Teacher's surname can not be more than thirty characters.");
                        return;
                    }

                    String requestPassword = password.getText();
                    if(requestPassword.length()<4){
                        errorLabel.setBounds(120,320,800,50);
                        errorLabel.setText("Teacher's password can not be less than four characters.");
                        return;
                    }
                    else if(requestPassword.length()>30){
                        errorLabel.setBounds(120,320,800,50);
                        errorLabel.setText("Teacher's password can not be more than thirty characters.");
                        return;
                    }

                    errorLabel.setText("");


                    CreateTeacherRequest createRequest = new CreateTeacherRequest(requestName, requestSurname,requestPassword);
                    Call<AddTeacherResponse> call = secretaryInterface.addTeacher(ApiClient.getToken(),createRequest);

                    call.enqueue(new Callback<AddTeacherResponse>() {

                        @Override
                        public void onResponse(Call<AddTeacherResponse> call, Response<AddTeacherResponse> response) {

                            if (response.isSuccessful()) {
                                String teacherID = response.body().getTeacherID();
                                JOptionPane.showMessageDialog(
                                        (JDialog) SwingUtilities.getWindowAncestor(AddTeacher.this),                      // parent component, null = center of screen
                                        "Teacher Added With ID Of "+teacherID+".",
                                        "Success",             // dialog title
                                        JOptionPane.INFORMATION_MESSAGE // type
                                );
                                teacher = new Teacher(teacherID,requestName,requestSurname);
                                successful = true;
                                ((JDialog) SwingUtilities.getWindowAncestor(AddTeacher.this)).dispose();
                            } else {
                                try {
                                    ApiResponse apiResponse = gson.fromJson(response.errorBody().string(), ApiResponse.class);
                                    JOptionPane.showMessageDialog(
                                            (JDialog) SwingUtilities.getWindowAncestor(AddTeacher.this),
                                            apiResponse.getMessage(),
                                            "Error",
                                            JOptionPane.ERROR_MESSAGE
                                    );
                                } catch (IOException ex) {
                                    JOptionPane.showMessageDialog(
                                            (JDialog) SwingUtilities.getWindowAncestor(AddTeacher.this),
                                            ex.getMessage(),
                                            "Error",
                                            JOptionPane.ERROR_MESSAGE
                                    );
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<AddTeacherResponse> call, Throwable t) {
                            JOptionPane.showMessageDialog(
                                    (JDialog) SwingUtilities.getWindowAncestor(AddTeacher.this),
                                    "Network Error.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }

                    });
                }
        );

    }

    public boolean getSuccessful(){
        return successful;
    }

    public Teacher getTeacher(){
        return teacher;
    }

}
