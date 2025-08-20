
import java.awt.*;
import javax.swing.*;


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
        private JTextArea description;
        private JButton lastSelectedButton;

        
        public SecreView(String title)
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
            
            ex1 = createRoundedPanel(Color.WHITE, 20, new test1(10, new Color(90, 90, 90)));
            ex1.setLayout(new BorderLayout()); 

            // Create scrollable list panel
            JPanel listPanel = new JPanel();
            listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
            listPanel.setOpaque(false); // For transparency if desired

            // Add many labels to force scrollbars
            for (int i = 1; i <= 30; i++) {
                JButton button = new JButton("• Student ID" + (22390000 + i));
                button.setFont(new Font("Consolas", Font.PLAIN, 24));
                button.setForeground(Color.BLACK); // Use white if dark background
                button.setAlignmentX(Component.CENTER_ALIGNMENT);
                //button.setPreferredSize(new Dimension(300, 600));
                
                
                button.addActionListener
                (
                    l -> 
                    {
                        description.append(button.getText() + "\n");
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




            ex2 = createRoundedPanel(Color.WHITE, 20, new test1(10, new Color(90, 90, 90)));
            ex2.setLayout(new BorderLayout());

            // Create scrollable list panel
            JPanel listPanel2 = new JPanel();
            listPanel2.setLayout(new BoxLayout(listPanel2, BoxLayout.Y_AXIS));
            listPanel2.setOpaque(false); // For transparency if desired

            // Add many labels to force scrollbars
            for (int i = 1; i <= 30; i++) {
                JButton button = new JButton("• Course ID " + (6000 + i));
                button.setFont(new Font("Consolas", Font.PLAIN, 24));
                button.setForeground(Color.BLACK); // Use white if dark background
                button.setAlignmentX(Component.CENTER_ALIGNMENT);
                
                final int num;
                num = i;
                button.addActionListener
                (
                    l -> 
                    {
                        description.append(button.getText() + "\n");
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

            // Add to ex1
            ex2.add(scrollPane2, BorderLayout.CENTER);

            ex3 = createRoundedPanel(Color.WHITE, 20, new test1(10, new Color(90, 90, 90)));
            ex3.setLayout(new BorderLayout());

            description = new JTextArea(45, 55);
            description.setFont(new Font("Consolas", Font.PLAIN,  16));
            description.setText("This is a large block of text...\nYou can scroll down...");
            description.setEditable(true);
            JScrollPane textScroll = new JScrollPane(description);
            ex3.add(textScroll, BorderLayout.CENTER); 
            

            JPanel controlPanel = new JPanel();
            controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

            JButton addBtn = new JButton("➕ Add");
            JButton deleteBtn = new JButton("🗑 Delete");
            JButton editBtn = new JButton("✏ Edit");

            // Add action for Add
            addBtn.addActionListener(e -> {
                String name = JOptionPane.showInputDialog(this, "Enter new button text:");
                if (name != null && !name.trim().isEmpty()) {
                    JButton newButton = new JButton(name);
                    newButton.setFont(new Font("Consolas", Font.PLAIN, 24));
                    newButton.setAlignmentX(Component.CENTER_ALIGNMENT);

                    // add same listener logic
                    newButton.addActionListener(l -> {
                        description.append(newButton.getText() + "\n");
                        lastSelectedButton = newButton;
                    });

                    // For demo, add to listPanel (students). You could also choose ex2 list
                    ((JPanel)((JViewport)((JScrollPane)ex1.getComponent(0)).getComponent(0)).getView()).add(newButton);
                    ex1.revalidate();
                    ex1.repaint();
                }
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
                    String newName = JOptionPane.showInputDialog(this, "Edit button text:", lastSelectedButton.getText());
                    if (newName != null && !newName.trim().isEmpty()) {
                        lastSelectedButton.setText(newName);
                    }
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
            JButton deleteBtn2 = new JButton("🗑 Delete");
            JButton editBtn2 = new JButton("✏ Edit");

            // Add action for Add
            addBtn2.addActionListener(e -> {
                String name = JOptionPane.showInputDialog(this, "Enter new button text:");
                if (name != null && !name.trim().isEmpty()) {
                    JButton newButton = new JButton(name);
                    newButton.setFont(new Font("Consolas", Font.PLAIN, 24));
                    newButton.setAlignmentX(Component.CENTER_ALIGNMENT);

                    // add same listener logic
                    newButton.addActionListener(l -> {
                        description.append(newButton.getText() + "\n");
                        lastSelectedButton = newButton;
                    });

                    // For demo, add to listPanel (students). You could also choose ex2 list
                    ((JPanel)((JViewport)((JScrollPane)ex2.getComponent(0)).getComponent(0)).getView()).add(newButton);
                    ex2.revalidate();
                    ex2.repaint();
                }
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
                    String newName = JOptionPane.showInputDialog(this, "Edit button text:", lastSelectedButton.getText());
                    if (newName != null && !newName.trim().isEmpty()) {
                        lastSelectedButton.setText(newName);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No button selected!");
                }
            });

            controlPanel2.add(addBtn2);
            controlPanel2.add(deleteBtn2);
            controlPanel2.add(editBtn2);

            ex2.add(controlPanel2, BorderLayout.SOUTH);

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
        new SecreView("bruh");
        
    }

    private JPanel createRoundedPanel(Color bgColor, int arc, test1 border) 
    {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
                g2.dispose();

                super.paintComponent(g); // Paint child components on top!

            }

            @Override
            public boolean isOpaque() {
                return false;
            }
        };

        panel.setOpaque(false);
        panel.setBackground(bgColor);
        if (border != null) {
            panel.setBorder(border);
        }
        return panel;
    }

}


//autaaaaaa