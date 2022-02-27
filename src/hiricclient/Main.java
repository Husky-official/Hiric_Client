package hiricclient;

import hiricclient.views.UserView;

import java.util.Scanner;

public class Main {

    public static void  main(String[] args) throws Exception {
        welcomeToHiric();
    }

    public static void welcomeToHiric() throws Exception {

        UserView userView = new UserView();
        String toContinue;

        do{
            System.out.println("\t\t\t||-------------------------------------------------------------------||");
            System.out.println("\t\t\t||------------------      WELCOME TO HIRIC        -------------------||");
            System.out.println("\t\t\t||-------------------------------------------------------------------||");
            System.out.println("\t\t\t||------------------    1.LOGIN                    ------------------||");
            System.out.println("\t\t\t||------------------    2.REGISTER                 ------------------||");

            Scanner scanner = new Scanner(System.in);
            int choice;

            System.out.println("\t\t\t||-------------------------------------------------------------------||");
            System.out.println("\t\t\t\t  Enter your choice" );
            choice = scanner.nextInt();

            switch (choice){
                case 1:
                    userView.loginUser();
                    break;
                default:
                    System.out.println("Invalid input");
            }

            System.out.print("\t\tDo you want to continue searching? (y/n): ");
            toContinue = scanner.next();
        }while (toContinue.equalsIgnoreCase("y") || toContinue.equalsIgnoreCase("yes"));
    }
}
