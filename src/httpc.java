
import java.io.IOException;
import java.net.URISyntaxException;

import Client.Client;
import Handlers.RequestHandler;
import Handlers.ResponseHandler;

public class httpc {
    public static void main(String[] args) throws IOException, URISyntaxException {

        RequestHandler.handleRequest(args);

        String response = Client.sendRequest(RequestHandler.requestMessage);

        ResponseHandler.handleResponse(response, args);

    }

}
