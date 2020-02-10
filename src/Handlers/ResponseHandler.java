package Handlers;

import java.io.IOException;
import java.net.URISyntaxException;

import Arguments.Verbose;

public class ResponseHandler {

    public static void handleResponse(String response, String[] args) throws URISyntaxException, IOException {

        response = Verbose.applyArgument(response);

        System.out.println("\n" + response);

        RedirectHandler.handleRedirect(response, args);
    }

}
