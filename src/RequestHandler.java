import Arguments.*;
import Helper.Helper;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URI;

import java.util.ArrayList;

public class RequestHandler {

    public static String requestMessage;
    public static String web;
    private static String method;
    private static String urlPath;
    private static String version = "HTTP/1.0";

    public static String handleRequest(String[] args) throws MalformedURLException {
        String stringURL;
        boolean file = false;
        boolean data = false;
        String entityBody = null;
        ArrayList<String> headersArray = new ArrayList<String>();

        method = args[1].toUpperCase();

        //------------------------------------------------------------------------- If there's arguments
        if (!args[2].contains("http")) {
            int i;

            for (i = 2; i < args.length; i++) {


                if(args[i].equals("-v")){

                    if (Verbose.active) {
                        Helper.help();
                        break;
                    }
                    Verbose.active = true;
                }

                if (args[i].equals("-h")) {
                    headersArray.add(args[i + 1]);

                    i++;
                    continue;
                }
                //--------------------------- Argument -d
                if (args[i].equals("-d")) {
                    if (file || data) {
                        Helper.help();
                        break;
                    }

                    data = true;

                    entityBody = args[i+1];


                    if(!entityBody.contains("}")){
                        i = i+2;
                        while(!args[i].contains("-d") && !args[i].contains("-v") && !args[i].contains("-h") &&
                                !args[i].contains("http")){

                            entityBody = entityBody + args [i];
                            i++;
                        }
                    }
                    continue;
                }

                //--------------------------- Argument -f
                if(args[i].equals("-f")){
                    if(data || file){

                        Helper.help();
                        break;
                    }

                    file = true;

                    //entityBody = File.applyArgument(args[i+1]);

                    i++;
                    continue;
                }

                //----------------If you didn't match with any of these if's, error in your input
                else{
                    Helper.help();
                    break;
                }
            }

            stringURL = args[i-1];
            URL urlObject = new URL(stringURL);

            web = urlObject.getHost();
            web = "www." + web;

            urlPath = urlObject.getPath();

            requestMessage = method + " " + urlPath + " " + version + "\r\n";

        }

        //---------------------------------------------------------------------- Otherwise if no arguments
        else{
            requestMessage = method + " " + urlPath + " " + version + "\r\n";
        }

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

        http: // httpbin.org/status/418

        // 2) Get arguments

        // 3) Get URL and path (Use URL and URI classes)

        // 4) Put together the request message with the correct format

        return null;
    }

    // TODO: Implement this method so that it handles any errors in the user input.

    private static void handleErrors(String[] args) {

    }
}
