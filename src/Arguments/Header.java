package Arguments;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import Helper.Helper;

public class Header {

    public static boolean active = false;

    public static String applyArgument(ArrayList<String> headers) throws FileNotFoundException {

        if (!active)
            return "";

        Set<String> headerHash = new LinkedHashSet<String>();

        // Create a hash of the header names to see if there are any duplicates
        for (String s : headers) {
            String headerName = s.substring(0, s.indexOf(":"));
            if (!headerHash.add(headerName))
                Helper.help("You have duplicate headers!");
        }

        // If there are no duplicates
        headerHash.clear();
        for (String s : headers) {
            headerHash.add(s);
        }

        // Construct the headers String for the request message
        String headersRequestMsg = "";

        for (String s : headerHash) {
            headersRequestMsg += s + "\r\n";
        }

        return headersRequestMsg;
    }

}