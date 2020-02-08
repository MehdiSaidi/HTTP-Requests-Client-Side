
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URISyntaxException;

import Handlers.RequestHandler;
import Handlers.ResponseHandler;

public class httpc {
    public static void main(String[] args) throws IOException, URISyntaxException {

        // CTRL+ALT+R, Right, E, Enter, Tab, enter your command line parameters, Enter.
        // Test Variables
        // URL = "www.httpbin.org"
        // Path = "GET /status/418 HTTP/1.0\r\nUser-Agent: Hello\r\n\r\n"
        // httpc get -v 'http://httpbin.org/get?course=networking&assignment=1'

        RequestHandler.handleRequest(args);

        InetAddress web = InetAddress.getByName(RequestHandler.web);
        Socket socket = new Socket(web, 80);

        Writer out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        out.write(RequestHandler.requestMessage);
        out.flush();

        int data = in.read();
        String response = "";

        while (data != -1) {
            char c = (char) data;
            response += c;
            data = in.read();
        }

        ResponseHandler.handleResponse(response);

        out.close();
        in.close();
        socket.close();
    }

}
