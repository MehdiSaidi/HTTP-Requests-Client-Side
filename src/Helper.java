import java.util.*;
import java.io.*;

public class Helper {

    static File HelpFile = new File("src/help.txt");
    static File HelpGetFile = new File("src/help_get.txt");
    static File HelpPostFile = new File("src/help_post.txt");


    public static void help(){

        try{
            Scanner ReadHelp = new Scanner(HelpFile);

            while(ReadHelp.hasNextLine()){
                System.out.println(ReadHelp.nextLine());
            }

            ReadHelp.close();
        }

        catch(FileNotFoundException e){
            System.out.println("File does not exist");
        }

    }

    public static void helpGet(){

        try {
            Scanner ReadHelpGet = new Scanner(HelpGetFile);

            while(ReadHelpGet.hasNextLine()){
                System.out.println(ReadHelpGet.nextLine());
            }

            ReadHelpGet.close();
        }

        catch(FileNotFoundException e){
            System.out.println("The file does not exist");
        }
    }

    public static void helpPost(){
        try {
            Scanner ReadHelpPost = new Scanner(HelpPostFile);

            while(ReadHelpPost.hasNextLine()){
                System.out.println(ReadHelpPost.nextLine());
            }

            ReadHelpPost.close();
        }

        catch(FileNotFoundException e){
            System.out.println("The file does not exist");
        }
    }
}
