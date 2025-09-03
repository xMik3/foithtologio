package views.secretary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.UIManager;




public class SecreView extends JFrame {
        
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
        private JButton lastSelectedButton;

        
        public SecreView(String title)
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
            gridp.setLayout(new java.awt.GridLayout(2, 3, 10, 10)); // 2 rows, 3 columns, with spacing

            
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


            ex1.setLayout(new BorderLayout()); 

            // Create scrollable list panel
            JPanel listPanel = new JPanel();
            listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
            listPanel.setOpaque(false); // For transparency if desired

            // Add many labels to force scrollbars
            for (int i = 1; i <= 30; i++) {
                JButton button = new JButton("Student ID" + (22390000 + i));
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
                        
                        lastSelectedButton = button;
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
            scrollPane.setPreferredSize(new Dimension(600, 800)); // Force visible area

            // Add to ex1
            ex1.add(scrollPane, BorderLayout.CENTER);





            ex2.setLayout(new BorderLayout());

            // Create scrollable list panel
            JPanel listPanel2 = new JPanel();
            listPanel2.setLayout(new BoxLayout(listPanel2, BoxLayout.Y_AXIS));
            listPanel2.setOpaque(false); // For transparency if desired

            // Add many labels to force scrollbars
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

                
                final int num;
                num = i;
                button.addActionListener
                (
                    l -> 
                    {
                        
                        lastSelectedButton = button;
                    }
                );
                
                
                listPanel2.add(button);
            }

            // Put listPanel inside a scroll pane
            JScrollPane scrollPane2 = new JScrollPane(listPanel2);
            scrollPane2.setOpaque(false);
            scrollPane2.getViewport().setOpaque(false);
            scrollPane2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane2.setPreferredSize(new Dimension(400, 800)); // Force visible area

            // Add to ex2
            ex2.add(scrollPane2, BorderLayout.CENTER);


            ex3.setLayout(new BorderLayout());
            
            // Create scrollable list panel
            JPanel listPanel3 = new JPanel();
            listPanel3.setLayout(new BoxLayout(listPanel3, BoxLayout.Y_AXIS));
            listPanel3.setOpaque(false); // For transparency if desired

            // Add many labels to force scrollbars
            for (int i = 1; i <= 30; i++) {
                JButton button = new JButton("Teacher ID " + (6000 + i));
                button.setFont(new Font("Arial", Font.PLAIN, 24));
                button.setForeground(Color.LIGHT_GRAY); // Use white if dark background
                button.setAlignmentX(Component.LEFT_ALIGNMENT);
                button.setHorizontalAlignment(SwingConstants.LEFT);
                button.setHorizontalTextPosition(SwingConstants.LEFT);
                int buttonHeight = 40; // or any value you like
                button.setPreferredSize(new Dimension(0, buttonHeight));
                button.setMaximumSize(new Dimension(Integer.MAX_VALUE, buttonHeight));
                button.setMinimumSize(new Dimension(0, buttonHeight));

                
                final int num;
                num = i;
                button.addActionListener
                (
                    l -> 
                    {
                        
                        lastSelectedButton = button;
                    }
                );
                
                
                listPanel3.add(button);
            }

            // Put listPanel inside a scroll pane
            JScrollPane scrollPane3 = new JScrollPane(listPanel3);
            scrollPane3.setOpaque(false);
            scrollPane3.getViewport().setOpaque(false);
            scrollPane3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            scrollPane3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane3.setPreferredSize(new Dimension(400, 800)); // Force visible area

            // Add to ex1
            ex3.add(scrollPane3, BorderLayout.CENTER);

            
            

            JPanel controlPanel = new JPanel();
            controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

