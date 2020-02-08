package Arguments;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Header {

    public static boolean active = false;

    public static String applyArgument(ArrayList<String> headers) {

        if (!active)
            return "";

        Set<String> headerHash = new HashSet<String>();

        // Since a set doesn't allow duplicates it will get rid of any duplicates
        for (String s : headers) {
            headerHash.add(s);
        }

        String headersRequestMsg = "";

        for (String s : headerHash) {
            headersRequestMsg += s + "\r\n";
        }

        return headersRequestMsg;
    }

}