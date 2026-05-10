package Letterboxd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenuFrame extends JFrame implements ActionListener {

    private Library lib;

    private JButton btnAddContent;
    private JButton btnRateContent;
    private JButton btnSearchContent;
    private JButton btnShowAll;
    private JButton btnCreateUser;
    private JButton btnSearchUser;

    public MainMenuFrame(Library lib) {
        this.lib = lib;
        initComponents();
    }

    private void initComponents() {
        setTitle("Letterboxd");
        setSize(300, 320);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JLabel header = new JLabel("Letterboxd");
        header.setFont(new Font("Arial", Font.BOLD, 20));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(14));
        add(header);
        add(Box.createVerticalStrut(12));
        add(createButtonPanel());
        add(Box.createVerticalStrut(12));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    fileManager.saveData(lib);
                    System.out.println("Data saved to library.txt");
                } catch (java.io.IOException ex) {
                    System.out.println("Error saving file: " + ex.getMessage());
                }
                dispose();
                System.exit(0);
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 1, 6, 6));
        panel.setBorder(BorderFactory.createTitledBorder("Main Menu"));

        btnAddContent     = new JButton("Add Content");
        btnRateContent    = new JButton("Rate Content");
        btnSearchContent  = new JButton("Search Content");
        btnShowAll        = new JButton("Show All Content");
        btnCreateUser     = new JButton("Create User");
        btnSearchUser     = new JButton("Search User");

        btnAddContent   .addActionListener(this);
        btnRateContent  .addActionListener(this);
        btnSearchContent.addActionListener(this);
        btnShowAll      .addActionListener(this);
        btnCreateUser   .addActionListener(this);
        btnSearchUser   .addActionListener(this);

        panel.add(btnAddContent);
        panel.add(btnRateContent);
        panel.add(btnSearchContent);
        panel.add(btnShowAll);
        panel.add(btnCreateUser);
        panel.add(btnSearchUser);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if      (src == btnAddContent)    new AddContentFrame(lib);
        else if (src == btnRateContent)   new RateContentFrame(lib);
        else if (src == btnSearchContent) new SearchContentFrame(lib);
        else if (src == btnShowAll)       new ShowAllContentFrame(lib);
        else if (src == btnCreateUser)    new CreateUserFrame(lib);
        else if (src == btnSearchUser)    new SearchUserFrame(lib);
    }
}
