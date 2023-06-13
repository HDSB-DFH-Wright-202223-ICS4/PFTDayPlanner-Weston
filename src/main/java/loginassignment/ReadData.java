package loginassignment;

import java.io.File; // Import the File class
import java.io.FileReader;
import java.io.IOException; // Import the IOException class to handle errors
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class ReadData {

    public static List<String> SavedUsernames = new LinkedList<>();
    public static List<String> SavedPasswords = new LinkedList<>();

    public static void main(String[] args) {

    }

    public static void UpdateSavedInfo() {
        try {
            File myObj = new File("savedata.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
                // So if the file hasn't been created. then create it.
            } else {
                System.out.println("File already exists.");
                // So if file already exists, then read from it.
                Path of = Path.of("savedata.txt");
                String content = Files.readString(of);

                List<String> lines = Files.readAllLines(of, Charset.defaultCharset());
                for (String line : lines) {
                    System.out.println(line);
                    for(int i=0; i <line.length(); i++)
                    {
                        if (line.charAt(i) == ',') {// Username
                            String username = line.substring(0, i);
                            String password = line.substring(i + 1, line.length());
                            SavedUsernames.add(ReverseString(username));
                            SavedPasswords.add(ReverseString(password));
                        }
                    }
                }

            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static String ReverseString(String text)
    {
        char ch;
        String nstr = "";

        for(int i=0; i < text.length(); i++) {
            ch = text.charAt(i);
            nstr = ch+nstr;
        }

        return nstr;
    }

}