            JButton addBtn = new JButton("➕ Add");
            addBtn.setFont(new Font("", Font.PLAIN, 24));
            addBtn.setAlignmentX(Component.CENTER_ALIGNMENT); // needed for BoxLayout
            addBtn.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); // fill horizontally
            
            JButton deleteBtn = new JButton("🗑 Delete");
            deleteBtn.setFont(new Font("", Font.PLAIN, 24));
            deleteBtn.setAlignmentX(Component.CENTER_ALIGNMENT); // needed for BoxLayout
            deleteBtn.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); // fill horizontally
            
            JButton editBtn = new JButton("✏ Edit");
            editBtn.setFont(new Font("", Font.PLAIN, 24));
            editBtn.setAlignmentX(Component.CENTER_ALIGNMENT); // needed for BoxLayout
            editBtn.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); // fill horizontally

            // Add action for Add
            addBtn.addActionListener(e -> {
                addusr form = new addusr("form");
            });
            

            // Delete last selected
            deleteBtn.addActionListener(e -> {
                if (lastSelectedButton != null) {
                    Container parent = lastSelectedButton.getParent();
                    parent.remove(lastSelectedButton);
                    parent.revalidate();
                    parent.repaint();
                    lastSelectedButton = null;
                } else {
                    JOptionPane.showMessageDialog(this, "No button selected!");
                }
            });

            // Edit last selected
            editBtn.addActionListener(e -> {
                if (lastSelectedButton != null) {
                    showFormDialog(true);
                } else {
                    JOptionPane.showMessageDialog(this, "No button selected!");
                }
            });

            controlPanel.add(addBtn);
            controlPanel.add(deleteBtn);
            controlPanel.add(editBtn);

            ex1.add(controlPanel, BorderLayout.SOUTH);

            JPanel controlPanel2 = new JPanel();
            controlPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

            JButton addBtn2 = new JButton("➕ Add");
            addBtn2.setFont(new Font("", Font.PLAIN, 24));
            addBtn2.setAlignmentX(Component.CENTER_ALIGNMENT); // needed for BoxLayout
            addBtn2.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); // fill horizontally
            
            JButton deleteBtn2 = new JButton("🗑 Delete");
            deleteBtn2.setFont(new Font("", Font.PLAIN, 24));
            deleteBtn2.setAlignmentX(Component.CENTER_ALIGNMENT); // needed for BoxLayout
            deleteBtn2.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); // fill horizontally
            
            JButton editBtn2 = new JButton("✏ Edit");
            editBtn2.setFont(new Font("", Font.PLAIN, 24));
            editBtn2.setAlignmentX(Component.CENTER_ALIGNMENT); // needed for BoxLayout
            editBtn2.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); // fill horizontally

            // Add action for Add
            addBtn2.addActionListener(e -> {
                addcrs form = new addcrs("Form");
                int sem = form.getSemester();
                String name = form.getName();
            });
            

            // Delete last selected
            deleteBtn2.addActionListener(e -> {
                if (lastSelectedButton != null) {
                    Container parent = lastSelectedButton.getParent();
                    parent.remove(lastSelectedButton);
                    parent.revalidate();
                    parent.repaint();
                    lastSelectedButton = null;
                } else {
                    JOptionPane.showMessageDialog(this, "No button selected!");
                }
            });

            // Edit last selected
            editBtn2.addActionListener(e -> {
                if (lastSelectedButton != null) {
                    showFormDialog(true);
                } else {
                    JOptionPane.showMessageDialog(this, "No button selected!");
                }
            });

            controlPanel2.add(addBtn2);
            controlPanel2.add(deleteBtn2);
            controlPanel2.add(editBtn2);

            ex2.add(controlPanel2, BorderLayout.SOUTH);

            JPanel controlPanel3 = new JPanel();
            controlPanel3.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

            JButton addBtn3 = new JButton("➕ Add");
            addBtn3.setFont(new Font("", Font.PLAIN, 24));
            addBtn3.setAlignmentX(Component.CENTER_ALIGNMENT); // needed for BoxLayout
            addBtn3.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); // fill horizontally
            
            JButton deleteBtn3 = new JButton("🗑 Delete");
            deleteBtn3.setFont(new Font("", Font.PLAIN, 24));
            deleteBtn3.setAlignmentX(Component.CENTER_ALIGNMENT); // needed for BoxLayout
            deleteBtn3.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); // fill horizontally
            
            JButton editBtn3 = new JButton("✏ Edit");
            editBtn3.setFont(new Font("", Font.PLAIN, 24));
            editBtn3.setAlignmentX(Component.CENTER_ALIGNMENT); // needed for BoxLayout
            editBtn3.setMaximumSize(new Dimension(Short.MAX_VALUE, 50)); // fill horizontally

            // Add action for Add
            addBtn3.addActionListener(e -> showFormDialog(false));

            // Delete last selected
            deleteBtn3.addActionListener(e -> {
                if (lastSelectedButton != null) {
                    Container parent = lastSelectedButton.getParent();
                    parent.remove(lastSelectedButton);
                    parent.revalidate();
                    parent.repaint();
                    lastSelectedButton = null;
                } else {
                    JOptionPane.showMessageDialog(this, "No button selected!");
                }
            });

            // Edit last selected
            editBtn3.addActionListener(e -> {
                if (lastSelectedButton != null) {
                    showFormDialog(true);
                } else {
                    JOptionPane.showMessageDialog(this, "No button selected!");
                }
            });

            controlPanel3.add(addBtn3);
            controlPanel3.add(deleteBtn3);
            controlPanel3.add(editBtn3);

            ex3.add(controlPanel3, BorderLayout.SOUTH);

            // Main container
            gridp = new JPanel();
            gridp.setLayout(new BorderLayout());

            // --- Top row (titles) ---
            JPanel titleRow = new JPanel(new GridLayout(1, 3, 10, 10));
            titleRow.setBackground(new Color(80, 80, 80)); // light gray
            titleRow.add(title0);
            titleRow.add(title1);
            titleRow.add(title2);
            titleRow.setPreferredSize(new Dimension(0, 60)); // control height of title row

            // --- Bottom row (content panels) ---
            JPanel panelRow = new JPanel(new GridLayout(1, 3, 10, 10));
            panelRow.add(ex1);
            panelRow.add(ex2);
            panelRow.add(ex3);

            // Add rows to main gridp
            gridp.add(titleRow, BorderLayout.NORTH);
            gridp.add(panelRow, BorderLayout.CENTER);

            add(gridp, BorderLayout.CENTER);

       
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setUndecorated(true); // Optional: remove window borders
            setVisible(true);
            gridp.setVisible(true);


            
        }
    public static void main(String[] args) {
        new SecreView("bruh");
        
    }
    
    private void showFormDialog(boolean isEdit) 
    {
        // Form panel
        JPanel formPanel = new JPanel(new BorderLayout(10, 10));
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));

        JTextField nameField = new JTextField(20);
        JTextArea descriptionArea = new JTextArea(5, 20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane descriptionScroll = new JScrollPane(descriptionArea);

        // Pre-fill if editing
        if (isEdit && lastSelectedButton != null) {
            nameField.setText(lastSelectedButton.getText());
            descriptionArea.setText("Edit description here..."); // You can map descriptions per button
        }

        fieldsPanel.add(new JLabel("Name:"));
        fieldsPanel.add(nameField);
        fieldsPanel.add(new JLabel("Description:"));
        fieldsPanel.add(descriptionScroll);

        formPanel.add(fieldsPanel, BorderLayout.CENTER);

        int result = JOptionPane.showConfirmDialog(
                this,
                formPanel,
                isEdit ? "Edit Item" : "Add Item",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText().trim();
            String desc = descriptionArea.getText().trim();

            if (!name.isEmpty()) {
                if (isEdit && lastSelectedButton != null) {
                    // Update existing button
                    lastSelectedButton.setText(name);
                    
                } else {
                    // Create new button
                    JButton newButton = new JButton(name);
                    newButton.setFont(new Font("Consolas", Font.PLAIN, 24));
                    newButton.setAlignmentX(Component.CENTER_ALIGNMENT);

                    newButton.addActionListener(l -> {
                        
                        lastSelectedButton = newButton;
                    });

                    // Example: Add to student panel (ex1)
                    ((JPanel)((JViewport)((JScrollPane)ex1.getComponent(0)).getComponent(0)).getView()).add(newButton);
                    ex1.revalidate();
                    ex1.repaint();

                    
                }
            }
        }
    }



}



//autaaaaaa