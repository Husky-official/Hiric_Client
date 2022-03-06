package utils;

/**
 * @author : Abijuru Seth
 * @description : Action loading util;
 * - consisting a constructor loader(int loadLength, string loadingStatement) - takes format
 *  - loadLength: number of dots to display for the loading
 *  - loadingStatement: loading work ex: 'loading'
 */

import interfaces.MessageTypes;

import java.io.IOException;

import static utils.MessagePrinter.printConsoleMessage;

public class Loader {
    public Loader(int loadLength, String loadingStatement) throws IOException, InterruptedException {
        printConsoleMessage(MessageTypes.ACTION, true, loadingStatement);
        for (int i = 0; i < loadLength; i++) {
            printConsoleMessage(MessageTypes.ACTION,true, ".");
            Thread.sleep(100);
        }
    }
}
