package Letterboxd;

import javax.swing.SwingUtilities;

public class LetterboxdGUI {

    public static void main(String[] args) {

        Library lib = new Library();

        try {
            fileManager.loadData();
        } catch (java.io.IOException e) {
            System.out.println("No previous save file found. Starting fresh.");
        }

        SwingUtilities.invokeLater(() -> new MainMenuFrame(lib));
    }
}
