import Arguments.*;
import java.net.URL;
import java.net.URI;

public class RequestHandler {

    public static String requestMessage;
    public static String web;
    private static String method;
    private static String urlPath;

    public static String handleRequest(String[] args) {

        // Handle any errors
        // ? Maybe we don't need this method and we can just do it all here
        // ? If we're going through the String Array anyway
        handleErrors(args);

        /*
         * If we get to here it means everything is fine with what the user entered,
         * then it's all about going through the array and getting the appropriate
         * things
         */

        // 1) Get method (GET or POST)
        method = args[1].toUpperCase();

        // 2) Get arguments

        // 3) Get URL and path (Use URL and URI classes)

        // 4) Put together the request message with the correct format

        return null;
    }

    // TODO: Implement this method so that it handles any errors in the user input.

    private static void handleErrors(String[] args) {

    }
}
