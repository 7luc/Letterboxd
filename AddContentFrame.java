package Letterboxd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddContentFrame extends JFrame implements ActionListener {

    private Library lib;

    private JTextField titleField;
    private JTextField ageField;
    private JTextField durationField;

    private JButton addBtn;
    private JButton openSearchBtn;

    public AddContentFrame(Library lib) {
        this.lib = lib;

        setTitle("Add Movie");
        setSize(350, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 5, 5));

        add(new JLabel("Title:"));
        titleField = new JTextField();
        add(titleField);

        add(new JLabel("Age Rating:"));
        ageField = new JTextField();
        add(ageField);

        add(new JLabel("Duration (min):"));
        durationField = new JTextField();
        add(durationField);

        addBtn = new JButton("Add Content");
        addBtn.addActionListener(this);
        add(addBtn);

        openSearchBtn = new JButton("Open Search Window");
        openSearchBtn.addActionListener(this);
        add(openSearchBtn);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addBtn) {
            String title = titleField.getText().trim();
            String ageText = ageField.getText().trim();
            String durText = durationField.getText().trim();

            if (title.isEmpty() || ageText.isEmpty() || durText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }

            try {
                int age = Integer.parseInt(ageText);
                int duration = Integer.parseInt(durText);

                Movie m = new Movie(title, age, duration);
                lib.addContent(m);

                JOptionPane.showMessageDialog(this, "Movie added successfully!");

                titleField.setText("");
                ageField.setText("");
                durationField.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Age and Duration must be numbers.");
            }
        }

        if (e.getSource() == openSearchBtn) {
            new SearchFrame(lib);
        }
    }
}
