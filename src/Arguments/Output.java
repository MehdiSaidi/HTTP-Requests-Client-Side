package Arguments;


import java.io.*;

public class Output {
    public static boolean active = false;
    public static String outputFile;

    public static void applyArgument(String response) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(outputFile));
        out.write(response);
        out.flush();
        out.close();
        System.out.println("Response was written to the specified file **if the file did not exist, it has been created**");
    }
}
