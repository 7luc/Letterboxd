package Letterboxd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SearchFrame extends JFrame implements ActionListener {

    private Library lib;

    private JTextField searchField;
    private JButton searchBtn;
    private JTextArea resultArea;

    public SearchFrame(Library lib) {
        this.lib = lib;

        setTitle("Search Content");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));

        JPanel topPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        topPanel.add(new JLabel("Content Title:"));
        searchField = new JTextField();
        topPanel.add(searchField);

        searchBtn = new JButton("Search");
        searchBtn.addActionListener(this);
        topPanel.add(new JLabel());
        topPanel.add(searchBtn);

        add(topPanel, BorderLayout.NORTH);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == searchBtn) {
            String keyword = searchField.getText().trim();

            if (keyword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a title to search");
                return;
            }

            Content result = lib.searchContent(keyword);

            if (result == null) 
                resultArea.setText("No content found for: " + keyword);
            else 
                resultArea.setText(result.toString());
         
        }
    }
}
