package Letterboxd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddContentFrame extends JFrame implements ActionListener {

    private Library lib;

    private JComboBox<String> typeBox;
    private JTextField        titleField;
    private JTextField        ageField;
    private JTextField        durationField;
    private JTextField        partsField;
    private JTextField        seasonsField;
    private JLabel            durationLabel;
    private JLabel            partsLabel;
    private JLabel            seasonsLabel;
    private JButton           addBtn;

    public AddContentFrame(Library lib) {
        this.lib = lib;
        initComponents();
    }

    private void initComponents() {
        setTitle("Add Content");
        setSize(320, 290);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(new GridLayout(8, 2, 5, 5));

        typeBox       = new JComboBox<>(new String[]{"Movie", "Series", "Movie Series"});
        titleField    = new JTextField();
        ageField      = new JTextField();
        durationLabel = new JLabel("Duration (min):");
        durationField = new JTextField();
        partsLabel    = new JLabel("Number of Parts:");
        partsField    = new JTextField();
        seasonsLabel  = new JLabel("Number of Seasons:");
        seasonsField  = new JTextField();
        addBtn        = new JButton("Add");

        typeBox.addActionListener(e -> updateFieldVisibility());
        addBtn.addActionListener(this);

        add(new JLabel("Type:"));    add(typeBox);
        add(new JLabel("Title:"));   add(titleField);
        add(new JLabel("Age Rating:")); add(ageField);
        add(durationLabel);          add(durationField);
        add(partsLabel);             add(partsField);
        add(seasonsLabel);           add(seasonsField);
        add(new JLabel(""));         add(addBtn);

        updateFieldVisibility();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateFieldVisibility() {
        String type = (String) typeBox.getSelectedItem();

        boolean isMovie       = "Movie".equals(type);
        boolean isSeries      = "Series".equals(type);
        boolean isMovieSeries = "Movie Series".equals(type);

        durationLabel.setVisible(isMovie || isMovieSeries);
        durationField.setVisible(isMovie || isMovieSeries);
        partsLabel   .setVisible(isMovieSeries);
        partsField   .setVisible(isMovieSeries);
        seasonsLabel .setVisible(isSeries);
        seasonsField .setVisible(isSeries);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String type  = (String) typeBox.getSelectedItem();
        String title = titleField.getText().trim();
        String ageStr = ageField.getText().trim();

        if (title.isEmpty() || ageStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Title and Age Rating are required.");
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Age Rating must be a number.");
            return;
        }

        if (lib.searchContent(title) != null) {
            JOptionPane.showMessageDialog(this, "Content with this title already exists.");
            return;
        }

        try {
            if ("Movie".equals(type)) {
                int duration = Integer.parseInt(durationField.getText().trim());
                lib.addContent(new Movie(title, age, duration));

            } else if ("Series".equals(type)) {
                int seasons = Integer.parseInt(seasonsField.getText().trim());
                lib.addContent(new Series(title, age, seasons));

            } else {
                int duration = Integer.parseInt(durationField.getText().trim());
                int parts    = Integer.parseInt(partsField.getText().trim());
                if (parts <= 1) {
                    JOptionPane.showMessageDialog(this, "Movie Series must have more than 1 part.");
                    return;
                }
                lib.addContent(new MovieSerise(title, age, duration, parts));
            }

            JOptionPane.showMessageDialog(this, "Content added successfully!");
            titleField.setText("");
            ageField.setText("");
            durationField.setText("");
            partsField.setText("");
            seasonsField.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for all numeric fields.");
        }
    }
}
