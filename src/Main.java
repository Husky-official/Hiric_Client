import interfaces.MessageTypes;
import views.BillingView;
import views.InvoiceView;
import views.HiringView;
import views.MessageView;
import views.RegisterView;
import views.UserView;
import views.hiring.JobPostingView;
import views.interviewing.EventSchedulingView;

import java.io.IOException;
import java.util.Scanner;

import static utils.MessagePrinter.printConsoleMessage;

/**
 * @author: DABAGIRE Valens
 * @author: ABIJURU Seth
 * @description : The entry point for our system
 */

public class Main {

    public void hiricWelcomePage() throws IOException {
        try {
            printConsoleMessage(MessageTypes.NORMAL, false,"\n\n\n                                                                                   ");
            printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t\t                                                                                 ");
            printConsoleMessage(MessageTypes.ACTION, false,"\t\t\t\t  __           __                                                                ");
            printConsoleMessage(MessageTypes.ACTION, false,"\t\t\t\t |  |         |  |                                                               ");
            printConsoleMessage(MessageTypes.ACTION, false,"\t\t\t\t |  |         |  |                                                               ");
            printConsoleMessage(MessageTypes.ACTION, false,"\t\t\t\t |  |         |  |                                                               ");
            printConsoleMessage(MessageTypes.ACTION, false,"\t\t\t\t |  |         |  |                                                               ");
            printConsoleMessage(MessageTypes.ACTION, false,"\t\t\t\t |  |          __                                                                ");
            printConsoleMessage(MessageTypes.ACTION, false,"\t\t\t\t |  |  __________                                                                ");
            printConsoleMessage(MessageTypes.ACTION, false,"\t\t\t\t |  / /          |      __         _______            __           _____         ");
            printConsoleMessage(MessageTypes.ACTION, false,"\t\t\t\t | / /           |     |  |      /   ____  \\         |  |       /   ___  \\     ");
            printConsoleMessage(MessageTypes.ACTION, false,"\t\t\t\t |/ /________    |     |  |     |  /      \\__\\       |  |      |  /     \\__\\ ");
            printConsoleMessage(MessageTypes.ACTION, false,"\t\t\t\t  __         |   |     |  |     |  |                 |  |      |  |              ");
            printConsoleMessage(MessageTypes.ACTION, false,"\t\t\t\t |  |        |   |     |  |     |  |                 |  |      |  |              ");
            printConsoleMessage(MessageTypes.ACTION, false,"\t\t\t\t |  |        |   |     |  |     |  |                 |  |      |  |              ");
            printConsoleMessage(MessageTypes.ACTION, false,"\t\t\t\t |  |        |   |     |  |     |  |                 |  |      |  |              ");
            printConsoleMessage(MessageTypes.ACTION, false,"\t\t\t\t |  |        |   |     |  |     |  |                 |  |      |  |      __      ");
            printConsoleMessage(MessageTypes.ACTION, false,"\t\t\t\t |  |        |   |     |  |     |  |                 |  |      |   \\___/   /    ");
            printConsoleMessage(MessageTypes.ACTION, false,"\t\t\t\t |  |        |   |     |  |     |  |                 |  |       \\         /     ");
            printConsoleMessage(MessageTypes.ACTION, false,"\t\t\t\t  --          ---       --       --                   --           -----         ");
            printConsoleMessage(MessageTypes.ACTION, false,"\t\t\t\t                                                                                 ");
            printConsoleMessage(MessageTypes.ACTION, false,"\t\t\t\t ==========================================================================      ");
        } catch (IOException error) {
            printConsoleMessage(MessageTypes.ERROR, false, error.getMessage());
        }
    }

    public void loadingPageWrapper() throws InterruptedException, IOException {
        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t\t__________________________________________________________________________");
        printConsoleMessage(MessageTypes.ACTION, true,"\t\t\t\t\t\tHiric \t");
        for (int i = 0; i < 20; i++) {
            printConsoleMessage(MessageTypes.NORMAL,true, ".");
            Thread.sleep(100);
        }
        printConsoleMessage(MessageTypes.NORMAL, false,"\n");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t\t__________________________________________________________________________\n\n");
        printConsoleMessage(MessageTypes.NORMAL, false,"\n");
    }

    public static void WelcomeToHiric() throws IOException {
        try {
            EventSchedulingView eventSchedulingView = new EventSchedulingView();
            UserView userView = new UserView();
            BillingView billingView = new BillingView();
            MessageView messageView = new MessageView();
            InvoiceView invoiceView = new InvoiceView();
            String toContinue;

            do {
                new Main().hiricWelcomePage();
                new Main().loadingPageWrapper();
                printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||-------------------------------------------------------------------||");
                printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||------------------      WELCOME TO HIRIC        -------------------||");
                printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||-------------------------------------------------------------------||");
                printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||------------------    1.LOGIN                    ------------------||");
                printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||------------------    2.REGISTER                 ------------------||");
                printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||------------------    4.CREATE JOB POST          ------------------||");
                printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||------------------    4.GET JOB POSTS            ------------------||");
                printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||------------------    5.UPDATE JOB POST          ------------------||");
                printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||------------------    6.PAY YOUR EMPLOYEE        ------------------||");
                printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||------------------    7.HIRING                   ------------------||");
                printConsoleMessage(MessageTypes.NORMAL, false, "\t\t\t||-----------------    9.MESSAGING                ------------------||");
                printConsoleMessage(MessageTypes.NORMAL, false, "\t\t\t||-----------------    10.Interviewing            ------------------||");

                Scanner scanner = new Scanner(System.in);
                int choice;

                printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||-------------------------------------------------------------------||");
                printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t\t  Enter your choice");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> userView.loginUser();
                    case 2-> RegisterView.registerUser();
                    case 6 -> billingView.makePayment();
                    case 7 -> HiringView.mainMethod();
                    case 9 -> MessageView.mainMethod();
                    case 10 -> eventSchedulingView.mainMethod();
                    default -> printConsoleMessage(MessageTypes.ERROR, false, "Invalid input");
                }

                printConsoleMessage(MessageTypes.NORMAL, false,"\t\tDo you want to continue searching? (y/n): ");
                toContinue = scanner.next();
            } while (toContinue.equalsIgnoreCase("y") || toContinue.equalsIgnoreCase("yes"));
        } catch (Exception error) {
            printConsoleMessage(MessageTypes.ERROR, false, error.getMessage());
        }
    }

    public static void main(String [] args) throws IOException {
        WelcomeToHiric();
    }
}