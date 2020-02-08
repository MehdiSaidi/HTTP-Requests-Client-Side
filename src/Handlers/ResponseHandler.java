package Handlers;

import Arguments.Verbose;

public class ResponseHandler {

    public static void handleResponse(String response) {
        Verbose.applyArgument(response);
    }

}
