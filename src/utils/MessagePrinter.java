package utils;

/**
 * @author : Abijuru Seth
 * @description : Printing messages according to their types;
 */

import customs.ConsoleColorConfigurations;
import interfaces.MessageTypes;

import java.io.IOException;

public class MessagePrinter {
    public static void printConsoleMessage(MessageTypes messageType, Boolean sameLine, String message) throws IOException {
        try {
            if (messageType == MessageTypes.ERROR) {
                if (sameLine) {
                    System.out.print(ConsoleColorConfigurations.getRED()+message+ConsoleColorConfigurations.getRESET());
                } else {
                    System.out.println(ConsoleColorConfigurations.getRED()+message+ConsoleColorConfigurations.getRESET());
                }
            } else if (messageType == MessageTypes.SUCCESS) {
                if (sameLine) {
                    System.out.print(ConsoleColorConfigurations.getGREEN()+message+ConsoleColorConfigurations.getRESET());
                } else {
                    System.out.println(ConsoleColorConfigurations.getGREEN()+message+ConsoleColorConfigurations.getRESET());
                }
            } else if (messageType == MessageTypes.WARNING) {
                if (sameLine) {
                    System.out.print(ConsoleColorConfigurations.getYELLOW()+message+ConsoleColorConfigurations.getRESET());
                } else {
                    System.out.println(ConsoleColorConfigurations.getYELLOW()+message+ConsoleColorConfigurations.getRESET());
                }
            } else if (messageType == MessageTypes.ACTION) {
                if (sameLine) {
                    System.out.print(ConsoleColorConfigurations.getBLUE()+message+ConsoleColorConfigurations.getRESET());
                } else {
                    System.out.println(ConsoleColorConfigurations.getBLUE()+message+ConsoleColorConfigurations.getRESET());
                }
            } else {
                if (sameLine) {
                    System.out.print(message);
                } else {
                    System.out.println(message);
                }
            }
        } catch (Exception error) {
            System.out.println(ConsoleColorConfigurations.getRED()+error.getMessage()+ConsoleColorConfigurations.getRESET());
        }
    }
}
