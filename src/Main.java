import interfaces.MessageTypes;
import views.UserView;
import static utils.MessagePrinter.printConsoleMessage;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author: DABAGIRE Valens
 * @author: ABIJURU Seth
 * @description : The entry point for our system
 */

public class Main {

    public void hiricWelcomePage() throws IOException {
        try {
            printConsoleMessage(MessageTypes.NORMAL,"\n\n\n                                                                                   ");
            printConsoleMessage(MessageTypes.NORMAL,"\t\t\t\t                                                                                 ");
            printConsoleMessage(MessageTypes.ACTION,"\t\t\t\t  __           __                                                                ");
            printConsoleMessage(MessageTypes.ACTION,"\t\t\t\t |  |         |  |                                                               ");
            printConsoleMessage(MessageTypes.ACTION,"\t\t\t\t |  |         |  |                                                               ");
            printConsoleMessage(MessageTypes.ACTION,"\t\t\t\t |  |         |  |                                                               ");
            printConsoleMessage(MessageTypes.ACTION,"\t\t\t\t |  |         |  |                                                               ");
            printConsoleMessage(MessageTypes.ACTION,"\t\t\t\t |  |          __                                                                ");
            printConsoleMessage(MessageTypes.ACTION,"\t\t\t\t |  |  __________                                                                ");
            printConsoleMessage(MessageTypes.ACTION,"\t\t\t\t |  / /          |      __         _______            __           _____         ");
            printConsoleMessage(MessageTypes.ACTION,"\t\t\t\t | / /           |     |  |      /   ____  \\         |  |       /   ___  \\     ");
            printConsoleMessage(MessageTypes.ACTION,"\t\t\t\t |/ /________    |     |  |     |  /      \\__\\       |  |      |  /     \\__\\ ");
            printConsoleMessage(MessageTypes.ACTION,"\t\t\t\t  __         |   |     |  |     |  |                 |  |      |  |              ");
            printConsoleMessage(MessageTypes.ACTION,"\t\t\t\t |  |        |   |     |  |     |  |                 |  |      |  |              ");
            printConsoleMessage(MessageTypes.ACTION,"\t\t\t\t |  |        |   |     |  |     |  |                 |  |      |  |              ");
            printConsoleMessage(MessageTypes.ACTION,"\t\t\t\t |  |        |   |     |  |     |  |                 |  |      |  |              ");
            printConsoleMessage(MessageTypes.ACTION,"\t\t\t\t |  |        |   |     |  |     |  |                 |  |      |  |      __      ");
            printConsoleMessage(MessageTypes.ACTION,"\t\t\t\t |  |        |   |     |  |     |  |                 |  |      |   \\___/   /    ");
            printConsoleMessage(MessageTypes.ACTION,"\t\t\t\t |  |        |   |     |  |     |  |                 |  |       \\         /     ");
            printConsoleMessage(MessageTypes.ACTION,"\t\t\t\t  --          ---       --       --                   --           -----         ");
            printConsoleMessage(MessageTypes.ACTION,"\t\t\t\t                                                                                 ");
            printConsoleMessage(MessageTypes.ACTION,"\t\t\t\t ==========================================================================      ");
        } catch (IOException error) {
            printConsoleMessage(MessageTypes.ERROR, error.getMessage());
        }
    }

    public void loadingPageWrapper() throws InterruptedException, IOException {
        printConsoleMessage(MessageTypes.NORMAL,"\t\t\t\t__________________________________________________________________________");
        printConsoleMessage(MessageTypes.ACTION,"\t\t\t\t\t\tHiric \t");
        for (int i = 0; i < 20; i++) {
            System.out.print(".");
            Thread.sleep(100);
        }
        printConsoleMessage(MessageTypes.NORMAL,"\n");
        printConsoleMessage(MessageTypes.NORMAL,"\t\t\t\t__________________________________________________________________________\n\n");
        printConsoleMessage(MessageTypes.NORMAL,"\n");
    }

    public static void WelcomeToHiric() throws IOException {
        try {

            UserView userView = new UserView();
            String toContinue;

            do {
                new Main().hiricWelcomePage();
                new Main().loadingPageWrapper();
                printConsoleMessage(MessageTypes.NORMAL,"\t\t\t||-------------------------------------------------------------------||");
                printConsoleMessage(MessageTypes.NORMAL,"\t\t\t||------------------      WELCOME TO HIRIC        -------------------||");
                printConsoleMessage(MessageTypes.NORMAL,"\t\t\t||-------------------------------------------------------------------||");
                printConsoleMessage(MessageTypes.NORMAL,"\t\t\t||------------------    1.LOGIN                    ------------------||");
                printConsoleMessage(MessageTypes.NORMAL,"\t\t\t||------------------    2.REGISTER                 ------------------||");

                Scanner scanner = new Scanner(System.in);
                int choice;

                printConsoleMessage(MessageTypes.NORMAL,"\t\t\t||-------------------------------------------------------------------||");
                printConsoleMessage(MessageTypes.NORMAL,"\t\t\t\t  Enter your choice");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        userView.loginUser();
                        break;
                    default:
                        printConsoleMessage(MessageTypes.ERROR,"Invalid input");
                }

                printConsoleMessage(MessageTypes.NORMAL,"\t\tDo you want to continue searching? (y/n): ");
                toContinue = scanner.next();
            } while (toContinue.equalsIgnoreCase("y") || toContinue.equalsIgnoreCase("yes"));
        } catch (Exception error) {
            printConsoleMessage(MessageTypes.ERROR, error.getMessage());
        }
    }

    public static void main(String [] args) throws IOException {
        WelcomeToHiric();
    }
}
