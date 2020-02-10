package Handlers;

import java.io.IOException;
import java.net.URISyntaxException;

import Arguments.Output;
import Arguments.Verbose;

public class ResponseHandler {

    public static void handleResponse(String response, String[] args) throws URISyntaxException, IOException {

        System.out.println("\n" + Verbose.applyArgument(response));

        RedirectHandler.handleRedirect(response, args);

        if (Output.active)
            Output.applyArgument(response);
        else
            System.out.println("\n" + response);
    }

}
