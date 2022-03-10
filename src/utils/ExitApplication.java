package utils;

/**
 * @author : Abijuru Seth
 * @description : Existing the whole application util;
 * - this takes the current thread and stops it, then stop the entire application
 */

import interfaces.MessageTypes;

import java.io.IOException;

public class ExitApplication {
    public ExitApplication() throws IOException, InterruptedException {
        MessagePrinter.skipLines(1);
        new Loader(20, "Leaving ");
        MessagePrinter.skipLines(1);
        MessagePrinter.printConsoleMessage(MessageTypes.SUCCESS, false, "BYE FOR NOW!");
        MessagePrinter.skipLines(1);
        Thread.currentThread().stop();
    }
}
