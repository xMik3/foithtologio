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
            UIManager.put( "Button.arc", 999 );
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(3,2));
        
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
        addcrs.setFont(new Font("Arial", Font.PLAIN, 25));
        
        
        rmcrs = new JButton("Remove");
        rmcrs.setFont(new Font("Arial", Font.PLAIN, 25));
        
        
        titlepanel1 = new JPanel();
        titlepanel1.setLayout(new BorderLayout());
        titlepanel1.add(title1,BorderLayout.CENTER);
        
        titlepanel2 = new JPanel();
        titlepanel2.setLayout(new BorderLayout());
        titlepanel2.add(title2, BorderLayout.CENTER);
        
        
        JPanel listPanel = new JPanel();
            listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
            listPanel.setOpaque(false); // For transparency if desired

            // Add many labels to force scrollbars
            for (int i = 1; i <= 30; i++) {
                JButton button = new JButton("• Item " + i);
                button.setFont(new Font("Arial", Font.PLAIN, 25));
                button.setAlignmentX(Component.LEFT_ALIGNMENT);
                
                
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
            
            bodypanel1.add(scrollPane,BorderLayout.CENTER);
            btnpanel1.add(addcrs,BorderLayout.NORTH);
            btnpanel2.add(rmcrs,BorderLayout.NORTH);
            
            panel.add(titlepanel1);
            panel.add(titlepanel2);
            panel.add(bodypanel1);
            panel.add(bodypanel2);
            panel.add(btnpanel1);
            panel.add(btnpanel2);
            
            panel.setVisible(true);
            setVisible(true);
            setSize(1200,700);
            add(panel);
            
}
        public static void main(String[] args) {
        new StudView("bruh");
        
    }
}
