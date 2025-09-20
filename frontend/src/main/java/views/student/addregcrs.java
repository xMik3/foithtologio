
package views.student;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import java.util.ArrayList;
import api.StudentInterface;
import models.students.RegisteredCourse;
import models.students.AvailableCourse;
import models.students.request.RegisterCoursesRequest;
import models.students.response.GetAvailableCoursesResponse;
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
import models.secretary.response.GetCoursesResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import views.secretary.AddCourse;

public class addregcrs extends JFrame{
    private DefaultListModel<String> crsesName;
    private JList crsesName2;
    private ArrayList<AvailableCourse> allAvailCrses;
    private ArrayList<String> toreg;
    
    private StudView studentview;
    
    private JPanel titlep;
    private JPanel bodyp;
    private JPanel cntrlp;
    private JPanel p;
    
    JButton cnfrm;
    JLabel title;

    private JLabel errorLabel;
    
    
    public addregcrs(StudView studentview){

        this.studentview = studentview;

        errorLabel = new JLabel("");
        errorLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        errorLabel.setForeground(Color.RED);


        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        StudentInterface stuInterface = ApiClient.getClient().create(StudentInterface.class);
        Gson gson = new Gson();

         try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
            UIManager.put("TextComponent.arc", 25);

        } catch (Exception e) {
            e.printStackTrace();
        }

         crsesName = new DefaultListModel<>();
         crsesName2 = new JList(crsesName);
         crsesName2.setFixedCellHeight(50);

         allAvailCrses = new ArrayList<>();
         toreg = new ArrayList<>();

         crsesName2.setCellRenderer(new DefaultListCellRenderer() {
             @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setFont(new Font("Arial", Font.PLAIN, 24));
                label.setHorizontalAlignment(SwingConstants.CENTER);
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
         
         
         
         Call<GetAvailableCoursesResponse> call = stuInterface.getAvailableCourses(ApiClient.getToken());
            
            call.enqueue(new Callback<GetAvailableCoursesResponse>() {

            @Override
            public void onResponse(Call<GetAvailableCoursesResponse> call, Response<GetAvailableCoursesResponse> response) 
            {
                if (response.isSuccessful()) {
                        GetAvailableCoursesResponse availResponse = response.body();
                        allAvailCrses = availResponse.getAvailableCourses();
                        for(int i=0;i<allAvailCrses.size();i++){
                        AvailableCourse course = allAvailCrses.get(i);
                        String label = course.getID();
                        String name = course.getName();
                        crsesName.add(i,name + " - " + label);
        }

                } else {
                        errorLabel.setBounds(500,550,600,50);
                            try {
                                 ApiResponse loginResponse = gson.fromJson(response.errorBody().string(), ApiResponse.class);
                                 errorLabel.setText("*"+ loginResponse.getMessage());
                            } catch (IOException ex) 
                            {
                                 errorLabel.setText("*"+ex.getMessage());
                            }
                        }

            }

                            @Override
                            public void onFailure(Call<GetAvailableCoursesResponse> call, Throwable t) {
                                errorLabel.setText("*Something went wrong. Please try again.");
                            }

                        });
         
            
         crsesName2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
         
         p = new JPanel();
         p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
         
         titlep = new JPanel();
         titlep.setLayout(new FlowLayout(FlowLayout.CENTER));
         titlep.setBackground(new Color(80, 80, 80));
         titlep.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
         titlep.setMaximumSize(new Dimension(Integer.MAX_VALUE,75));
         
         bodyp = new JPanel();
         bodyp.setLayout(new BorderLayout());
         bodyp.setMaximumSize(new Dimension(Integer.MAX_VALUE, 450));


        cntrlp = new JPanel();
         cntrlp.setLayout(new FlowLayout(FlowLayout.RIGHT));
         cntrlp.setMaximumSize(new Dimension(Integer.MAX_VALUE, 75));
         
         cnfrm = new JButton("Confirm");
         cnfrm.setFont(new Font("Arial", Font.PLAIN, 20));
         cnfrm.setAlignmentX(Component.CENTER_ALIGNMENT);
         cnfrm.setAlignmentY(Component.CENTER_ALIGNMENT);
         
         title = new JLabel("Add Registered Course");
         title.setFont(new Font("Arial", Font.PLAIN, 25));

         JScrollPane scroll = new JScrollPane(crsesName2);
         scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
         
         bodyp.add(scroll,BorderLayout.CENTER);
         titlep.add(title);
         cntrlp.add(cnfrm);

         p.setBounds(0,0,600,600);
         p.add(titlep);
         p.add(bodyp);
         p.add(cntrlp);


        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(600, 600));
        layeredPane.add(p,JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(errorLabel,JLayeredPane.PALETTE_LAYER);



         setLocationRelativeTo(null);
         setVisible(true);
         setSize(600,600);
         add(layeredPane, BorderLayout.CENTER);
         setResizable(false);

         cnfrm.addActionListener(l -> {
               int[] indexes = crsesName2.getSelectedIndices();
               for(int index:indexes){
                   AvailableCourse course = allAvailCrses.get(index);
                   String label = course.getID();
                   toreg.add(label);
               }

            RegisterCoursesRequest regRequest = new RegisterCoursesRequest(toreg);
            Call<ApiResponse> call2 = stuInterface.registerCourse(ApiClient.getToken(),regRequest);

            call2.enqueue(new Callback<ApiResponse>() {

            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                        ApiResponse regResponse = response.body();

                        JOptionPane.showMessageDialog(
                                (JDialog) SwingUtilities.getWindowAncestor(addregcrs.this),                      // parent component, null = center of screen
                                "Courses Registered.",    // message text
                                "Success",             // dialog title
                                JOptionPane.INFORMATION_MESSAGE // type
                        );

                        dispose();
                } else {
                        errorLabel.setBounds(300,350,600,50);
                            try {
                                 ApiResponse registerResponse = gson.fromJson(response.errorBody().string(), ApiResponse.class);
                                 errorLabel.setText("*"+ registerResponse.getMessage());
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


            });



        }
       
}
