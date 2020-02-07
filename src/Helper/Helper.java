package Helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Helper {

    static File helpFile = new File("Helper/help.txt");
    static File helpGetFile = new File("Helper/help_get.txt");
    static File helpPostFile = new File("Helper/help_post.txt");

    public static void help() {

        try {
            Scanner readHelp = new Scanner(helpFile);

            while (readHelp.hasNextLine()) {
                System.out.println(readHelp.nextLine());
            }

            readHelp.close();
        }

        catch (FileNotFoundException e) {
            System.out.println("File does not exist");
        }

    }

    public static void helpGet() {

        try {
            Scanner readHelpGet = new Scanner(helpGetFile);

            while (readHelpGet.hasNextLine()) {
                System.out.println(readHelpGet.nextLine());
            }

            readHelpGet.close();
        }

        catch (FileNotFoundException e) {
            System.out.println("The file does not exist");
        }
    }

    public static void helpPost() {
        try {
            Scanner readHelpPost = new Scanner(helpPostFile);

            while (readHelpPost.hasNextLine()) {
                System.out.println(readHelpPost.nextLine());
            }

            readHelpPost.close();
        }

        catch (FileNotFoundException e) {
            System.out.println("The file does not exist");
        }
    }
}
