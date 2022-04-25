package views;

import interfaces.MessageTypes;
import views.hiring.JobApplicationView;
import views.hiring.JobPostingView;
//import views.hiring.ShortListingView;

import java.util.Scanner;
import static utils.MessagePrinter.printConsoleMessage;

public class HiringView {
    public static void mainMethod() throws Exception {
        printConsoleMessage(MessageTypes.NORMAL, false, "\tHIRING");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t-----------------------");
        printConsoleMessage(MessageTypes.NORMAL, false, "\t1.JOB POSTING");
        printConsoleMessage(MessageTypes.NORMAL, false, "\t2.JOB APPLICATION");
        printConsoleMessage(MessageTypes.NORMAL, false, "\t3.SHORT LISTING");
        Scanner scanner = new Scanner(System.in);
        int choice;
        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||-------------------------------------------------------------------||");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t\t  Enter your choice");
        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> JobPostingView.mainMethod();
            case 2 -> JobApplicationView.main();
          //  case 3 -> ShortListingView.mainMethod();
        }
    }


}
