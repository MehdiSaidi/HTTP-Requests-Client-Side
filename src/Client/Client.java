package Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import Handlers.RequestHandler;
import Helper.Helper;

public class Client {

    public static String sendRequest(String requestMsg) throws IOException {

        try {
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

            out.close();
            in.close();
            socket.close();

            return response;

        } catch (UnknownHostException e) {
            Helper.help();
        }

        return "";
    }
}