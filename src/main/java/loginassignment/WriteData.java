package loginassignment;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class WriteData {

    public static void WriteNewAccount(String username, String password) throws IOException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("savedata.txt", true));
            writer.append(ReverseString(username) + "," + ReverseString(password) + "\n");
            writer.close();
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
