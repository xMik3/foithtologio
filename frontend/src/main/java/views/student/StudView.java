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
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
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
    JPanel refreshp;
    private JLabel errorLabel;
    JLabel title1;
    JLabel title2;
    
    JLabel tname;
    JLabel tsurname;
    JLabel smstr;
    JLabel nm;
    JLabel cid;
    JLabel grade;
    
    int curind=0;
    
    JButton addcrs;
    JButton rmcrs;
    JButton refresh;
    DefaultListModel<String> crsesName;
    JList crsesName2;
    ArrayList<RegisteredCourse> regcrses;
    
    boolean isRefreshing = false;
    
    private StudentInterface stuInterface;
    private Gson gson;
    
    String toDelete;
    
        public StudView(String title){
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        stuInterface = ApiClient.getClient().create(StudentInterface.class);
        gson = new Gson();
        
         try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
            UIManager.put("TextComponent.arc", 25);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        Color originalBackground = UIManager.getColor("Button.background");

        errorLabel = new JLabel("");
        errorLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        errorLabel.setForeground(Color.RED);


        crsesName = new DefaultListModel<>();
        crsesName2 = new JList(crsesName);
        regcrses = new ArrayList<>();
        crsesName2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        crsesName2.setFixedCellHeight(50);
        
        
        crsesName2.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                    // Set font and text alignment
                    label.setFont(new Font("Arial", Font.PLAIN, 24));
                    label.setHorizontalAlignment(SwingConstants.CENTER);

                    // Set borders
                    Border lineBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(78, 80, 82));
                    Border leftBorder = BorderFactory.createEmptyBorder(0, 10, 0, 0);
                    label.setBorder(new CompoundBorder(lineBorder, leftBorder));


                    if (isSelected) {
                        label.setBackground(new Color(60, 60, 60));

                    } else {
                        label.setBackground(new Color(80, 80, 80));

                    }

                    label.setOpaque(true);  // important for background color

                    return label;
            }
        });
        
        
        
        load();
        
        panel = new JPanel(new BorderLayout());
        
        infop = new JPanel();
        infop.setLayout(new BoxLayout(infop,BoxLayout.Y_AXIS));
        
        infop.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        
        
        bodypanel1 = new JPanel();
        bodypanel1.setLayout(new BorderLayout());
        
        bodypanel1.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        
        bodypanel2 = new JPanel();
        bodypanel2.setLayout(new BorderLayout());
        bodypanel2.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        
        btnpanel1 = new JPanel();
        btnpanel1.setLayout(new FlowLayout());
        btnpanel1.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        
        btnpanel2 = new JPanel();
        btnpanel2.setLayout(new FlowLayout());
        btnpanel2.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        
        refreshp = new JPanel();
        refreshp.setLayout(new BorderLayout());
        refreshp.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        title1 = new JLabel("Courses");
        title1.setFont(new Font("Arial", Font.PLAIN, 30));
        
        title2 = new JLabel("Information");
        title2.setFont(new Font("Arial", Font.PLAIN, 30));
        
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
        
        refresh = new JButton("Refresh");
        refresh.setFont(new Font("", Font.PLAIN, 24));
        refresh.setAlignmentX(Component.CENTER_ALIGNMENT); // needed for BoxLayout
     
        
        titlepanel1 = new JPanel();
        titlepanel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        titlepanel1.add(title1);
        titlepanel1.setPreferredSize(new Dimension(0, 60));
        titlepanel1.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        
        titlepanel2 = new JPanel();
        titlepanel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        titlepanel2.add(title2);
        titlepanel2.setPreferredSize(new Dimension(0, 10));
        titlepanel2.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        
        cid = new JLabel();
        cid.setFont(new Font("Arial", Font.PLAIN, 24));
        cid.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        tname = new JLabel();
        tname.setFont(new Font("Arial", Font.PLAIN, 24));
        tname.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        tsurname = new JLabel();
        tsurname.setFont(new Font("Arial", Font.PLAIN, 24));
        tsurname.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        smstr = new JLabel();
        smstr.setFont(new Font("Arial", Font.PLAIN, 24));
        smstr.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        nm = new JLabel();
        nm.setFont(new Font("Arial", Font.PLAIN, 34));
        nm.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        grade = new JLabel();
        grade.setFont(new Font("Arial", Font.PLAIN, 24));
        grade.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        controlPanel.add(addcrs);
        controlPanel.add(rmcrs);
        
            
            // Put listPanel inside a scroll pane
            JScrollPane scrollPane = new JScrollPane(crsesName2);
            scrollPane.setOpaque(false);
            scrollPane.getViewport().setOpaque(false);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setBackground(new Color(80, 80, 80));
            scrollPane.getViewport().setBackground(new Color(80, 80, 80));
            scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            
            addcrs.addActionListener(l -> {
                  if (curind != -1 && !isRefreshing) {
                    new addregcrs(this);
                  }
                }
            );
            
            
            rmcrs.addActionListener(l -> {
                        if (curind != -1 && !isRefreshing) {
                        
                            int curind2 = curind;
                            RegisteredCourse course = regcrses.get(curind2);
                        
            Call<ApiResponse> call2 = stuInterface.deleteCourse(course.getID(),ApiClient.getToken());
            
            call2.enqueue(new Callback<ApiResponse>() {

            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                        ApiResponse delResponse = response.body();
                            SwingUtilities.invokeLater(() -> {
                            crsesName.remove(curind2);
                            regcrses.remove(curind2); 
                            });
                            
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
                    });
            
            crsesName2.addListSelectionListener
                (
                    l -> 
                    {
                        if (!l.getValueIsAdjusting() && !isRefreshing) {
                        rmcrs.setOpaque(true);
                        rmcrs.setBackground(originalBackground);
                        
                        curind = crsesName2.getSelectedIndex();
                        
                        if (curind == -1) {
                            return; // nothing selected, exit early
        }
                        RegisteredCourse course = regcrses.get(curind);
                        nm.setText(course.getName());
                        cid.setText("Course ID : " + course.getID());
                        smstr.setText("Semester : " + course.getSemester());
                        tname.setText("Instructed by : " + course.getTeacherSurname() + " " + course.getTeacherName());
                        if(course.getGrade()==null){
                            grade.setText("Ungraded");
                        }
                        else{
                            grade.setText("Grade : " + course.getGrade());
                        }
                        System.out.println(curind);

                    }}
                );
            
            refresh.addActionListener
                (
                    l -> 
                    {
                      load();
                    }
                );
            

            infop.add(nm);
            infop.add(Box.createRigidArea(new Dimension(0, 20)));
            infop.add(cid);
            infop.add(Box.createRigidArea(new Dimension(0, 10)));
            infop.add(smstr);
            infop.add(Box.createRigidArea(new Dimension(0, 10)));
            infop.add(tname);
            infop.add(Box.createRigidArea(new Dimension(0, 10)));
            infop.add(grade);
            
            refreshp.add(refresh,BorderLayout.CENTER);
            
            bodypanel1.add(scrollPane, BorderLayout.CENTER);
            bodypanel2.add(controlPanel, BorderLayout.SOUTH);
            bodypanel2.add(infop, BorderLayout.CENTER);
            bodypanel2.add(refreshp, BorderLayout.NORTH);
            
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


            setVisible(true);
            setSize(1300,600);
            setResizable(false);
            add(panel);
            
}
        public void load(){
            
            isRefreshing = true;
            
            Call<GetRegisteredCoursesResponse> call = stuInterface.getRegisteredCourses(ApiClient.getToken());
            
            call.enqueue(new Callback<GetRegisteredCoursesResponse>() {

            @Override
            public void onResponse(Call<GetRegisteredCoursesResponse> call, Response<GetRegisteredCoursesResponse> response) {
                if (response.isSuccessful()) {
                        GetRegisteredCoursesResponse getcrsesResponse = response.body();
                        
                        regcrses = getcrsesResponse.getRegisteredCourses();
                        
                        crsesName.clear();
                        
                        for(int i=0;i<regcrses.size();i++){
                        RegisteredCourse course = regcrses.get(i);
                        String label = course.getID();
                        String name = course.getName();
                        crsesName.add(i,name + " - " + label);
                }
                        isRefreshing = false;

                } else {
                        errorLabel.setBounds(500,550,600,50);
                            try {
                                 ApiResponse callResponse = gson.fromJson(response.errorBody().string(), ApiResponse.class);
                                 errorLabel.setText("*"+ callResponse.getMessage());
                            } catch (IOException ex) {
                                 errorLabel.setText("*"+ex.getMessage());
                                    }
                                    isRefreshing = false;
                                }

                            }

                            @Override
                            public void onFailure(Call<GetRegisteredCoursesResponse> call, Throwable t) {
                                errorLabel.setText("*Something went wrong. Please try again.");
                                isRefreshing = false;
                            }

                        });

        }
        
}
