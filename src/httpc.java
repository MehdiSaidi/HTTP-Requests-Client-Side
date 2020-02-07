import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class httpc {
    public static void main(String[] args) {

        try {

            InetAddress web = InetAddress.getByName("www.httpbin.org");
            Socket socket = new Socket(web, 80);

            Scanner keyboard = new Scanner(System.in);

            PrintWriter out = new PrintWriter(socket.getOutputStream());
            Scanner in = new Scanner(socket.getInputStream());

            System.out.println("Please enter your command");

            String command = keyboard.nextLine();

            String[] splittedString = command.split(" ");

            System.out.println(splittedString[0]);

            if (splittedString[0].equals("httpc")) {

            }

            command = "GET /status/418 HTTP/1.0\r\nUser-Agent: Hello\n\n";
            System.out.println(command);

            out.write(command);
            out.flush();

            while (in.hasNextLine()) {
                System.out.println(in.nextLine());
            }

            in.close();
            out.close();
            socket.close();
            keyboard.close();

        }

        catch (UnknownHostException e) {
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }
}