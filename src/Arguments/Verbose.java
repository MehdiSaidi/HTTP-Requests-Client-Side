package Arguments;

public class Verbose {

    public static boolean active = false;

    public static void applyArgument(String response) {

        if (active)
            return;

        System.out.println(response.substring(response.indexOf("\r\n\r\n")));
    }
}