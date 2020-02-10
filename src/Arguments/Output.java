package Arguments;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Output {
    public static boolean active = false;
    public static String outputFile;

    public static void applyArgument(String response) throws IOException {

        if (!active)
            return;

        BufferedWriter out = new BufferedWriter(new FileWriter(outputFile));
        out.write(response);
        out.flush();
        out.close();
        System.out.println(
                "\n" + "Response was written to the specified file **if the file did not exist, it has been created**");
    }
}
