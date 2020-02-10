package Handlers;

import java.io.IOException;
import java.net.URISyntaxException;

import Arguments.Output;
import Arguments.Verbose;

public class ResponseHandler {

    public static void handleResponse(String response, String[] args) throws URISyntaxException, IOException {

        RedirectHandler.handleRedirect(response, args);

        response = Verbose.applyArgument(response);

        System.out.println("\n" + response);

        Output.applyArgument(response);

    }

}
