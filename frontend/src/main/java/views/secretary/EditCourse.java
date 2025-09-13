package views.secretary;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import api.SecretaryInterface;
import models.general.ApiResponse;
import client.ApiClient;

import com.google.gson.Gson;
import models.general.Course;
import models.general.Teacher;
import models.secretary.request.EditCourseRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EditCourse extends JPanel {

    private JTextField name;
    private JComboBox semester;
    private JComboBox teacherList;

    private JButton confirm;

    private boolean successful = false;
    private Course resCourse;

    public EditCourse(SecretaryInterface secretaryInterface, Course course, ArrayList<Teacher> teachers) {

        Gson gson = new Gson();

        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
        } catch (Exception e) {
            e.printStackTrace();
        }

        name = new JTextField(20);
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setText(course.getName());

        String[] semesters = {"1","2","3","4","5","6","7","8","9","10","11","12"};
        semester = new JComboBox(semesters);
        semester.setFont(new Font("Arial", Font.PLAIN, 20));
        semester.setSelectedIndex(course.getSemester()-1);


        ArrayList<String> teacherNames = new ArrayList<>();
        teacherNames.add("None");
        for(int i=0;i<teachers.size();i++){
            Teacher teacher = teachers.get(i);
            teacherNames.add(teacher.getID() + " - " + teacher.getName() + " " + teachers.get(i).getSurname());
        }

        teacherList = new JComboBox(teacherNames.toArray());
        teacherList.setFont(new Font("Arial", Font.PLAIN, 20));

        String assignedTeacherID = course.getTeacherID();
        if(assignedTeacherID == null){
            teacherList.setSelectedIndex(0);
        }
        else {
            for (int i = 0; i < teachers.size(); i++) {
                if (teachers.get(i).getID().equals(assignedTeacherID)) {
                    teacherList.setSelectedIndex(i+1);
                }
            }
        }

        JLabel editCourseLabel = new JLabel("Edit Course");
        editCourseLabel.setFont(new Font("Arial", Font.PLAIN, 34));

        JLabel nameLabel = new JLabel("Name : ");
        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 24));

        JLabel assignTeacherLabel = new JLabel("Assign Teacher : ");
        assignTeacherLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        assignTeacherLabel.setFont(new Font("Arial", Font.PLAIN, 24));

        JLabel semesterLabel = new JLabel("Semester : ");
        semesterLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        semesterLabel.setFont(new Font("Arial", Font.PLAIN, 24));

        confirm = new JButton("Confirm");
        confirm.setFont(new Font("Arial", Font.PLAIN, 24));


        JPanel mainPanel = new JPanel(new GridBagLayout());


        JPanel upperPanel = new JPanel();
        upperPanel.add(editCourseLabel);

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
        middleLeftPanel.add(assignTeacherLabel);
        middleLeftPanel.add(semesterLabel);


        GridBagConstraints middleLeftPanelConstraints = new GridBagConstraints();

        middleLeftPanelConstraints.gridx = 0;
        middleLeftPanelConstraints.gridy = 0;

        middleLeftPanelConstraints.weighty = 1;
        middleLeftPanelConstraints.weightx = 0.5;

        middleLeftPanelConstraints.fill = GridBagConstraints.BOTH;

        middlePanel.add(middleLeftPanel, middleLeftPanelConstraints);


        JPanel middleRightPanel = new JPanel(new GridLayout(3,1,0,20));


        middleRightPanel.add(name);
        middleRightPanel.add(teacherList);
        middleRightPanel.add(semester);


        GridBagConstraints middleRightPanelConstraints = new GridBagConstraints();

        middleRightPanelConstraints.gridx = 1;
        middleRightPanelConstraints.gridy = 0;

        middleRightPanelConstraints.weighty = 1;
        middleRightPanelConstraints.weightx = 0.5;

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
                errorLabel.setText("Course's name can not be less than four characters.");
                return;
            }
            else if(requestName.length()>30){
                errorLabel.setBounds(120,320,800,50);
                errorLabel.setText("Course's name can not be more than thirty characters.");
                return;
            }

            int teacherIndex = teacherList.getSelectedIndex();
            String teacherID;
            String teacherName;
            String teacherSurname;

            if(teacherIndex==0){
                teacherID = "NULL";
                teacherName = null;
                teacherSurname = null;
            }
            else{
                Teacher teacher = teachers.get(teacherIndex-1);
                teacherID = teacher.getID();
                teacherName = teacher.getName();
                teacherSurname = teacher.getSurname();
            }


            int requestSemester = Integer.parseInt((String)semester.getSelectedItem());

            errorLabel.setText("");

            EditCourseRequest editRequest = new EditCourseRequest(requestName, requestSemester,teacherID);
            Call<ApiResponse> call = secretaryInterface.editCourse(ApiClient.getToken(),course.getID(),editRequest);

            call.enqueue(new Callback<ApiResponse>() {

                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                    if (response.isSuccessful()) {
                        JOptionPane.showMessageDialog(
                                (JDialog) SwingUtilities.getWindowAncestor(EditCourse.this),
                                "Course Edited.",    // message text
                                "Success",             // dialog title
                                JOptionPane.INFORMATION_MESSAGE // type
                        );
                        resCourse = new Course(course.getID(),requestName,requestSemester,teacherID, teacherName, teacherSurname);
                        successful = true;
                        ((JDialog) SwingUtilities.getWindowAncestor(EditCourse.this)).dispose();
                    } else {
                        try {
                            ApiResponse apiResponse = gson.fromJson(response.errorBody().string(), ApiResponse.class);
                            JOptionPane.showMessageDialog(
                                    (JDialog) SwingUtilities.getWindowAncestor(EditCourse.this),
                                    apiResponse.getMessage(),
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(
                                    (JDialog) SwingUtilities.getWindowAncestor(EditCourse.this),
                                    ex.getMessage(),
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }

                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    JOptionPane.showMessageDialog(
                            (JDialog) SwingUtilities.getWindowAncestor(EditCourse.this),
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

    public Course getResCourse(){
        return resCourse;
    }

}
