package views;

import clientconnector.ClientServerConnector;
import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.MessageTypes;
import models.RequestBody;
import static utils.MessagePrinter.printConsoleMessage;

import java.io.IOException;
import java.util.Scanner;

public class Dashboard {
    public static void main(String[] args) throws Exception{

        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||-------------------------------------------------------------------||");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||------------------      HIRIC DASHBOARD         -------------------||");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||-------------------------------------------------------------------||");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||------------------    1.ADMIN                    ------------------||");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||------------------    2.EMPLOYER                 ------------------||");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||------------------    3.EMPLOYEE                 ------------------||");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||-------------------------------------------------------------------||");

        Scanner scanner = new Scanner(System.in);
        int choice;
        choice = scanner.nextInt();

        switch (choice) {
            case 1 -> adminDashboard(true);
            case 3 -> employeeDashboard(true);
        }
    }

    public static void adminDashboard(Boolean isAdmin) throws IOException {
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/adminDashboard");
        requestBody.setAction("adminDashboard");


        ObjectMapper objectMapper = new ObjectMapper();
        String requestString = objectMapper.writeValueAsString(requestBody);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        String response = clientServerConnector.connectToServer(requestString);

        printConsoleMessage(MessageTypes.SUCCESS, false, response);
    }

    public static void employeeDashboard(Boolean isEmployee) throws IOException {
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/employeeDashboard");
        requestBody.setAction("employeeDashboard");

        //Dummy stuffs

        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||-------------------------------------------------------------------||");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||------------------      EMPLOYEE DASHBOARD      -------------------||");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||-------------------------------------------------------------------||");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||------------------    Name: Igor Rwagapfizi      ------------------||");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||------------------    Gender: Male               ------------------||");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||------------------    DOB: 21/Nov/2004           ------------------||");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||------------------    Tel: 0788000000            ------------------||");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||------------------    Email: johndoe@gmail.com   ------------------||");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||-------------------------------------------------------------------||");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||------------------    Jobs done: 5               ------------------||");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||------------------    Preferable Amount: 5000    ------------------||");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||------------------    Date joined: 25/Mar/2018   ------------------||");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||-------------------------------------------------------------------||");

        printConsoleMessage(MessageTypes.NORMAL, false,"\n\t\t\t||-------------------------------------------------------------------||");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||------------------    Date joined: 25/Mar/2018   ------------------||");

        ObjectMapper objectMapper = new ObjectMapper();
        String requestString = objectMapper.writeValueAsString(requestBody);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        String response = clientServerConnector.connectToServer(requestString);

        printConsoleMessage(MessageTypes.SUCCESS, false, response);
    }
}