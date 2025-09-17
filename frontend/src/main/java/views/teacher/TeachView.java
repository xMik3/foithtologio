package views.teacher;

import api.TeacherInterface;
import client.ApiClient;
import com.google.gson.Gson;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import models.general.ApiResponse;
import models.general.Course;
import models.teacher.ManagedCourse;
import models.teacher.ManagedStudent;
import models.teacher.request.GradeRequest;
import models.teacher.response.GetManagedCoursesResponse;
import models.teacher.response.GetManagedStudentsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeachView extends JFrame {
        
         
        private JLabel title0;
        private JLabel title1;
        private JLabel title2;
        JLabel uname;
        JLabel usurname;
        JLabel smstr;
        JLabel nm;
        JLabel id;
        JLabel errorLabel;
        JLabel gradeL;
        
        int curind1;
        int curind2;
        
        private JPanel p;
        private JPanel titleRow;
        private JPanel titlep1;
        private JPanel titlep2;
        private JPanel titlep3;
        
        private JPanel bodyRow;
        private JPanel bodyp1;
        private JPanel bodyp2;
        private JPanel bodyp3;
        
        private JPanel refreshp;
        private JPanel gradep;
        private JPanel infop;
        
        private JButton refresh;
        private JButton grade;
        
        double doublegrade;
        boolean isRefreshing = false;
        
        DefaultListModel<String> crsesName;
        JList crsesName2;
        ArrayList<ManagedCourse> crses;
        
        DefaultListModel<String> stuName;
        JList stuName2;
        ArrayList<ManagedStudent> stu;
        TeacherInterface teacherInterface;
        Gson gson;

        public TeachView(String title)
        {

            super(title);

            try {
                UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
            } catch (Exception e) {
                e.printStackTrace();
            }

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             teacherInterface = ApiClient.getClient().create(TeacherInterface.class);
            setLayout(new BorderLayout());
            errorLabel = new JLabel();
            gson = new Gson();
            
            
            crses = new ArrayList<>();
            crsesName = new DefaultListModel<>();
            crsesName2 = new JList(crsesName);
            crsesName2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            
            stu = new ArrayList<>();
            stuName = new DefaultListModel<>();
            stuName2 = new JList(stuName);
            stuName2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            
            load();
            
            p = new JPanel();
            p.setLayout(new BorderLayout());
            
            title0 = new JLabel("Students");
            title1 = new JLabel("Courses");
            title2 = new JLabel("Description");
            
            title0.setFont(new Font("Arial", Font.PLAIN, 30));
            title1.setFont(new Font("Arial", Font.PLAIN, 30));
            title2.setFont(new Font("Arial", Font.PLAIN, 30));
            
            titleRow = new JPanel(new GridLayout(1,3));
            
            bodyRow = new JPanel(new GridLayout(1,3));
            
            titlep1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
            titlep1.setBackground(new Color(80, 80, 80));
            titlep1.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            
            titlep2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
            titlep2.setBackground(new Color(80, 80, 80));
            titlep2.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            
            titlep3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
            titlep3.setBackground(new Color(80, 80, 80));
            titlep3.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            
            bodyp1 = new JPanel(new BorderLayout());
            bodyp1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            bodyp1.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            
            bodyp2 = new JPanel(new BorderLayout());
            bodyp2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            bodyp2.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            
            bodyp3 = new JPanel(new BorderLayout());
            bodyp3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            bodyp3.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            
            refreshp = new JPanel(new BorderLayout());
            refreshp.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            
            gradep = new JPanel(new BorderLayout());
            gradep.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            
            infop = new JPanel();
            infop.setLayout(new BoxLayout(infop,BoxLayout.Y_AXIS));
            infop.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            infop.setBackground(new Color(80, 80, 80));
            
            grade = new JButton("Grade");
            grade.setFont(new Font("", Font.PLAIN, 24));
            
            refresh = new JButton("Refresh");
            refresh.setFont(new Font("", Font.PLAIN, 24));
            
            nm = new JLabel("1323");
            nm.setFont(new Font("Arial", Font.PLAIN, 20));
            
            gradeL = new JLabel("1323");
            gradeL.setFont(new Font("Arial", Font.PLAIN, 20));
            
            id = new JLabel("322332");
            id.setFont(new Font("Arial", Font.PLAIN, 20));
            
            uname = new JLabel("2343423");
            uname.setFont(new Font("Arial", Font.PLAIN, 20));
            
            usurname = new JLabel("4232343");
            usurname.setFont(new Font("Arial", Font.PLAIN, 20));
            
            smstr = new JLabel("2434343");
            smstr.setFont(new Font("Arial", Font.PLAIN, 20));
            
            // Put listPanel inside a scroll pane
            JScrollPane scrollPane = new JScrollPane(crsesName2);
            scrollPane.setOpaque(false);
            scrollPane.getViewport().setOpaque(false);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setBackground(new Color(80, 80, 80));
            scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


            

            JScrollPane scrollPane2 = new JScrollPane(stuName2);
            scrollPane2.setOpaque(false);
            scrollPane2.getViewport().setOpaque(false);
            scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane2.setBackground(new Color(80, 80, 80));
            scrollPane2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            
            titlep1.add(title0);
            titlep2.add(title1);
            titlep3.add(title2);
            
            titleRow.add(titlep1);
            titleRow.add(titlep2);
            titleRow.add(titlep3);
            
            refreshp.add(refresh);
            gradep.add(grade);
            
            infop.add(nm);
            infop.add(Box.createRigidArea(new Dimension(0, 20)));
            infop.add(id);
            infop.add(Box.createRigidArea(new Dimension(0, 20)));
            infop.add(smstr);
            infop.add(Box.createRigidArea(new Dimension(0, 20)));
            infop.add(uname);
            
            bodyp1.add(scrollPane);
            bodyp2.add(scrollPane2);
            bodyp3.add(refreshp,BorderLayout.NORTH);
            bodyp3.add(infop,BorderLayout.CENTER);
            bodyp3.add(gradep,BorderLayout.SOUTH);
            bodyRow.add(bodyp1);
            bodyRow.add(bodyp2);
            bodyRow.add(bodyp3);
            p.add(titleRow,BorderLayout.NORTH);
            p.add(bodyRow,BorderLayout.CENTER);
            
            setSize(1200,700);
            setLocationRelativeTo(null); // center on screen
            setVisible(true);
            p.setVisible(true);
            add(p);
            
            crsesName2.addListSelectionListener
                (
                    l -> 
                    {
                        if (!l.getValueIsAdjusting() && !isRefreshing) {
                        
                        curind1 = crsesName2.getSelectedIndex();
                        ManagedCourse course = crses.get(curind1);
                        id.setText("Course's ID:" + course.getID());
                        nm.setText("Course's Name:" + course.getName());
                        smstr.setText("Course's Semester:" + course.getSemester());
                        usurname.setVisible(false);
                        System.out.println(curind1);
                        
                        String cid = course.getID();
                        Call<GetManagedStudentsResponse> call = teacherInterface.getManagedStudents(ApiClient.getToken(),cid);
            
            call.enqueue(new Callback<GetManagedStudentsResponse>() {

            @Override
            public void onResponse(Call<GetManagedStudentsResponse> call, Response<GetManagedStudentsResponse> response) {
                if (response.isSuccessful()) {
                        GetManagedStudentsResponse getstuResponse = response.body();
                        
                        stuName.clear();
                        
                        stu = getstuResponse.getStudents();
                        for(int i=0;i<stu.size();i++){
                        ManagedStudent student = stu.get(i);
                        String label = student.getID();
                        String name = student.getNAME();
                        String surname = student.getSURNAME();
                        stuName.add(i,label + "-" + name); 
                        
            
            
        }
                        isRefreshing = false;

                } else {
                        errorLabel.setBounds(500,550,600,50);
                            try {
                                 ApiResponse loginResponse = gson.fromJson(response.errorBody().string(), ApiResponse.class);
                                 errorLabel.setText("*"+ loginResponse.getMessage());
                            } catch (IOException ex) {
                                 errorLabel.setText("*"+ex.getMessage());
                                    }
                            isRefreshing = false;
                            }

                        }

                            @Override
                            public void onFailure(Call<GetManagedStudentsResponse> call, Throwable t) {
                                errorLabel.setText("*Something went wrong. Please try again.");
                                isRefreshing = false;
                            }

                        });
                    }}
                );
            
            
            
            
            stuName2.addListSelectionListener
                (
                    l -> 
                    {
                        if (!l.getValueIsAdjusting() && !isRefreshing) {
                        
                        curind2 = stuName2.getSelectedIndex();
                        ManagedStudent student = stu.get(curind2);
                        id.setText("Student's ID:" + student.getID());
                        nm.setText("Student's Name:" + student.getNAME());
                        smstr.setText("Course's Surname:" + student.getSURNAME());
                        usurname.setText("Student's semester:" + student.getSURNAME());
                        usurname.setVisible(true);
                        System.out.println(curind2);
                    }}
                );
            
            
            refresh.addActionListener
                (
                    l -> 
                    {
                      load();
                    }
                );
            
            grade.addActionListener
                (
                    l -> 
                    {   curind1 = crsesName2.getSelectedIndex();
                        curind2 = stuName2.getSelectedIndex();

                        if(curind1 != -1 && curind2 != -1){

                        String course = crses.get(curind1).getID();
                        String student = stu.get(curind2).getID();
                        
                        GradeRequest gradeRequest = new GradeRequest(doublegrade);
                        
                        Call<ApiResponse> call = teacherInterface.gradeStudent(ApiClient.getToken(),course,student,gradeRequest);
            
                        call.enqueue(new Callback<ApiResponse>() {

                    @Override
                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                            if (response.isSuccessful()) {
                            ApiResponse getcrsesResponse = response.body();
                            
             

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
                }
            );
            
    }
          public void load(){
              isRefreshing = true;
            
            Call<GetManagedCoursesResponse> call = teacherInterface.getManagedCourses(ApiClient.getToken());
            
            call.enqueue(new Callback<GetManagedCoursesResponse>() {

            @Override
            public void onResponse(Call<GetManagedCoursesResponse> call, Response<GetManagedCoursesResponse> response) {
                if (response.isSuccessful()) {
                        GetManagedCoursesResponse getcrsesResponse = response.body();
                        
                        crsesName.clear();
                        
                        crses = getcrsesResponse.getManagedCourses();
                        for(int i=0;i<crses.size();i++){
                        ManagedCourse course = crses.get(i);
                        String label = course.getID();
                        String name = course.getName();
                        crsesName.add(i,label + "-" + name); 
                        
            
            
        }
                        isRefreshing = false;

                } else {
                        errorLabel.setBounds(500,550,600,50);
                            try {
                                 ApiResponse loginResponse = gson.fromJson(response.errorBody().string(), ApiResponse.class);
                                 errorLabel.setText("*"+ loginResponse.getMessage());
                            } catch (IOException ex) {
                                 errorLabel.setText("*"+ex.getMessage());
                                    }
                            isRefreshing = false;
                            }

                        }

                            @Override
                            public void onFailure(Call<GetManagedCoursesResponse> call, Throwable t) {
                                errorLabel.setText("*Something went wrong. Please try again.");
                                isRefreshing = false;
                            }

                        });
            
              
          }   

    public static void main(String[] args) {
        new TeachView("Teacher View");
    }

}
