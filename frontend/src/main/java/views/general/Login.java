package views.general;

import javax.swing.*;
import java.awt.*;
import org.jdesktop.swingx.prompt.PromptSupport;

public class Login extends JFrame{

    private JLabel icon;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField organizationURLField;
    private JButton loginButton;

    public Login(String title){
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
            UIManager.put("TextComponent.arc", 25);
            UIManager.put( "Button.arc", 999 );
        } catch (Exception e) {
            e.printStackTrace();
        }


        icon = new JLabel(new ImageIcon(getClass().getResource("/images/temporaryIcon.png")));
        organizationURLField = new JTextField();
        organizationURLField.setFont(new Font("Arial", Font.PLAIN, 25));
        PromptSupport.setPrompt("Enter Organization URL", organizationURLField);

        JPanel leftUpPanel = new JPanel(new BorderLayout());
        leftUpPanel.add(icon,BorderLayout.CENTER);
        leftUpPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));

        JPanel leftDownPanel = new JPanel(new BorderLayout());
        leftDownPanel.add(organizationURLField,BorderLayout.CENTER);
        leftDownPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 200, 0));

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

        loginButton = new JButton();
        loginButton.setFont(new Font("Arial", Font.PLAIN, 22));
        loginButton.setText("Login");

        JPanel rightUpPanel = new JPanel(new BorderLayout());
        rightUpPanel.setLayout(new GridLayout(2,1,0,25));
        rightUpPanel.add(usernameField, BorderLayout.NORTH);
        rightUpPanel.add(passwordField, BorderLayout.SOUTH);
        rightUpPanel.setBorder(BorderFactory.createEmptyBorder(100, 0, 55, 0));

        JPanel rightDownPanel = new JPanel(new BorderLayout());
        rightDownPanel.add(loginButton);
        rightDownPanel.setBorder(BorderFactory.createEmptyBorder(0, 140, 230, 140));

        JPanel rightPanel = new JPanel(new  BorderLayout());
        rightPanel.setLayout(new GridLayout(2,1,0,0));
        rightPanel.add(rightUpPanel, BorderLayout.CENTER);
        rightPanel.add(rightDownPanel, BorderLayout.SOUTH);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 0, 100));
        panel.setLayout(new GridLayout(1,2,100,0));
        panel.add(leftPanel, BorderLayout.WEST);
        panel.add(rightPanel,BorderLayout.EAST);

        add(panel);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

    }
        
    public static void main(String[] args) {
        new Login("Login");

    }

}
