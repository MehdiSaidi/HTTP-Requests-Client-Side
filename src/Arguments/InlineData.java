package Arguments;

import Arguments.ArgumentHash;
import Handlers.RequestHandler;

public class InlineData {
    public static boolean active = false;

    public static int applyArgument(int index, String[] args) {

        char end = args[index].charAt(args[index].length() - 1);

        if (ArgumentHash.argumentHash.contains(args[index]) || end == '\'') {
            RequestHandler.entityBody += args[index];
            return index;
        }

        RequestHandler.entityBody += args[index] + " ";

        while (!ArgumentHash.argumentHash.contains(args[index]) && end != '\'') {
            index++;
            end = args[index].charAt(args[index].length() - 1);

            if (end == '\'')
                RequestHandler.entityBody += args[index];
            else
                RequestHandler.entityBody += args[index] + " ";

        }
        return index;
    }
}