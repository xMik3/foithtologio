package views.student;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import java.util.ArrayList;
import api.StudentInterface;
import models.students.RegisteredCourse;
import models.students.response.GetRegisteredCoursesResponse;
import models.general.ApiResponse;
import models.general.Course;
import client.ApiClient;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import org.jdesktop.swingx.prompt.PromptSupport;
import com.google.gson.Gson;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class StudView extends JFrame{
    
    JPanel titlepanel1;
    JPanel titlepanel2;
    JPanel bodypanel1;
    JPanel bodypanel2;
    JPanel btnpanel1;
    JPanel btnpanel2;
    JPanel panel;
    JPanel infop;
    private JLabel errorLabel;
    JLabel title1;
    JLabel title2;
    
    JLabel tname;
    JLabel tsurname;
    JLabel smstr;
    JLabel nm;
    JLabel cid;
    
    int curind;
    
    JButton addcrs;
    JButton rmcrs;
    DefaultListModel<String> crsesName;
    JList crsesName2;
    ArrayList<RegisteredCourse> regcrses;
    
    String toDelete;
    
        public StudView(String title){
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        StudentInterface stuInterface = ApiClient.getClient().create(StudentInterface.class);
        Gson gson = new Gson();
        
         try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
            UIManager.put("TextComponent.arc", 25);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        Color originalBackground = UIManager.getColor("Button.background");
        errorLabel = new JLabel();
        
        crsesName = new DefaultListModel<>();    
        crsesName2 = new JList(crsesName);
        regcrses = new ArrayList<>();
        crsesName2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        Call<GetRegisteredCoursesResponse> call = stuInterface.getRegisteredCourses(ApiClient.getToken());
            
            call.enqueue(new Callback<GetRegisteredCoursesResponse>() {

            @Override
            public void onResponse(Call<GetRegisteredCoursesResponse> call, Response<GetRegisteredCoursesResponse> response) {
                if (response.isSuccessful()) {
                        GetRegisteredCoursesResponse getcrsesResponse = response.body();
                        
                        regcrses = getcrsesResponse.getRegisteredCourses();

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
                            public void onFailure(Call<GetRegisteredCoursesResponse> call, Throwable t) {
                                errorLabel.setText("*Something went wrong. Please try again.");
                            }

                        }); 
            
            
        panel = new JPanel(new BorderLayout());
        
        infop = new JPanel();
        infop.setLayout(new BoxLayout(infop,BoxLayout.Y_AXIS));
        
        bodypanel1 = new JPanel();
        bodypanel1.setLayout(new BorderLayout());
        
        bodypanel2 = new JPanel();
        bodypanel2.setLayout(new BorderLayout());
        
        btnpanel1 = new JPanel();
        btnpanel1.setLayout(new FlowLayout());
        
        btnpanel2 = new JPanel();
        btnpanel2.setLayout(new FlowLayout());
        
        title1 = new JLabel("Courses:");
        title1.setFont(new Font("Arial", Font.PLAIN, 25));
        
        title2 = new JLabel("Information:");
        title2.setFont(new Font("Arial", Font.PLAIN, 25));
        
        addcrs = new JButton("Add");
        addcrs.setFont(new Font("", Font.PLAIN, 24));
        addcrs.setAlignmentX(Component.CENTER_ALIGNMENT); // needed for BoxLayout
        addcrs.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); // fill horizontally
        
        
        rmcrs = new JButton("Remove");
        rmcrs.setFont(new Font("", Font.PLAIN, 24));
        rmcrs.setAlignmentX(Component.CENTER_ALIGNMENT); // needed for BoxLayout
        rmcrs.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); // fill horizontally
        rmcrs.setOpaque(false);
        rmcrs.setBackground(new Color(0, 0, 0, 50));
        
        titlepanel1 = new JPanel();
        titlepanel1.setLayout(new BorderLayout());
        titlepanel1.add(title1,BorderLayout.CENTER);
        titlepanel1.setPreferredSize(new Dimension(0, 60));
        
        titlepanel2 = new JPanel();
        titlepanel2.setLayout(new BorderLayout());
        titlepanel2.add(title2, BorderLayout.CENTER);
        titlepanel2.setPreferredSize(new Dimension(0, 60));
        
        cid = new JLabel("Course's ID:");
        cid.setFont(new Font("Arial", Font.PLAIN, 20));
        
        tname = new JLabel("Course instructed by:");
        tname.setFont(new Font("Arial", Font.PLAIN, 20));
        
        tsurname = new JLabel("tsur");
        tsurname.setFont(new Font("Arial", Font.PLAIN, 20));
        
        smstr = new JLabel("smstr");
        smstr.setFont(new Font("Arial", Font.PLAIN, 20));
        
        nm = new JLabel("nm");
        nm.setFont(new Font("Arial", Font.PLAIN, 20));
        
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        controlPanel.add(addcrs);
        controlPanel.add(rmcrs);
        
        for(int i=0;i<regcrses.size();i++){
            RegisteredCourse course = regcrses.get(i);
            String label = course.getID();
            String name = course.getName();
            crsesName.add(i,label + "-" + name); 
        }
        
        
        
        JPanel listPanel = new JPanel();
            listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
            listPanel.setOpaque(false); // For transparency if desired

            listPanel.add(crsesName2);

            // Put listPanel inside a scroll pane
            JScrollPane scrollPane = new JScrollPane(listPanel);
            scrollPane.setOpaque(false);
            scrollPane.getViewport().setOpaque(false);
            scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setPreferredSize(new Dimension(200, 400)); // Force visible area
            
            addcrs.addActionListener
                (
                    l -> 
                    {
                      new addregcrs();
                    }
                );
            
            rmcrs.addActionListener
                (
                    l -> 
                    {
                        RegisteredCourse course = regcrses.get(curind);
                        
            Call<ApiResponse> call2 = stuInterface.deleteCourse(course.getID(),ApiClient.getToken());
            
            call2.enqueue(new Callback<ApiResponse>() {

            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                        ApiResponse delResponse = response.body();

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
            
            crsesName2.addListSelectionListener
                (
                    l -> 
                    {
                        rmcrs.setOpaque(true);
                        rmcrs.setBackground(originalBackground);
                        
                        curind = crsesName2.getSelectedIndex();
                        RegisteredCourse course = regcrses.get(curind);
                        nm.setText("Course's Name:" + course.getName());
                        cid.setText("Course's ID:" + course.getID());
                        smstr.setText("Course's Semester:" + course.getSemester());
                        tname.setText("Course instructed by:" + course.getTeacherSurname() + course.getTeacherName());
                    }
                );
            
            infop.add(nm);
            infop.add(Box.createRigidArea(new Dimension(0, 20)));
            infop.add(cid);
            infop.add(Box.createRigidArea(new Dimension(0, 20)));
            infop.add(smstr);
            infop.add(Box.createRigidArea(new Dimension(0, 20)));
            infop.add(tname);
            
            
            bodypanel1.add(scrollPane, BorderLayout.CENTER);
            bodypanel2.add(controlPanel, BorderLayout.SOUTH);
            bodypanel2.add(infop, BorderLayout.NORTH);
            
            // row 1: titles
            JPanel titleRow = new JPanel(new GridLayout(1, 2));
            titlepanel1.setBackground(new Color(80, 80, 80));
            titlepanel2.setBackground(new Color(80, 80, 80));
            titleRow.add(titlepanel1);
            titleRow.add(titlepanel2);
            

            // row 2: body (two columns)
            JPanel bodyRow = new JPanel(new GridLayout(1, 2));
            bodyRow.add(bodypanel1); // left side = scrollPane + controlPanel
            bodyRow.add(bodypanel2); // right side = info panel

            // add to panel
            panel.add(titleRow, BorderLayout.NORTH);
            panel.add(bodyRow, BorderLayout.CENTER);

            panel.setVisible(true);
            setVisible(true);
            setSize(1200,700);
            add(panel);
            
}
        public static void main(String[] args) {
        new StudView("bruh");
        
    }
}
