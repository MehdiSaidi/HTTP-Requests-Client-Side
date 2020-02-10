package Arguments;

import Arguments.ArgumentHash;
import Handlers.RequestHandler;

public class InlineData {
    public static boolean active = false;

    public static int applyArgument(int index, String[] args) {

        while (!ArgumentHash.arguments.contains(args[index]) && (index + 1) != args.length - 1) {
            RequestHandler.entityBody += args[index] + " ";
            index++;
        }

        RequestHandler.entityBody += args[index];

        return index - 1;
    }
}