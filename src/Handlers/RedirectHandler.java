package Handlers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import Client.Client;

public class RedirectHandler {

    public static void handleRedirect(String response, String[] args) throws URISyntaxException, IOException {
        // Getting the status line and checking for a Redirect Code (3XX)
        String[] responseArr = response.split("\r\n");
        String[] statusLine = responseArr[0].split(" ");
        String statusCode = statusLine[1];

        // Not a redirect
        if (!(statusCode.charAt(0) == '3'))
            return;

        // Get the Location Header
        String locationHeader = "";

        for (String s : responseArr) {
            if (s.contains("Location:")) {
                locationHeader = s;
                break;
            }
        }

        // Check if it's just a path (Relative Redirect) or if the Host is different
        String location = locationHeader.substring(locationHeader.indexOf(" ") + 1);
        URI redirectLocation;
        redirectLocation = new URI(location);

        // Construct new args for the redirect
        String host = redirectLocation.getHost() == null ? RequestHandler.web : redirectLocation.getHost();
        String protocol = host != "" ? "http://" : "";
        String path = redirectLocation.getPath();

        String url = protocol + host + path;

        args[args.length - 1] = "\"" + url + "\"";

        String[] redirectArgs = args;

        RequestHandler.reset();
        RequestHandler.handleRequest(redirectArgs);
        String redirectResponse = Client.sendRequest(RequestHandler.requestMessage);
        ResponseHandler.handleResponse(redirectResponse, redirectArgs);

    }

}