package views;

import clientconnector.ClientServerConnector;
import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.MessageTypes;
import models.RequestBody;
import static utils.MessagePrinter.printConsoleMessage;

import java.io.IOException;
import java.util.Scanner;

public class Dashboard {
    public static void main() throws Exception{

        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||-------------------------------------------------------------------||");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||------------------      HIRIC DASHBOARD         -------------------||");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||-------------------------------------------------------------------||");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||------------------    1.ADMIN                    ------------------||");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||------------------    2.EMPLOYER                 ------------------||");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||------------------    3.EMPLOYEE                 ------------------||");

        Scanner scanner = new Scanner(System.in);
        int choice;
        choice = scanner.nextInt();

        switch (choice) {
            case 1 -> adminDashboard(true);
            case 2 -> employeeDashboard(true);
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


        ObjectMapper objectMapper = new ObjectMapper();
        String requestString = objectMapper.writeValueAsString(requestBody);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        String response = clientServerConnector.connectToServer(requestString);

        printConsoleMessage(MessageTypes.SUCCESS, false, response);
    }
}