package views.teacher;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class TeachView extends JFrame {
        
        private JPanel gridp; 
        private JLabel title0;
        private JLabel title1;
        private JLabel title2;
        private JPanel ex1;
        private JPanel ex2;
        private JPanel ex3;
        private JPanel ex4;
        private JPanel ex5;
        private JPanel ex6;
        private JTextArea description;

        public TeachView(String title)
        {

            super(title);

            try {
                UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
            } catch (Exception e) {
                e.printStackTrace();
            }

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new BorderLayout());
            
            gridp = new JPanel();
            gridp.setLayout(new GridBagLayout());
            
            title0 = new JLabel("Students");
            title1 = new JLabel("Courses");
            title2 = new JLabel("Description");
            
            title0.setFont(new Font("Consolas", Font.BOLD, 20));
            title0.setHorizontalAlignment(SwingConstants.CENTER);

            title1.setFont(new Font("Consolas", Font.BOLD, 20));
            title1.setHorizontalAlignment(SwingConstants.CENTER);

            title2.setFont(new Font("Consolas", Font.BOLD, 20));
            title2.setHorizontalAlignment(SwingConstants.CENTER);
            
            ex1 = new JPanel(new BorderLayout());
            ex2 = new JPanel(new BorderLayout());
            ex3 = new JPanel();
            ex4 = new JPanel();
            ex5 = new JPanel();
            ex6 = new JPanel();
            ex4.setBackground(Color.GRAY);
            ex5.setBackground(Color.GRAY);
            ex6.setBackground(Color.GRAY);

            ex4.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            ex5.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            ex6.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));



            JPanel listPanel = new JPanel();
            listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
            listPanel.setOpaque(false);

            for (int i = 1; i <= 30; i++) {
                JButton button = new JButton("Student ID" + (1000 + i));
                button.setFont(new Font("Arial", Font.PLAIN, 24));
                button.setForeground(Color.LIGHT_GRAY); // Use white if dark background
                button.setAlignmentX(Component.LEFT_ALIGNMENT);
                button.setHorizontalAlignment(SwingConstants.LEFT);
                button.setHorizontalTextPosition(SwingConstants.LEFT);
                int buttonHeight = 40; // or any value you like
                button.setPreferredSize(new Dimension(0, buttonHeight));
                button.setMaximumSize(new Dimension(Integer.MAX_VALUE, buttonHeight));
                button.setMinimumSize(new Dimension(0, buttonHeight));
                button.addActionListener(
                    l -> description.append(button.getText() + "\n")
                );
                listPanel.add(button);
            }

            JScrollPane scrollPane = new JScrollPane(listPanel);
            scrollPane.setOpaque(false);
            scrollPane.getViewport().setOpaque(false);
            scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setPreferredSize(new Dimension(600, 800));

            ex1.add(scrollPane, BorderLayout.CENTER);


            JPanel listPanel2 = new JPanel();
            listPanel2.setLayout(new BoxLayout(listPanel2, BoxLayout.Y_AXIS));
            listPanel2.setOpaque(false);

            for (int i = 1; i <= 30; i++) {
                JButton button = new JButton("Course ID " + (6000 + i));
                button.setFont(new Font("Arial", Font.PLAIN, 24));
                button.setForeground(Color.LIGHT_GRAY); // Use white if dark background
                button.setAlignmentX(Component.LEFT_ALIGNMENT);
                button.setHorizontalAlignment(SwingConstants.LEFT);
                button.setHorizontalTextPosition(SwingConstants.LEFT);
                int buttonHeight = 40; // or any value you like
                button.setPreferredSize(new Dimension(0, buttonHeight));
                button.setMaximumSize(new Dimension(Integer.MAX_VALUE, buttonHeight));
                button.setMinimumSize(new Dimension(0, buttonHeight));
                button.addActionListener(
                    l -> description.append(button.getText() + "\n")
                );
                listPanel2.add(button);
            }

            JScrollPane scrollPane2 = new JScrollPane(listPanel2);
            scrollPane2.setOpaque(false);
            scrollPane2.getViewport().setOpaque(false);
            scrollPane2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane2.setPreferredSize(new Dimension(600, 800));

            ex2.add(scrollPane2, BorderLayout.CENTER);


            description = new JTextArea(45, 55);
            description.setFont(new Font("Consolas", Font.PLAIN, 16));
            description.setText("");
            description.setEditable(false);
            JScrollPane textScroll = new JScrollPane(description);
            ex3.add(textScroll, BorderLayout.CENTER); 
            

            JPanel controlPanel = new JPanel();
            controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

            JButton gradeBtn = new JButton("Grade");
            gradeBtn.setFont(new Font("", Font.PLAIN, 24));
            gradeBtn.setAlignmentX(Component.CENTER_ALIGNMENT); // needed for BoxLayout
            gradeBtn.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); // fill horizontally
            gradeBtn.setOpaque(false);
            gradeBtn.setBackground(new Color(0, 0, 0, 50));

            // Add action for Add
            gradeBtn.addActionListener(e -> {
                String grade = JOptionPane.showInputDialog(this, "Enter new grade text:");
                
                
            });

            controlPanel.add(gradeBtn);
            ex1.add(controlPanel, BorderLayout.SOUTH);
            
            JPanel emptyPanel = new JPanel();
            emptyPanel.setPreferredSize(controlPanel.getPreferredSize());
            ex2.add(emptyPanel, BorderLayout.SOUTH);


            GridBagConstraints huh = new GridBagConstraints();
            huh.fill = GridBagConstraints.BOTH;
            huh.weightx = 1.0;
            huh.gridx = 0;
            huh.gridy = 0;
            
            huh.weightx = 0.5;
            huh.weighty = 0.15;
            gridp.add(title0, huh);
            gridp.add(ex4, huh);
            
            huh.gridx = 1;
            huh.weightx = 0.25;
            gridp.add(title1, huh);
            gridp.add(ex5, huh);
            
            huh.gridx = 2;
            huh.weightx = 0.25;
            gridp.add(title2, huh);
            gridp.add(ex6, huh);
            
            huh.gridy = 1;
            huh.gridx = 0;
            huh.weightx = 0.5;
            huh.weighty = 0.85;
            gridp.add(ex1, huh);
            
            huh.gridx = 1;
            huh.weightx = 0.5;
            gridp.add(ex2, huh);
            
            huh.gridx = 2;
            huh.weightx = 0.25;
            gridp.add(ex3, huh);
            
            add(gridp);
            //setExtendedState(JFrame.MAXIMIZED_BOTH);
            //setUndecorated(true);
            setSize(1200, 800);
            setLocationRelativeTo(null); // center on screen
            setVisible(true);
            gridp.setVisible(true);
        }

    public static void main(String[] args) {
        new TeachView("Teacher View");
    }

}
