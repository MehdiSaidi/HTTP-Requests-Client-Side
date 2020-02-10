package Arguments;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import Helper.Helper;

public class FileInlineData {
    public static boolean active = false;

    public static String applyArgument(String path, String[] args) throws IOException {

        if (!active)
            return "";

        if (args[args.length - 2].equals("-f"))
            Helper.help("Missing URL, please try again!");

        Character start = path.charAt(0);
        Character end = path.charAt(path.length() - 1);

        if (!start.equals('\'') && !end.equals('\''))
            Helper.help("The path should be enclosed by single quotes or you're missing a value for -f");

        path = path.substring(1, path.length() - 1);

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