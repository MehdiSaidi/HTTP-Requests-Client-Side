package Arguments;

import java.io.FileNotFoundException;

import Arguments.ArgumentHash;
import Handlers.RequestHandler;
import Helper.Helper;

public class InlineData {
    public static boolean active = false;

    public static int applyArgument(int index, String[] args) throws FileNotFoundException {

        if (ArgumentHash.arguments.contains(args[index])) {
            Helper.help("You are missing a value for the -d argument, please try again!");
        } else if (index == args.length - 1)
            Helper.help("You are missing a URL, please try again!");

        while (!ArgumentHash.arguments.contains(args[index + 1]) && (index + 1) != args.length - 1) {
            RequestHandler.entityBody += args[index] + " ";
            index++;
        }

        RequestHandler.entityBody += args[index];

        return index;
    }
}