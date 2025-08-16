
import javax.swing.*;
import java.awt.*;

public class Login extends JFrame{
    private JButton lgn;
    private JTextField usrnm;
    private JTextField pswd;
    private JLabel Lusrnm;
    private JLabel Lpswd;
    private JPanel p;
    private JLabel icon;
    private JPanel exp1;
    private JPanel exp2;
    private JPanel exp3;
    private JPanel exp4;
    private JPanel expi;
    private JPanel expil;
    private JPanel expir;
    
    
    
    
        public Login(String title){
            super(title);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            setLayout(new BorderLayout());
            
            p = new JPanel();
            p.setLayout(new GridBagLayout());
            
            lgn = new JButton("Log in");
            usrnm = new JTextField(20);
            pswd = new JTextField(20);
            Lusrnm = new JLabel("Username:");
            Lpswd = new JLabel("Password:");
            icon = new JLabel("icon");
            exp1 = new JPanel();
            exp2 = new JPanel();
            exp3 = new JPanel();
            exp4 = new JPanel();
            expi = new JPanel();
            expil = new JPanel();
            expir = new JPanel();
            
            Lpswd.setFont(new Font("Consolas", Font.BOLD, 20));
            Lpswd.setHorizontalAlignment(SwingConstants.CENTER);
            Lusrnm.setFont(new Font("Consolas", Font.BOLD, 20));
            Lusrnm.setHorizontalAlignment(SwingConstants.CENTER);
            
            lgn.setPreferredSize(new Dimension(120, 30));
            
            GridBagConstraints huh = new GridBagConstraints();
            huh.fill = GridBagConstraints.NONE;
            huh.insets=new Insets(10,5,10,5);
            huh.weighty =1.0;
            huh.weightx = 1.0;
            
            
            huh.gridx = 0;
            huh.gridy=0;
            huh.weightx=0.33;
            huh.weighty = 0.33;
             
            huh.gridx = 1;
            huh.weightx = 0.33;
            huh.weighty = 0.33;
            huh.anchor = GridBagConstraints.CENTER;
            p.add(icon,huh);
            
            huh.gridx = 2;
            huh.weightx=0.33;
            huh.weighty = 0.33;
            
            huh.gridy = 1;
            huh.gridx = 0;
            huh.weightx = 0.33;
            huh.weighty = 0.16;
            huh.anchor = GridBagConstraints.NORTHWEST;
            p.add(Lusrnm,huh);
            
            huh.gridy = 2;
            huh.weightx = 0.33;
            huh.weighty = 0.16;
            huh.anchor = GridBagConstraints.NORTHWEST;
            p.add(Lpswd,huh);
            
            huh.gridy=3;
            huh.weightx=0.33;
            huh.weighty = 0.33;
            
            huh.gridy = 1;
            huh.gridx = 1;
            huh.weightx = 0.33;
            huh.weighty = 0.16;
            huh.anchor = GridBagConstraints.NORTHWEST;
            p.add(usrnm,huh);
            
            huh.gridy = 2;
            huh.weightx = 0.33;
            huh.weighty = 0.16;
            huh.anchor = GridBagConstraints.NORTHWEST;
            p.add(pswd,huh);
            
            huh.gridy = 3;
            huh.weightx = 0.33;
            huh.weighty = 0.33;
            huh.anchor = GridBagConstraints.NORTHEAST;
            p.add(lgn,huh);
            
            huh.gridx = 2;
            huh.gridy = 0;
            huh.weightx=0.33;
            huh.weighty = 0.33;
            p.add(exp1,huh);
            
            huh.gridy = 1;
            huh.weightx=0.33;
            huh.weighty = 0.16;
            p.add(exp2,huh);
            
            huh.gridy = 2;
            huh.weightx=0.33;
            huh.weighty = 0.16;
            p.add(exp3,huh);
            
            huh.gridy = 3;
            huh.weightx=0.33;
            huh.weighty = 0.33;
            p.add(exp4,huh);
            
            add(p);
            setSize(500,700);
            setVisible(true);
            p.setVisible(true);
            
            
        } 
        
        public static void main(String[] args) {
        new Login("bruh");
        
    }



}
