package views.secretary;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

import client.ApiClient;
import api.SecretaryInterface;
import com.google.gson.Gson;
import models.general.ApiResponse;
import models.general.Course;
import models.general.Student;
import models.general.Teacher;
import models.secretary.response.GetCoursesResponse;
import models.secretary.response.GetStudentsResponse;
import models.secretary.response.GetTeachersResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SecretaryView extends JFrame {

    private JPanel gridp;
    private JLabel title0;
    private JLabel title1;
    private JLabel title2;

    private JPanel ex1;
    private JPanel ex2;
    private JPanel ex3;
    private JPanel ex4;
    private JPanel ex5;
    private JPanel ex6;

    private int completedRequests = 0;

    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Course> courses = new ArrayList<>();
    private ArrayList<Teacher> teachers = new ArrayList<>();

    private DefaultListModel<String> studentModel = new DefaultListModel<>();
    private DefaultListModel<String> courseModel = new DefaultListModel<>();
    private DefaultListModel<String> teacherModel = new DefaultListModel<>();

    private SecretaryInterface secretaryInterface;

    private JComboBox yearSelection;

    private JLabel errorLabel1;
    private JLabel errorLabel2;
    private JLabel errorLabel3;



    public SecretaryView(String title) {

        super(title);

        Gson gson = new Gson();

        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
            UIManager.put("ButtonArc", 999);
        } catch (Exception e) {
            e.printStackTrace();
        }

        secretaryInterface = ApiClient.getClient().create(SecretaryInterface.class);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        gridp = new JPanel();
        gridp.setLayout(new java.awt.GridLayout(2, 3, 10, 10));

        errorLabel1 = new JLabel("");
        errorLabel1.setFont(new Font("Arial", Font.PLAIN, 25));
        errorLabel1.setForeground(Color.WHITE);

        errorLabel2 = new JLabel("");
        errorLabel2.setFont(new Font("Arial", Font.PLAIN, 25));
        errorLabel2.setForeground(Color.WHITE);

        errorLabel3 = new JLabel("");
        errorLabel3.setFont(new Font("Arial", Font.PLAIN, 25));
        errorLabel3.setForeground(Color.WHITE);

        title0 = new JLabel("Students");
        title1 = new JLabel("Courses");
        title2 = new JLabel("Teachers");

        title0.setFont(new Font("Arial", Font.PLAIN, 30));
        title0.setHorizontalAlignment(SwingConstants.CENTER);

        title1.setFont(new Font("Arial", Font.PLAIN, 30));
        title1.setHorizontalAlignment(SwingConstants.CENTER);

        title2.setFont(new Font("Arial", Font.PLAIN, 30));
        title2.setHorizontalAlignment(SwingConstants.CENTER);

        ex1 = new JPanel();
        ex2 = new JPanel();
        ex3 = new JPanel();
        ex4 = new JPanel();
        ex5 = new JPanel();
        ex6 = new JPanel();

        ex4.setBackground(Color.GRAY);
        ex5.setBackground(Color.GRAY);
        ex6.setBackground(Color.GRAY);

        ex4.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        ex4.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        ex5.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        ex5.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        ex6.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        ex6.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));


        JList studentList = new JList<>(studentModel);
        studentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentList.setFont(new Font("Arial", Font.PLAIN, 24));
        studentList.setFixedCellHeight(50);


        JList courseList = new JList<>(courseModel);
        courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        courseList.setFont(new Font("Arial", Font.PLAIN, 24));
        courseList.setFixedCellHeight(50);


        JList teacherList = new JList<>(teacherModel);
        teacherList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        teacherList.setFont(new Font("Arial", Font.PLAIN, 24));
        teacherList.setFixedCellHeight(50);


        ex1.setLayout(new GridBagLayout());

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton addBtn = new JButton("Add");
        addBtn.setFont(new Font("", Font.PLAIN, 24));
        addBtn.setAlignmentX(Component.CENTER_ALIGNMENT); // needed for BoxLayout
        addBtn.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); // fill horizontally

        JButton deleteBtn = new JButton("Delete");
        deleteBtn.setEnabled(false);
        deleteBtn.setFont(new Font("", Font.PLAIN, 24));
        deleteBtn.setAlignmentX(Component.CENTER_ALIGNMENT); // needed for BoxLayout
        deleteBtn.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); // fill horizontally

        JButton editBtn = new JButton("Edit");
        editBtn.setEnabled(false);
        editBtn.setFont(new Font("", Font.PLAIN, 24));
        editBtn.setAlignmentX(Component.CENTER_ALIGNMENT); // needed for BoxLayout
        editBtn.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); // fill horizontally

        // Add action for Add
        addBtn.addActionListener(e -> {

            JDialog popup = new JDialog(this, null, true); // modal

            AddStudent addStudent = new AddStudent(secretaryInterface);
            popup.add(addStudent);
            popup.pack();
            popup.setResizable(false);
            popup.setLocationRelativeTo(null);
            popup.setVisible(true);

            if(addStudent.getSuccesful()){

                Student student = addStudent.getStudent();

                if(student.getEnrollmentYear() == Integer.parseInt(yearSelection.getSelectedItem().toString())){

                    students.add(student);
                    studentModel.addElement(student.getID() + " - " + student.getName() + " " + student.getSurname());

                }

            }

        });


        deleteBtn.addActionListener(e -> {
            int index = studentList.getSelectedIndex();
            Student student = students.get(index);
            String studentID = student.getID();

            Call<ApiResponse> deleteCall = secretaryInterface.deleteStudent(ApiClient.getToken(), studentID);
            deleteCall.enqueue(new Callback<ApiResponse>(){

                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                    if(response.isSuccessful()) {
                        students.remove(index);
                        studentModel.remove(index);

                        JOptionPane.showMessageDialog(
                                null,                      // parent component, null = center of screen
                                "Student Deleted.",    // message text
                                "Success",             // dialog title
                                JOptionPane.INFORMATION_MESSAGE // type
                        );
                    }
                    else{
                        try {
                            ApiResponse apiResponse = gson.fromJson(response.errorBody().string(), ApiResponse.class);
                            JOptionPane.showMessageDialog(
                                    null,
                                    apiResponse.getMessage(),
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    ex.getMessage(),
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable throwable) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Network Error",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            });

        });

        // Edit last selected
        editBtn.addActionListener(e -> {
            JDialog popup = new JDialog(this, null, true); // modal

            int index = studentList.getSelectedIndex();
            Student student = students.get(index);


            EditStudent editStudent = new EditStudent(secretaryInterface,student);
            popup.add(editStudent);
            popup.pack();
            popup.setResizable(false);
            popup.setLocationRelativeTo(null);
            popup.setVisible(true);

            if(editStudent.getSuccessful()){

                Student resStudent = editStudent.getResStudent();
                students.set(index,resStudent);
                studentModel.set(index,resStudent.getID() + " - " + resStudent.getName() + " " + resStudent.getSurname());

            }

        });

        controlPanel.add(addBtn);
        controlPanel.add(editBtn);
        controlPanel.add(deleteBtn);



        studentList.addListSelectionListener(e -> {
            if (!studentList.isSelectionEmpty()) {
                courseList.clearSelection();
                teacherList.clearSelection();

                deleteBtn.setEnabled(true);
                editBtn.setEnabled(true);
            } else {
                deleteBtn.setEnabled(false);
                editBtn.setEnabled(false);
            }
        });

        studentList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                Border lineBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(78, 80, 82));
                Border leftBorder = BorderFactory.createEmptyBorder(0, 10, 0, 0);
                label.setBorder(new CompoundBorder(lineBorder, leftBorder)); // bottom border

                return label;
            }
        });

        JScrollPane scrollPane = new JScrollPane(studentList);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(600, 800)); // Force visible area


        JPanel yearSelectionPanel = new JPanel(new GridLayout(1, 1, 10, 0));
        yearSelectionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));



        int currentYear = Year.now().getValue();
        int yearSelections = currentYear - 2000 + 1;
        String[] years = new String[yearSelections];

        for (int i = currentYear; i >= 2000; i--) {
            years[currentYear - i] = Integer.toString(i);
        }

        yearSelection = new JComboBox(years);
        yearSelection.setFont(new Font("Arial", Font.PLAIN, 24));

        yearSelection.addItemListener(new ItemListener() {

              @Override
              public void itemStateChanged(ItemEvent e) {
                  if(e.getStateChange() == ItemEvent.SELECTED) {

                      Call<GetStudentsResponse> getStudentsCall = secretaryInterface.getStudents(ApiClient.getToken(),Integer.parseInt(yearSelection.getSelectedItem().toString()));
                      getStudentsCall.enqueue(new Callback<GetStudentsResponse>() {

                          @Override
                          public void onResponse(Call<GetStudentsResponse> call, Response<GetStudentsResponse> response) {

                              if(response.isSuccessful()) {
                                  errorLabel1.setText("");

                                  GetStudentsResponse getStudentsResponse = response.body();

                                  studentModel.clear();

                                  students = getStudentsResponse.getStudents();

                                  for(int i = 0; i < students.size(); i++) {
                                      Student student = students.get(i);

                                      String studentLabel = student.getID() + " - " + student.getName() + " " + student.getSurname();

                                      studentModel.add(i, studentLabel);
                                  }

                              }
                              else{
                                  studentModel.clear();
                                  errorLabel1.setBounds(125,425,600,50);
                                  errorLabel1.setText("No Students Found.");
                              }

                          }

                          @Override
                          public void onFailure(Call<GetStudentsResponse> call, Throwable throwable) {
                              errorLabel1.setBounds(150,425,600,50);
                              errorLabel1.setText("Network Error.");

                          }
                      });

                  }
              }

        });

        yearSelectionPanel.add(yearSelection, BorderLayout.WEST);


        GridBagConstraints yearSelectionPanelConstraints = new GridBagConstraints();

        //Grid Bag Constraint For Year Selections Panel
        //We need it to be 20% of the total panel height
        yearSelectionPanelConstraints.gridx = 0; //Column 1

        yearSelectionPanelConstraints.gridy = 0; //Row 1
        yearSelectionPanelConstraints.weighty = 0; //20% weight
        yearSelectionPanelConstraints.weightx = 1;

        yearSelectionPanelConstraints.fill = GridBagConstraints.BOTH;


        GridBagConstraints scrollPane1Constraints = new GridBagConstraints();

        //Grid Bag Constraint for scroll pane
        //We need it to be 60% of the total panel height

        scrollPane1Constraints.gridx = 0;

        scrollPane1Constraints.gridy = 1;
        scrollPane1Constraints.weighty = 1;
        scrollPane1Constraints.weightx = 1;

        scrollPane1Constraints.fill = GridBagConstraints.BOTH;


        GridBagConstraints controlPanelConstraints = new GridBagConstraints();

        controlPanelConstraints.gridx = 0;

        controlPanelConstraints.gridy = 2;
        controlPanelConstraints.weighty = 0;
        controlPanelConstraints.weightx = 1;

        controlPanelConstraints.fill = GridBagConstraints.BOTH;

        ex1.add(scrollPane, scrollPane1Constraints);
        ex1.add(yearSelectionPanel, yearSelectionPanelConstraints);
        ex1.add(controlPanel, controlPanelConstraints);



        ex2.setLayout(new GridBagLayout());
        ex2.setBorder(BorderFactory.createEmptyBorder(10, 0, 3, 0));

        // Create scrollable list panel
        JPanel listPanel2 = new JPanel();
        listPanel2.setLayout(new BoxLayout(listPanel2, BoxLayout.Y_AXIS));
        listPanel2.setOpaque(false); // For transparency if desired

        JPanel controlPanel2 = new JPanel();
        controlPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton addBtn2 = new JButton("Add");
        addBtn2.setFont(new Font("", Font.PLAIN, 24));
        addBtn2.setAlignmentX(Component.CENTER_ALIGNMENT); // needed for BoxLayout
        addBtn2.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); // fill horizontally

        JButton deleteBtn2 = new JButton("Delete");
        deleteBtn2.setEnabled(false);
        deleteBtn2.setFont(new Font("", Font.PLAIN, 24));
        deleteBtn2.setAlignmentX(Component.CENTER_ALIGNMENT); // needed for BoxLayout
        deleteBtn2.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); // fill horizontally

        JButton editBtn2 = new JButton("Edit");
        editBtn2.setEnabled(false);
        editBtn2.setFont(new Font("", Font.PLAIN, 24));
        editBtn2.setAlignmentX(Component.CENTER_ALIGNMENT); // needed for BoxLayout
        editBtn2.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); // fill horizontally

        // Add action for Add
        addBtn2.addActionListener(e -> {
            JDialog popup = new JDialog(this, null, true); // modal

            AddCourse addCourse = new AddCourse(secretaryInterface);

            popup.add(addCourse);
            popup.pack();
            popup.setLocationRelativeTo(null);
            popup.setResizable(false);
            popup.setVisible(true);

            if(addCourse.getSuccessful()){

                Course course = addCourse.getCourse();

                courses.add(course);
                courseModel.addElement(course.getID() + " - " + course.getName());

            }

        });

        // Delete last selected
        deleteBtn2.addActionListener(e -> {
            int index = courseList.getSelectedIndex();
            Course course = courses.get(index);
            String courseID = course.getID();

            Call<ApiResponse> deleteCall = secretaryInterface.deleteCourse(ApiClient.getToken(), courseID);
            deleteCall.enqueue(new Callback<ApiResponse>(){

                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                    if(response.isSuccessful()) {
                        courses.remove(index);
                        courseModel.remove(index);

                        JOptionPane.showMessageDialog(
                                null,                      // parent component, null = center of screen
                                "Course Deleted.",    // message text
                                "Success",             // dialog title
                                JOptionPane.INFORMATION_MESSAGE // type
                        );
                    }
                    else{
                        try {
                            ApiResponse apiResponse = gson.fromJson(response.errorBody().string(), ApiResponse.class);
                            JOptionPane.showMessageDialog(
                                    null,
                                    apiResponse.getMessage(),
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    ex.getMessage(),
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable throwable) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Network Error",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            });

        });

        // Edit last selected
        editBtn2.addActionListener(e -> {
            JDialog popup = new JDialog(this, null, true); // modal

            int index = courseList.getSelectedIndex();
            Course course = courses.get(index);

            EditCourse editCourse = new EditCourse(secretaryInterface,course,teachers);
            popup.add(editCourse);
            popup.pack();
            popup.setLocationRelativeTo(null);
            popup.setResizable(false);
            popup.setVisible(true);

            if(editCourse.getSuccessful()){

                Course resCourse = editCourse.getResCourse();
                courses.set(index,resCourse);
                courseModel.set(index,resCourse.getID() + " - " + resCourse.getName());

            }

        });

        controlPanel2.add(addBtn2);
        controlPanel2.add(editBtn2);
        controlPanel2.add(deleteBtn2);


        courseList.addListSelectionListener(e -> {
            if (!courseList.isSelectionEmpty()) {
                studentList.clearSelection();
                teacherList.clearSelection();

                deleteBtn2.setEnabled(true);
                editBtn2.setEnabled(true);
            } else {
                deleteBtn2.setEnabled(false);
                editBtn2.setEnabled(false);
            }
        });

        courseList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                Border lineBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(78, 80, 82));
                Border leftBorder = BorderFactory.createEmptyBorder(0, 10, 0, 0);
                label.setBorder(new CompoundBorder(lineBorder, leftBorder)); // bottom border

                return label;
            }
        });


        // Put listPanel inside a scroll pane
        JScrollPane scrollPane2 = new JScrollPane(courseList);
        scrollPane2.setOpaque(false);
        scrollPane2.getViewport().setOpaque(false);
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane2.setPreferredSize(new Dimension(600, 800)); // Force visible area


        GridBagConstraints controlPanel2Constraints = new GridBagConstraints();

        controlPanel2Constraints.gridx = 0;
        controlPanel2Constraints.gridy = 1;
        controlPanel2Constraints.weighty = 0;
        controlPanel2Constraints.weightx = 1;

        controlPanel2Constraints.fill = GridBagConstraints.BOTH;


        GridBagConstraints scrollPane2Constraints = new GridBagConstraints();

        scrollPane2Constraints.gridx = 0;
        scrollPane2Constraints.gridy = 0;
        scrollPane2Constraints.weighty = 1;
        scrollPane2Constraints.weightx = 1;
        scrollPane2Constraints.insets = new Insets(0, 0, 10, 0);

        scrollPane2Constraints.fill = GridBagConstraints.BOTH;

        // Add to ex2
        ex2.add(scrollPane2, scrollPane2Constraints);
        ex2.add(controlPanel2, controlPanel2Constraints);


        ex3.setLayout(new GridBagLayout());
        ex3.setBorder(BorderFactory.createEmptyBorder(10, 0, 3, 0));

        JPanel controlPanel3 = new JPanel();
        controlPanel3.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton addBtn3 = new JButton("Add");
        addBtn3.setFont(new Font("", Font.PLAIN, 24));
        addBtn3.setAlignmentX(Component.CENTER_ALIGNMENT); // needed for BoxLayout
        addBtn3.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); // fill horizontally

        JButton deleteBtn3 = new JButton("Delete");
        deleteBtn3.setEnabled(false);
        deleteBtn3.setFont(new Font("", Font.PLAIN, 24));
        deleteBtn3.setAlignmentX(Component.CENTER_ALIGNMENT); // needed for BoxLayout
        deleteBtn3.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); // fill horizontally

        JButton editBtn3 = new JButton("Edit");
        editBtn3.setEnabled(false);
        editBtn3.setFont(new Font("", Font.PLAIN, 24));
        editBtn3.setAlignmentX(Component.CENTER_ALIGNMENT); // needed for BoxLayout
        editBtn3.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); // fill horizontally

        // Add action for Add
        addBtn3.addActionListener(e -> {
            JDialog popup = new JDialog(this, null, true); // modal


            AddTeacher addTeacher = new AddTeacher(secretaryInterface);

            popup.add(addTeacher);
            popup.pack();
            popup.setResizable(false);
            popup.setLocationRelativeTo(this);
            popup.setVisible(true);

            if(addTeacher.getSuccessful()){

                Teacher teacher = addTeacher.getTeacher();

                teachers.add(teacher);
                teacherModel.addElement(teacher.getID() + " - " + teacher.getName() + " " + teacher.getSurname());

            }

        });

        // Delete last selected
        deleteBtn3.addActionListener(e -> {
            int index = teacherList.getSelectedIndex();
            Teacher teacher = teachers.get(index);
            String teacherID = teacher.getID();

            Call<ApiResponse> deleteCall = secretaryInterface.deleteTeacher(ApiClient.getToken(), teacherID);
            deleteCall.enqueue(new Callback<ApiResponse>(){

                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                    if(response.isSuccessful()) {
                        teachers.remove(index);
                        teacherModel.remove(index);

                        JOptionPane.showMessageDialog(
                                null,                      // parent component, null = center of screen
                                "Teacher Deleted.",    // message text
                                "Success",             // dialog title
                                JOptionPane.INFORMATION_MESSAGE // type
                        );
                    }
                    else{
                        try {
                            ApiResponse apiResponse = gson.fromJson(response.errorBody().string(), ApiResponse.class);
                            System.out.println(teacherID);
                            JOptionPane.showMessageDialog(
                                    null,
                                    "ExternalException",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Internal Exception.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable throwable) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Network Error",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            });

        });

        // Edit last selected
        editBtn3.addActionListener(e -> {

            JDialog popup = new JDialog(this, null, true); // modal

            int index = teacherList.getSelectedIndex();
            Teacher teacher = teachers.get(index);

            EditTeacher editTeacher = new EditTeacher(secretaryInterface,teacher);
            popup.add(editTeacher);
            popup.pack();
            popup.setResizable(false);
            popup.setLocationRelativeTo(null);
            popup.setVisible(true);

            if(editTeacher.getSuccessful()){

                Teacher resTeacher = editTeacher.getResTeacher();
                teachers.set(index,resTeacher);
                teacherModel.set(index,resTeacher.getID() + " - " + resTeacher.getName() + " " + resTeacher.getSurname());

            }

        });

        controlPanel3.add(addBtn3);
        controlPanel3.add(editBtn3);
        controlPanel3.add(deleteBtn3);


        teacherList.addListSelectionListener(e -> {
            if (!teacherList.isSelectionEmpty()) {
                studentList.clearSelection();
                courseList.clearSelection();

                deleteBtn3.setEnabled(true);
                editBtn3.setEnabled(true);
            } else {
                deleteBtn3.setEnabled(false);
                editBtn3.setEnabled(false);
            }
        });

        teacherList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                Border lineBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(78, 80, 82));
                Border leftBorder = BorderFactory.createEmptyBorder(0, 10, 0, 0);
                label.setBorder(new CompoundBorder(lineBorder, leftBorder)); // bottom border

                return label;
            }
        });

        JPanel refreshButtonPanel = new JPanel(new GridLayout(1, 1, 10, 0));
        refreshButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setFont(new Font("", Font.PLAIN, 24));
        refreshButton.addActionListener(e -> {
            load();
        });

        refreshButtonPanel.add(refreshButton);


        GridBagConstraints refreshButtonPanelConstraints = new GridBagConstraints();

        refreshButtonPanelConstraints.gridx = 0;
        refreshButtonPanelConstraints.gridy = 0;
        refreshButtonPanelConstraints.weighty = 0;
        refreshButtonPanelConstraints.weightx = 1;

        refreshButtonPanelConstraints.fill = GridBagConstraints.BOTH;


        JScrollPane scrollPane3 = new JScrollPane(teacherList);
        scrollPane3.setOpaque(false);
        scrollPane3.getViewport().setOpaque(false);
        scrollPane3.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        scrollPane3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane3.setPreferredSize(new Dimension(600, 800)); // Force visible area

        GridBagConstraints controlPanel3Constraints = new GridBagConstraints();

        controlPanel3Constraints.gridx = 0;
        controlPanel3Constraints.gridy = 2;
        controlPanel3Constraints.weighty = 0;
        controlPanel3Constraints.weightx = 1;

        controlPanel3Constraints.fill = GridBagConstraints.BOTH;

        GridBagConstraints scrollPane3Constraints = new GridBagConstraints();

        scrollPane3Constraints.gridx = 0;
        scrollPane3Constraints.gridy = 1;
        scrollPane3Constraints.weighty = 1;
        scrollPane3Constraints.weightx = 1;
        scrollPane3Constraints.insets = new Insets(0, 0, 10, 0);

        scrollPane3Constraints.fill = GridBagConstraints.BOTH;

        // Add to ex3
        ex3.add(refreshButtonPanel, refreshButtonPanelConstraints);
        ex3.add(scrollPane3, scrollPane3Constraints);
        ex3.add(controlPanel3, controlPanel3Constraints);


        // Main container
        gridp = new JPanel();
        gridp.setLayout(new BorderLayout());

        // --- Top row (titles) ---
        JPanel titleRow = new JPanel(new GridLayout(1, 3, 10, 0));
        titleRow.setBackground(new Color(80, 80, 80)); // light gray
        titleRow.add(title0);
        titleRow.add(title1);
        titleRow.add(title2);
        titleRow.setPreferredSize(new Dimension(0, 60)); // control height of title row

        // --- Bottom row (content panels) ---
        JPanel panelRow = new JPanel(new GridLayout(1, 3, 10, 0));
        panelRow.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelRow.add(ex1);
        panelRow.add(ex2);
        panelRow.add(ex3);

        // Add rows to main gridp
        gridp.add(titleRow, BorderLayout.NORTH);
        gridp.add(panelRow, BorderLayout.CENTER);
        gridp.setBounds(0,0,1400,900);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1400, 900));
        layeredPane.add(gridp,JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(errorLabel1,JLayeredPane.PALETTE_LAYER);
        layeredPane.add(errorLabel2,JLayeredPane.MODAL_LAYER);
        layeredPane.add(errorLabel3,JLayeredPane.POPUP_LAYER);

        add(layeredPane, BorderLayout.CENTER);

        setExtendedState(JFrame.NORMAL);
        setSize(1400,900);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);



        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!studentList.getBounds().contains(e.getPoint()) && !courseList.getBounds().contains(e.getPoint()) && !teacherList.getBounds().contains(e.getPoint())) {
                    studentList.clearSelection();
                    courseList.clearSelection();
                    teacherList.clearSelection();
                }
            }
        });


        load();



    }

    private synchronized void completedRequest(boolean flag){
        if(flag){
            completedRequests++;
        }
        else{
            completedRequests--;
        }

        if(completedRequests == 3){
            SwingUtilities.invokeLater(() -> setVisible(true));
            completedRequests=0;
        }else if(completedRequests == -3){
            JOptionPane.showMessageDialog(
                    (JFrame) SwingUtilities.getWindowAncestor(this),
                    "Network Error.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            System.exit(0);
        }

    }

    private void load(){
        Call<GetStudentsResponse> getStudentsCall = secretaryInterface.getStudents(ApiClient.getToken(),Integer.parseInt(yearSelection.getSelectedItem().toString()));
        getStudentsCall.enqueue(new Callback<GetStudentsResponse>() {

            @Override
            public void onResponse(Call<GetStudentsResponse> call, Response<GetStudentsResponse> response) {

                if(response.isSuccessful()) {
                    GetStudentsResponse getStudentsResponse = response.body();

                    errorLabel1.setText("");
                    studentModel.clear();

                    students = getStudentsResponse.getStudents();

                    for(int i = 0; i < students.size(); i++) {
                        Student student = students.get(i);

                        String studentLabel = student.getID() + " - " + student.getName() + " " + student.getSurname();

                        studentModel.add(i, studentLabel);
                    }

                    completedRequest(true);

                }
                else{
                    errorLabel1.setBounds(125,425,600,50);
                    errorLabel1.setText("No Students Found.");

                    completedRequest(true);

                }

            }

            @Override
            public void onFailure(Call<GetStudentsResponse> call, Throwable throwable) {
                studentModel.clear();
                errorLabel1.setBounds(150,425,600,50);
                errorLabel1.setText("Network Error.");

                completedRequest(false);

            }
        });

        Call<GetCoursesResponse> getCoursesCall = secretaryInterface.getCourses(ApiClient.getToken());
        getCoursesCall.enqueue(new Callback<GetCoursesResponse>() {

            @Override
            public void onResponse(Call<GetCoursesResponse> call, Response<GetCoursesResponse> response) {

                if(response.isSuccessful()) {
                    GetCoursesResponse getCoursesResponse = response.body();

                    errorLabel2.setText("");
                    courseModel.clear();

                    courses = getCoursesResponse.getCourses();

                    for(int i = 0; i < courses.size(); i++) {
                        Course course = courses.get(i);

                        String courseLabel = course.getID() + " - " + course.getName();

                        courseModel.add(i, courseLabel);
                    }

                    completedRequest(true);

                }
                else{
                    courseModel.clear();
                    errorLabel2.setBounds(585,425,600,50);
                    errorLabel2.setText("No Courses Found.");

                    completedRequest(true);

                }

            }

            @Override
            public void onFailure(Call<GetCoursesResponse> call, Throwable throwable) {
                errorLabel2.setBounds(600,425,600,50);
                errorLabel2.setText("Network Error.");

                completedRequest(false);
            }
        });

        Call<GetTeachersResponse> getTeachersCall = secretaryInterface.getTeachers(ApiClient.getToken());
        getTeachersCall.enqueue(new Callback<GetTeachersResponse>() {

            @Override
            public void onResponse(Call<GetTeachersResponse> call, Response<GetTeachersResponse> response) {

                if(response.isSuccessful()) {
                    GetTeachersResponse getTeachersResponse = response.body();

                    errorLabel3.setText("");
                    teacherModel.clear();

                    teachers = getTeachersResponse.getTeachers();

                    for(int i = 0; i < teachers.size(); i++) {
                        Teacher teacher = teachers.get(i);

                        String teacherLabel = teacher.getID() + " - " + teacher.getName() + " " + teacher.getSurname();

                        teacherModel.add(i, teacherLabel);
                    }

                    completedRequest(true);

                }
                else{
                    teacherModel.clear();
                    errorLabel3.setBounds(1050,425,600,50);
                    errorLabel3.setText("No Teachers Found.");

                    completedRequest(true);

                }

            }

            @Override
            public void onFailure(Call<GetTeachersResponse> call, Throwable throwable) {
                errorLabel3.setBounds(1100,425,600,50);
                errorLabel3.setText("Network Error.");

                completedRequest(false);
            }


        });
    }

}
