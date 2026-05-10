package Letterboxd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreateUserFrame extends JFrame implements ActionListener {

    private Library lib;

    private JTextField usernameField;
    private JButton    createBtn;

    public CreateUserFrame(Library lib) {
        this.lib = lib;
        initComponents();
    }

    private void initComponents() {
        setTitle("Create User");
        setSize(280, 120);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(new GridLayout(2, 2, 5, 5));

        usernameField = new JTextField();
        createBtn     = new JButton("Create");

        createBtn.addActionListener(this);

        add(new JLabel("Username:")); add(usernameField);
        add(new JLabel(""));          add(createBtn);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = usernameField.getText().trim();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username cannot be empty.");
            return;
        }
        if (name.contains(" ")) {
            JOptionPane.showMessageDialog(this, "Username cannot contain spaces.");
            return;
        }
        if (lib.searchUser(name) != null) {
            JOptionPane.showMessageDialog(this, "Username already exists.");
            return;
        }

        lib.addUser(new User(name));
        JOptionPane.showMessageDialog(this, "User created successfully!");
        usernameField.setText("");
    }
}
