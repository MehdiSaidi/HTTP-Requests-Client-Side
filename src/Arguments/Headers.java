package Arguments;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Headers {

    public static boolean active = false;

    public static void applyArgument(ArrayList<String> headers) {

        if (!active)
            return;

        Set<String> headerHash = new HashSet<String>();

        // Since a set doesn't allow duplicates it will get rid of any duplicates
        for (String s : headers) {
            headerHash.add(s);
        }

        String headersRequestMsg = "";

        for (String s : headerHash) {
            headersRequestMsg += s + "\r\n";
        }

        // TODO: Set a headers string variable equal to headersRequestMsg

    }

}