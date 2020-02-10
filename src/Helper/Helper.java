package Helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Helper {

    static File helpFile = new File("src/Helper/help.txt");
    static File helpGetFile = new File("src/Helper/help_get.txt");
    static File helpPostFile = new File("src/Helper/help_post.txt");

    public static void help() throws FileNotFoundException {

        Scanner readHelp = new Scanner(helpFile);

        while (readHelp.hasNextLine()) {
            System.out.println(readHelp.nextLine());
        }
        readHelp.close();
        System.exit(0);
    }

    public static void help(String errMsg) throws FileNotFoundException {

        Scanner readHelp = new Scanner(helpFile);

        if (errMsg != null) {
            System.err.println("\n" + errMsg);
        }

        while (readHelp.hasNextLine()) {
            System.out.println(readHelp.nextLine());
        }
        readHelp.close();

        System.exit(0);
    }

    public static void helpGet() throws FileNotFoundException {

        Scanner readHelpGet = new Scanner(helpGetFile);

        while (readHelpGet.hasNextLine()) {
            System.out.println(readHelpGet.nextLine());
        }

        readHelpGet.close();
        System.exit(0);
    }

    public static void helpPost() throws FileNotFoundException {

        Scanner readHelpPost = new Scanner(helpPostFile);

        while (readHelpPost.hasNextLine()) {
            System.out.println(readHelpPost.nextLine());
        }

        readHelpPost.close();
        System.exit(0);
    }
}
