package Letterboxd;

import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class fileManager {

    private static final String FileName = "library.txt";

    public static void saveData(Library lib) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(FileName));

        writer.println("=== Content ===");
        Content[] contents = lib.getContents();
        for (int i = 0; i < lib.getContentCount(); i++) {
            writer.println(contents[i].toString());
        }

        writer.println("=== Users ===");
        lib.getUsers().printAll(writer);

        writer.close();
    }

    public static void loadData() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FileName));
        System.out.println("--- Previously saved data ---");
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println("-----------------------------");
        reader.close();
    }
}
