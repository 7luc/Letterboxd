package Letterboxd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RateContentFrame extends JFrame implements ActionListener {

    private Library lib;

    private JTextField contentField;
    private JTextField userField;
    private JTextField ratingField;
    private JTextField commentField;
    private JButton    submitBtn;

    public RateContentFrame(Library lib) {
        this.lib = lib;
        initComponents();
    }

    private void initComponents() {
        setTitle("Rate Content");
        setSize(300, 220);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(new GridLayout(5, 2, 5, 5));

        contentField = new JTextField();
        userField    = new JTextField();
        ratingField  = new JTextField();
        commentField = new JTextField();
        submitBtn    = new JButton("Submit");

        submitBtn.addActionListener(this);

        add(new JLabel("Content Title:")); add(contentField);
        add(new JLabel("Username:"));      add(userField);
        add(new JLabel("Rating (1-10):")); add(ratingField);
        add(new JLabel("Comment:"));       add(commentField);
        add(new JLabel(""));               add(submitBtn);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String contentName = contentField.getText().trim();
        String userName    = userField.getText().trim();
        String ratingStr   = ratingField.getText().trim();
        String comment     = commentField.getText().trim();

        if (contentName.isEmpty() || userName.isEmpty() || ratingStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Content title, username, and rating are required.");
            return;
        }

        Content c = lib.searchContent(contentName);
        if (c == null) {
            JOptionPane.showMessageDialog(this, "Content not found.");
            return;
        }

        User u = lib.searchUser(userName);
        if (u == null) {
            JOptionPane.showMessageDialog(this, "User not found.");
            return;
        }

        try {
            int rating = Integer.parseInt(ratingStr);
            Review r = new Review(rating, comment, u);
            u.addReview(r);
            c.addReview(r);
            JOptionPane.showMessageDialog(this, "Review added successfully!");
            contentField.setText("");
            userField   .setText("");
            ratingField .setText("");
            commentField.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Rating must be a number.");
        } catch (InvalidRatingException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}
