package views.general;

import java.awt.*;
import javax.swing.*;

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
       

        
        public Template(String title)
        {
            
            super(title);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            setLayout(new BorderLayout());
            
            
            gridp = new JPanel();
            gridp.setLayout(new GridBagLayout());
            
            title0 = new JLabel("Panel1");
            title1 = new JLabel("Panel2");
            title2 = new JLabel("Panel3");
            
            title0.setFont(new Font("Consolas", Font.BOLD, 20));
            title0.setHorizontalAlignment(SwingConstants.CENTER);

            title1.setFont(new Font("Consolas", Font.BOLD, 20));
            title1.setHorizontalAlignment(SwingConstants.CENTER);

            title2.setFont(new Font("Consolas", Font.BOLD, 20));
            title2.setHorizontalAlignment(SwingConstants.CENTER);
            
            ex1 = new JPanel();
            ex2 = new JPanel();
            ex3 = new JPanel();
            ex4 = new JPanel();
            ex5 = new JPanel();
            ex6 = new JPanel();
            ex4.setBackground(Color.GRAY);
            ex5.setBackground(Color.GRAY);
            ex6.setBackground(Color.GRAY);

            ex4.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            ex4.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

            ex5.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            ex5.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

            ex6.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            ex6.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            


            // Create scrollable list panel
            JPanel listPanel = new JPanel();
            listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
            listPanel.setOpaque(false); // For transparency if desired

            // Add many labels to force scrollbars
            for (int i = 1; i <= 30; i++) {
                JButton button = new JButton("• Item " + i);
                button.setFont(new Font("Consolas", Font.PLAIN, 16));
                button.setForeground(Color.BLACK); // Use white if dark background
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

            // Add to ex1
            ex1.add(scrollPane, BorderLayout.CENTER);






            
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
            
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setUndecorated(true); // Optional: remove window borders
            setVisible(true);
            gridp.setVisible(true);


            
        }
    public static void main(String[] args) {
        new Template("bruh");
        
    }



}


//autaaaaaa