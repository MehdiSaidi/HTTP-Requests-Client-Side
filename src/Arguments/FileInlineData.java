package Arguments;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileInlineData {
    public static boolean active = false;

    public static String applyArgument(String path) throws IOException {

        if (active)
            return "";

        FileInputStream file = new FileInputStream(path);
        BufferedReader in = new BufferedReader(new InputStreamReader(file));

        int data = in.read();
        String entityBody = "";

        while (data != -1) {
            char c = (char) data;
            entityBody += c;
            data = in.read();
        }

        in.close();

        return entityBody;
    }
}