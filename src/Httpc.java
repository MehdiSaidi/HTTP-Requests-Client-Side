import java.util.*;
import java.net.*;
import java.io.*;

public class Httpc {
    public static void main(String [] args){

        try {

            InetAddress web = InetAddress.getByName("www.httpbin.org");
            Socket ClientSocket = new Socket(web, 80);

            Scanner keyboard = new Scanner(System.in);

            PrintWriter out = new PrintWriter(ClientSocket.getOutputStream());
            Scanner in = new Scanner(ClientSocket.getInputStream());

            System.out.println("Please enter your command");

            String command = keyboard.nextLine();

            String [] splittedString = command.split(" ");

            System.out.println(splittedString[0]);

            if(splittedString[0].equals("httpc")){
                
            }

           /* command = "GET /status/418 HTTP/1.0\r\nUser-Agent: Hello\n\n";

            System.out.println(command);

            out.write(command);
            out.flush();

            while(in.hasNextLine()){
                System.out.println(in.nextLine());
            }

            in.close();
            out.close();
            ClientSocket.close();
            keyboard.close();
*/
        }

        catch(UnknownHostException e){}

        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
