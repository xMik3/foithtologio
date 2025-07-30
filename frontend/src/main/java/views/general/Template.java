
package com.mycompany.foit;
import javax.swing.*;
import java.awt.*;

public class Template extends JFrame {
        
        private JPanel gridp; //One big panel with customizable Grid layout
        private JLabel title0;
        private JLabel title1;
        private JLabel title2;
        //example subpanels to see where the grid splits
        private JPanel ex1;
        private JPanel ex2;
        private JPanel ex3;
        private JPanel ex4;
        private JPanel ex5;
        private JPanel ex6;
       

        
        public Template(String title){
            
            super(title);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            setLayout(new BorderLayout());
            
            
            gridp = new JPanel();
            gridp.setLayout(new GridBagLayout());
            
            title0 = new JLabel("titos1");
            title1 = new JLabel("*titlos");
            title2 = new JLabel("hahaha titos");
            
            ex1 = new JPanel();
            ex2 = new JPanel();
            ex3 = new JPanel();
            ex4 = new JPanel();
            ex5 = new JPanel();
            ex6 = new JPanel();
            ex1.setBackground(Color.RED);
            ex2.setBackground(Color.BLUE);
            ex3.setBackground(Color.GREEN);
            ex4.setBackground(Color.GRAY);
            ex5.setBackground(Color.WHITE);
            ex6.setBackground(Color.PINK);
            
            //adjusting each subgrid 
            GridBagConstraints huh = new GridBagConstraints();
            huh.fill = GridBagConstraints.BOTH;
            huh.weightx = 1.0;
            huh.gridx = 0;
            huh.gridy=0;
            
            huh.weightx=0.5;
            huh.weighty=0.15;
            gridp.add(title0,huh);
            gridp.add(ex4,huh);
            
            huh.gridx=1;
            huh.weightx=0.25;
            gridp.add(title1,huh);
            gridp.add(ex5,huh);
            
            huh.gridx=2;
            huh.weightx=0.25;
            gridp.add(title2,huh);
            gridp.add(ex6,huh);
            
            huh.gridy=1;
            huh.gridx=0;
            huh.weightx=0.5;
            huh.weighty=0.85;
            gridp.add(ex1,huh);
            
            huh.gridx=1;
            huh.weightx=0.25;
            gridp.add(ex2,huh);
            
            huh.gridx=2;
            huh.weightx=0.25;
            gridp.add(ex3,huh);
            
            add(gridp);
            
            setSize(800,600);
            setVisible(true);
            gridp.setVisible(true);
            
            
}
    public static void main(String[] args) {
        new Template("bruh");
        
    }
}
//autaaaaaa