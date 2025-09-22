import views.general.Login;
import javax.swing.*;
import java.awt.*;


public class App{

    public static void main(String[] args){

        JFrame loginWindow = new Login("Login");

        ImageIcon logoIcon = new ImageIcon(App.class.getResource("/images/icon.png"));
        loginWindow.setIconImage(logoIcon.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH));
        loginWindow.setLocationRelativeTo(null);
        loginWindow.setVisible(true);

    }

}