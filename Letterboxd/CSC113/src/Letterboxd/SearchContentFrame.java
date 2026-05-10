package Letterboxd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class SearchContentFrame extends JFrame implements ActionListener {

    private Library lib;

    private JTextField searchField;
    private JButton    searchBtn;
    private JTextArea  resultArea;

    public SearchContentFrame(Library lib) {
        this.lib = lib;
        initComponents();
    }

    private void initComponents() {
        setTitle("Search Content");
        setSize(380, 320);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        JPanel topPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        topPanel.add(new JLabel("Content Title:"));
        searchField = new JTextField();
        topPanel.add(searchField);
        searchBtn = new JButton("Search");
        searchBtn.addActionListener(this);
        topPanel.add(searchBtn);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        resultArea.setMargin(new Insets(6, 6, 6, 6));

        add(topPanel,                     BorderLayout.NORTH);
        add(new JScrollPane(resultArea),  BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String keyword = searchField.getText().trim();
        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a title to search.");
            return;
        }

        Content result = lib.searchContent(keyword);
        if (result == null) {
            resultArea.setText("Content not found.");
            return;
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(baos));
        result.displayReviews();
        System.setOut(oldOut);
        String reviews = baos.toString().trim();

        resultArea.setText(
            result.toString() + "\n\n" +
            "=== Reviews ===\n" +
            (reviews.isEmpty() ? "No reviews yet." : reviews)
        );
    }
}
