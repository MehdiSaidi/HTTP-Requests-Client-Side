package Arguments;

public class Verbose {

    public static boolean active = false;

    public static String applyArgument(String response) {

        if (active)
            return response;

        return response.substring(response.indexOf("\r\n\r\n"));
    }
}