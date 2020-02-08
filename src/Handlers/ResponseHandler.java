package Handlers;

import Arguments.Verbose;

public class ResponseHandler {

    public static void handleResponse(String response) {

        response = Verbose.applyArgument(response);

        System.out.println("\n" + response);
    }

}
