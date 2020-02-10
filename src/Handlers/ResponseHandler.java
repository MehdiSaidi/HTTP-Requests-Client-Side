package Handlers;

import java.io.IOException;
import java.net.URISyntaxException;

import Arguments.Output;
import Arguments.Verbose;

public class ResponseHandler {

    public static void handleResponse(String response, String[] args) throws URISyntaxException, IOException {

        String parsedResponse = Verbose.applyArgument(response);

        if (!parsedResponse.equals("\r\n\r\n"))
            System.out.println("\n" + Verbose.applyArgument(response));

        Output.applyArgument(response);

        RedirectHandler.handleRedirect(response, args);
    }

}
