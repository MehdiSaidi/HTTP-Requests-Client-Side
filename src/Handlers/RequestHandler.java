package Handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import Arguments.ArgumentHash;
import Arguments.FileInlineData;
import Arguments.Header;
import Arguments.InlineData;
import Arguments.Verbose;
import Helper.Helper;

public class RequestHandler {

    public static String requestMessage;
    public static String web;
    public static String entityBody = "";
    private static String method;
    private static String urlPath;
    private static String httpVersion = "HTTP/1.0\r\n";
    private static String headers = "";

    public static void handleRequest(String[] args) throws IOException, URISyntaxException {
        // 1) Get method (GET or POST)
        setMethod(args);

        // 2) Get URL
        setURL(args);

        // 3) Get arguments
        handleArguments(args);

        // 4) add any Default headers if necessary
        addDefaultHeaders();

        // 5) Put together the request message with the correct format
        requestMessage = method + " " + urlPath + " " + httpVersion + headers + "\r\n" + entityBody;
    }

    private static void handleArguments(String[] args) throws IOException {
        ArrayList<String> headerArr = new ArrayList<String>();
        int i = 1;

        if (!args[1].contains("http") && ArgumentHash.arguments.contains(args[1])) {

            for (i = 1; i < args.length; i++) {

                // ---------- Argument -v ----------
                if (args[i].equals("-v")) {

                    if (Verbose.active) {
                        Helper.help();
                        break;
                    }
                    Verbose.active = true;
                    continue;
                }

                // ---------- Argument -h ----------
                if (args[i].equals("-h")) {
                    String headerFieldName = args[i + 1].substring(0, args[i + 1].indexOf(":") + 1);
                    String headerValue = args[i + 1].substring(args[i + 1].indexOf(":") + 1);
                    String header = headerFieldName + " " + headerValue;

                    Header.active = true;

                    headerArr.add(header);

                    i++;
                    continue;
                }

                // ---------- Argument -d ----------
                if (args[i].equals("-d")) {
                    if (FileInlineData.active || InlineData.active) {
                        Helper.help();
                        break;
                    }

                    InlineData.active = true;

                    i++;
                    i = InlineData.applyArgument(i, args);

                    continue;
                }

                // ---------- Argument -f ----------
                if (args[i].equals("-f")) {
                    if (InlineData.active || FileInlineData.active) {

                        Helper.help();
                        break;
                    }

                    FileInlineData.active = true;

                    entityBody = FileInlineData.applyArgument(args[i + 1]);

                    continue;
                }

                // If you didn't match with any of these if's, error in your input
                else {
                    break;
                }
            }

        }

        headers += Header.applyArgument(headerArr);
        entityBody = (entityBody.length() > 0) ? entityBody.substring(1, entityBody.length() - 1) : "";
    }

    private static void setURL(String[] args) throws IOException {
        boolean isURLValid = false;

        web = args[args.length - 1];
        Character start = web.charAt(0);
        Character end = web.charAt(web.length() - 1);

        if(start.equals("'"))
            Helper.help("The URL cannot be wrapped by single quotation marks, please add double quotation marks.");

        web = (start.equals('\"') && end.equals('\"')) ? web.substring(1, web.length() - 1) : web;

        // --- the purpose of this loop is to retry once the exception has been catched.
        while (!isURLValid) {
            try {
                URL urlObject = new URL(web);

                web = urlObject.getHost();
                urlPath = urlObject.getFile();
                isURLValid = true;

            } catch (MalformedURLException e) {
                web = "http://" + web;
            }
        }
    }

    private static void setMethod(String[] args) throws FileNotFoundException {
        method = args[0];

        if (method.equals("help")) {
            if (args[1].equals("get"))
                Helper.helpGet();
            else if (args[1].equals("post"))
                Helper.helpPost();
        } else
            method = method.toUpperCase();

        if(!method.equals("GET") && !method.equals("POST"))
            Helper.help();
    }

    private static void addDefaultHeaders() throws IOException {
        if (!headers.contains("Host"))
            headers = headers + "Host:" + web + "\r\n";

        if(!headers.contains("Content-Length"))
            headers = headers + "Content-Length:" + entityBody.length() + "\r\n";

        String ContentLengthNum = headers.substring(headers.indexOf("Length:"),headers.indexOf("\r\n"));
        int ContentLengthNumber = Integer.parseInt(ContentLengthNum.replaceAll("\\D", ""));

        if(ContentLengthNumber > entityBody.length())
            Helper.help("The content-length entered must be smaller or equal to the content-length of the entity body");
    }
}
