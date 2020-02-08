package Handlers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import Arguments.FileInlineData;
import Arguments.Header;
import Arguments.Verbose;
import Helper.Helper;

public class RequestHandler {

    public static String requestMessage;
    public static String web;
    private static String method;
    private static String urlPath;
    private static String httpVersion = "HTTP/1.0";
    private static String entityBody = "";
    private static String headers = "";

    public static void handleRequest(String[] args) throws IOException, URISyntaxException {

        // 1) Get method (GET or POST)
        method = args[0];

        if (method.contains("help")) {
            if (args[1].contains("get"))
                Helper.helpGet();
            else if (args[1].contains("post"))
                Helper.helpPost();
        } else
            method = method.toUpperCase();

        // 2) Get arguments
        int urlIndex = handleArguments(args);

        // 3) Get URL and path (Use URL and URI classes)
        // TODO: I think there might be something wrong with how we are getting the URL
        web = args[urlIndex];

        // TODO: Hacky way of doing it, let's keep an eye on this
        web = web.replaceAll("'", "");

        URL urlObject = new URL(web);

        web = urlObject.getHost();
        // TODO: If in the command line the user already enter a URL with www.
        // this will add an extra www. so it will become -> www.www.
        web = "www." + web;

        urlPath = urlObject.getFile();

        // 4) Put together the request message with the correct format
        requestMessage = method + " " + urlPath + " " + httpVersion + "\r\n" + headers + entityBody + "\r\n\r\n";

    }

    private static int handleArguments(String[] args) throws IOException {
        boolean data = false;
        ArrayList<String> headerArr = new ArrayList<String>();
        int i = 1;

        // TODO: Fix -> The URL is not always going to contain http
        if (!args[1].contains("http")) {
            for (i = 1; i < args.length && !args[i].contains("http"); i++) {

                // Argument -v
                if (args[i].equals("-v")) {

                    if (Verbose.active) {
                        Helper.help();
                        break;
                    }
                    Verbose.active = true;
                    continue;
                }

                // Argument -h
                if (args[i].equals("-h")) {
                    String headerFieldName = args[i + 1].substring(0, args[i + 1].indexOf(":") + 1);
                    String headerValue = args[i + 1].substring(args[i + 1].indexOf(":") + 1);
                    String header = headerFieldName + " " + headerValue;

                    Header.active = true;

                    headerArr.add(header);

                    i++;
                    continue;
                }
                // TODO Create InlineData class and fix the issue below
                // Argument -d
                if (args[i].equals("-d")) {
                    if (FileInlineData.active || data) {
                        Helper.help();
                        break;
                    }

                    data = true;

                    entityBody = args[i + 1];

                    // TODO: Fix this. It's not always going to be in JSON format {Assignment : 1}
                    // it's just a string
                    if (!entityBody.contains("}")) {
                        i = i + 2;
                        while (!args[i].contains("-d") && !args[i].contains("-v") && !args[i].contains("-h")
                                && !args[i].contains("http")) {

                            entityBody = entityBody + args[i];
                            i++;
                        }
                    }
                    continue;
                }

                // Argument -f
                if (args[i].equals("-f")) {
                    if (data || FileInlineData.active) {

                        Helper.help();
                        break;
                    }

                    FileInlineData.active = true;

                    entityBody = FileInlineData.applyArgument(args[i + 1]);

                    i++;
                    continue;
                }

                // If you didn't match with any of these if's, error in your input
                else {
                    Helper.help();
                    break;
                }
            }

        }

        headers = Header.applyArgument(headerArr);

        return i;
    }
}
