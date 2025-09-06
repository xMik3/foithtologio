package views.student;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

import api.LoginInterface;
import models.login.request.LoginRequest;
import models.login.response.LoginResponse;
import models.general.ApiResponse;
import client.ApiClient;

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
    
    JLabel title1;
    JLabel title2;
    
    JButton addcrs;
    JButton rmcrs;
    
        public StudView(String title){
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        Gson gson = new Gson();
        
         try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
            UIManager.put("TextComponent.arc", 25);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        panel = new JPanel(new BorderLayout());
        
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
        
        
        titlepanel1 = new JPanel();
        titlepanel1.setLayout(new BorderLayout());
        titlepanel1.add(title1,BorderLayout.CENTER);
        titlepanel1.setPreferredSize(new Dimension(0, 60));
        
        titlepanel2 = new JPanel();
        titlepanel2.setLayout(new BorderLayout());
        titlepanel2.add(title2, BorderLayout.CENTER);
        titlepanel2.setPreferredSize(new Dimension(0, 60));
        
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        controlPanel.add(addcrs);
        controlPanel.add(rmcrs);
        
        JPanel listPanel = new JPanel();
            listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
            listPanel.setOpaque(false); // For transparency if desired

            // Add many labels to force scrollbars
            for (int i = 1; i <= 30; i++) {
                JButton button = new JButton("• Item " + i);
                button.setFont(new Font("Arial", Font.PLAIN, 24));
                button.setForeground(Color.LIGHT_GRAY); // Use white if dark background
                button.setAlignmentX(Component.LEFT_ALIGNMENT);
                button.setHorizontalAlignment(SwingConstants.LEFT);
                button.setHorizontalTextPosition(SwingConstants.LEFT);
                int buttonHeight = 40; // or any value you like
                button.setPreferredSize(new Dimension(0, buttonHeight));
                button.setMaximumSize(new Dimension(Integer.MAX_VALUE, buttonHeight));
                button.setMinimumSize(new Dimension(0, buttonHeight));
                
                
                button.addActionListener
                (
                    l -> 
                    {
                        System.out.println("Clicked: " + button.getText());
                    }
                );
                
                
                listPanel.add(button);
            }

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
                        System.out.println("Clicked: " + addcrs.getText());
                    }
                );
            
            rmcrs.addActionListener
                (
                    l -> 
                    {
                        System.out.println("Clicked: " + rmcrs.getText());
                    }
                );
            
            
            bodypanel1.add(scrollPane, BorderLayout.CENTER);
            bodypanel1.add(controlPanel, BorderLayout.SOUTH);
            
            
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
