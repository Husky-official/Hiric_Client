package utils;

/**
 * @author : Abijuru Seth
 * @description : Printing messages according to their types;
 */

import customs.ConsoleColorConfigurations;
import interfaces.MessageTypes;

import java.io.IOException;

public class MessagePrinter {
    public static void printConsoleMessage(MessageTypes messageType, String message) throws IOException {
        try {
            if (messageType == MessageTypes.ERROR) {
                System.out.println(ConsoleColorConfigurations.getRED()+message+ConsoleColorConfigurations.getRESET());
            } else if (messageType == MessageTypes.SUCCESS) {
                System.out.println(ConsoleColorConfigurations.getGREEN()+message+ConsoleColorConfigurations.getRESET());
            } else if (messageType == MessageTypes.WARNING) {
                System.out.println(ConsoleColorConfigurations.getYELLOW()+message+ConsoleColorConfigurations.getRESET());
            } else if (messageType == MessageTypes.ACTION) {
                System.out.println(ConsoleColorConfigurations.getBLUE()+message+ConsoleColorConfigurations.getRESET());
            } else {
                System.out.println(message);
            }
        } catch (Exception error) {
            System.out.println(ConsoleColorConfigurations.getRED()+error.getMessage()+ConsoleColorConfigurations.getRESET());
        }
    }
}
