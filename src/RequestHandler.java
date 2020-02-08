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

        String stringURL = args [2];
        URL urlObject = new URL(stringURL);

        web = urlObject.getHost();
        web = "www." + web;

        urlPath = urlObject.getPath();


        requestMessage = method + " " + urlPath + " " + version;

        method = args [1].toUpperCase();

        requestMessage = method + " ";

        boolean verbose = false;
        boolean header = false;
        boolean file = false;
        boolean data = false;


        requestMessage = method;

        ArrayList<String> headersArray = new ArrayList<String>();
        String entityBody;


        if(!args[2].contains("http")) {


            for (int i = 2; !args[i].contains("http"); i++) {
                if(args[i].equals("-v")){

                    if(Verbose.active == true){
                        Helper.help();
                        break;
                    }
                    Verbose.active = true;
                }

                if(args[i].equals("-h")){
                    headersArray.add(args[i+1]);

                    i++;
                    continue;
                }

                if( args[i].equals("-d")){
                    if(file || data){
                        Helper.help();
                        break;
                    }

                    data = true;

                    entityBody = args[i+1];
                    i++;
                    continue;
                }

                if(args[i].equals("-f")){
                    if(data == true){
                        Helper.help();
                        break;
                    }
                }
            }

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



        http://httpbin.org/status/418






        // 2) Get arguments

        // 3) Get URL and path (Use URL and URI classes)

        // 4) Put together the request message with the correct format

        return null;
    }

    // TODO: Implement this method so that it handles any errors in the user input.


    private static void handleErrors(String[] args) {

    }
}
