package Letterboxd;

import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ShowAllContentFrame extends JFrame {

    public ShowAllContentFrame(Library lib) {
        initComponents(lib);
    }

    private void initComponents(Library lib) {
        setTitle("All Content");
        setSize(450, 340);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(baos));
        lib.displayContent();
        System.setOut(oldOut);
        String all = baos.toString().trim();

        JTextArea area = new JTextArea(all.isEmpty() ? "No content added yet." : all);
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 12));
        area.setMargin(new Insets(6, 6, 6, 6));

        add(new JScrollPane(area), BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
