import java.util.Scanner;

import views.UserView;

/**
 *@author: DABAGIRE Valens
 * @description : The entry point for our system
 * */

public class Main {
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";

    public void hiricWelcomePage() {
        System.out.println(            "\n\n\n                                                                                   ");
        System.out.println(            "\t\t\t\t                                                                                 ");
        System.out.println(ANSI_BLUE + "\t\t\t\t  __           __                                                                " + ANSI_RESET);
        System.out.println(ANSI_BLUE + "\t\t\t\t |  |         |  |                                                               " + ANSI_RESET);
        System.out.println(ANSI_BLUE + "\t\t\t\t |  |         |  |                                                               " + ANSI_RESET);
        System.out.println(ANSI_BLUE + "\t\t\t\t |  |         |  |                                                               " + ANSI_RESET);
        System.out.println(ANSI_BLUE + "\t\t\t\t |  |         |  |                                                               " + ANSI_RESET);
        System.out.println(ANSI_BLUE + "\t\t\t\t |  |          __                                                                " + ANSI_RESET);
        System.out.println(ANSI_BLUE + "\t\t\t\t |  |  __________                                                                " + ANSI_RESET);
        System.out.println(ANSI_BLUE + "\t\t\t\t |  / /          |      __         _______            __           _____         " + ANSI_RESET);
        System.out.println(ANSI_BLUE + "\t\t\t\t | / /           |     |  |      /   ____  \\         |  |       /   ___  \\     " + ANSI_RESET);
        System.out.println(ANSI_BLUE + "\t\t\t\t |/ /________    |     |  |     |  /      \\__\\       |  |      |  /     \\__\\ " + ANSI_RESET);
        System.out.println(ANSI_BLUE + "\t\t\t\t  __         |   |     |  |     |  |                 |  |      |  |              " + ANSI_RESET);
        System.out.println(ANSI_BLUE + "\t\t\t\t |  |        |   |     |  |     |  |                 |  |      |  |              " + ANSI_RESET);
        System.out.println(ANSI_BLUE + "\t\t\t\t |  |        |   |     |  |     |  |                 |  |      |  |              " + ANSI_RESET);
        System.out.println(ANSI_BLUE + "\t\t\t\t |  |        |   |     |  |     |  |                 |  |      |  |              " + ANSI_RESET);
        System.out.println(ANSI_BLUE + "\t\t\t\t |  |        |   |     |  |     |  |                 |  |      |  |      __      " + ANSI_RESET);
        System.out.println(ANSI_BLUE + "\t\t\t\t |  |        |   |     |  |     |  |                 |  |      |   \\___/   /    " + ANSI_RESET);
        System.out.println(ANSI_BLUE + "\t\t\t\t |  |        |   |     |  |     |  |                 |  |       \\         /     " + ANSI_RESET);
        System.out.println(ANSI_BLUE + "\t\t\t\t  --          ---       --       --                   --           -----         " + ANSI_RESET);
        System.out.println(ANSI_BLUE + "\t\t\t\t                                                                                 " + ANSI_RESET);
        System.out.println(ANSI_BLUE + "\t\t\t\t ==========================================================================    " + ANSI_RESET);
    }

    public void loadingPageWrapper() throws InterruptedException {
        System.out.println("\t\t\t\t__________________________________________________________________________");
        System.out.print(ANSI_BLUE +"\t\t\t\t\t\tHiric \t"+ANSI_RESET);
        for (int i = 0; i < 20; i++) {
            System.out.print(".");
            Thread.sleep(100);
        }
        System.out.print("\n");
        System.out.println("\t\t\t\t__________________________________________________________________________\n\n");
        System.out.println("\n");
    }

    public static void welcomeToHiric() throws Exception {

        UserView userView = new UserView();
        String toContinue;

        do {
            new Main().hiricWelcomePage();
            new Main().loadingPageWrapper();
            System.out.println("\t\t\t||-------------------------------------------------------------------||");
            System.out.println("\t\t\t||------------------      WELCOME TO HIRIC        -------------------||");
            System.out.println("\t\t\t||-------------------------------------------------------------------||");
            System.out.println("\t\t\t||------------------    1.LOGIN                    ------------------||");
            System.out.println("\t\t\t||------------------    2.REGISTER                 ------------------||");

            Scanner scanner = new Scanner(System.in);
            int choice;

            System.out.println("\t\t\t||-------------------------------------------------------------------||");
            System.out.println("\t\t\t\t  Enter your choice");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    userView.loginUser();
                    break;
                default:
                    System.out.println("Invalid input");
            }

            System.out.print("\t\tDo you want to continue searching? (y/n): ");
            toContinue = scanner.next();
        } while (toContinue.equalsIgnoreCase("y") || toContinue.equalsIgnoreCase("yes"));
    }

    public static void main(String[] args) throws Exception {
        welcomeToHiric();
    }
}
