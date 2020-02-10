package Handlers;

import Arguments.Output;
import Arguments.Verbose;

import javax.imageio.IIOException;
import java.io.IOException;

public class ResponseHandler {

    public static void handleResponse(String response) throws IOException {

        response = Verbose.applyArgument(response);

        if(Output.active)
            Output.applyArgument(response);
        else
            System.out.println("\n" + response);
    }

}
