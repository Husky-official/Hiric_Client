package views;

import clientconnector.ClientServerConnector;
import com.fasterxml.jackson.databind.JsonNode;
import utils.ExitApplication;
import utils.Loader;
import utils.MessagePrinter;
import static utils.MessagePrinter.printConsoleMessage;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.MessageTypes;
import models.RequestBody;
import models.User;
import models.UserLogout;

/**
 * @author: Shumbusho David
 * @description : The entry point for login user
 */
public class UserView {
    public void mainMethod() throws Exception {
    }

    int choice;

    public void userLoggedIn() throws IOException, InterruptedException {
        new Loader(15, "Loading.............");
        MessagePrinter.skipLines(1);
        printConsoleMessage(MessageTypes.NORMAL, false, "\t\t\t||------------------    Logged in successfully       ------------------||");
        System.out.println("\n");
        printConsoleMessage(MessageTypes.NORMAL, false, "\t\t\t||------------------    1.Logout                    ------------------||");
        printConsoleMessage(MessageTypes.NORMAL, false, "\t\t\t||------------------    2.Go back               ------------------||");
        printConsoleMessage(MessageTypes.NORMAL, false, "\t\t\t||------------------    0.Exit           ------------------||");
        printConsoleMessage(MessageTypes.NORMAL, false, "\t\t\t\t  Enter your choice");
        Scanner Scanner = new Scanner(System.in);
        choice = Scanner.nextInt();
        switch (choice) {
            case 1:
                logoutUser();
                break;
            case 2:
                    new Loader(15, "Going back ");
                    MessagePrinter.skipLines(1);
                    break;
            case 0 :
                new ExitApplication();
                break;
            default:
                printConsoleMessage(MessageTypes.ERROR, false, "Invalid input");
        }
    }
        public void logoutUser () throws IOException, InterruptedException {
            Scanner scanner = new Scanner(System.in);
            printConsoleMessage(MessageTypes.NORMAL, false, "\tUSER LOGOUT");
            ;
            printConsoleMessage(MessageTypes.NORMAL, false, "\t-----------------------");
            printConsoleMessage(MessageTypes.NORMAL, false, "\tEnter your email");
            String email = scanner.nextLine();
            UserLogout user = new UserLogout();
            user.setEmail(email);
            RequestBody requestBody = new RequestBody();
            requestBody.setUrl("/users");
            requestBody.setAction("logout");
            requestBody.setObject(user);

            String requestString = new ObjectMapper().writeValueAsString(requestBody);
            //sending object to server
            ClientServerConnector clientServerConnector = new ClientServerConnector();
            String response = clientServerConnector.connectToServer(requestString);

            //System.out.println("Response : " +response);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonResponse = objectMapper.readTree(response);
            int status = jsonResponse.get("status").asInt();
            System.out.println(status);
            String message = jsonResponse.get("message").asText();
            String actionDone = jsonResponse.get("actionToDo").asText();
            new Loader(15, "Leaving.............");
            MessagePrinter.skipLines(1);
            if(Objects.equals(status, 500) || Objects.equals(status,404) || Objects.equals(status,400)){
                printConsoleMessage(MessageTypes.ERROR, false,"========================================================================");
                printConsoleMessage(MessageTypes.ERROR, false,"STATUS ||         MESSAGE        ||             ACTION DON              ");
                printConsoleMessage(MessageTypes.ERROR, false,"========================================================================");
                printConsoleMessage(MessageTypes.ERROR, false,status+"    ||" + message +"   ||" + actionDone);
                printConsoleMessage(MessageTypes.ERROR, false,"========================================================================");
                return;
            }
            printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
            printConsoleMessage(MessageTypes.NORMAL, false,"STATUS ||         MESSAGE        ||             ACTION DON              ");
            printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
            printConsoleMessage(MessageTypes.NORMAL, false,status+"    ||" + message +"   ||" + actionDone);
            printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
        }
    public void loginUser() throws Exception {

        Scanner scanner = new Scanner(System.in);
        printConsoleMessage(MessageTypes.NORMAL, false, "\tUSER LOGIN");

        printConsoleMessage(MessageTypes.NORMAL, false, "\t-----------------------");
        printConsoleMessage(MessageTypes.NORMAL, false, "\tEnter your email");
        String email = scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter your password");
        String password = scanner.nextLine();
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/users");
        requestBody.setAction("login");
        requestBody.setObject(user);

        String requestString = new ObjectMapper().writeValueAsString(requestBody);
        //sending object to server
        ClientServerConnector clientServerConnector = new ClientServerConnector();
        String response = clientServerConnector.connectToServer(requestString);

        //System.out.println("Response : " +response);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(response);

        int status = jsonResponse.get("status").asInt();
        String message = jsonResponse.get("message").asText();
        String actionDone = jsonResponse.get("actionToDo").asText();
        if(Objects.equals(message, "You are already logged in.") || Objects.equals(message,"User logged in successfully")){
        userLoggedIn();
        return;
        }
        new Loader(15, "Loading.............");
        MessagePrinter.skipLines(1);
        if(Objects.equals(status, 500) || Objects.equals(status,404) || Objects.equals(status,400)){
            printConsoleMessage(MessageTypes.ERROR, false,"========================================================================");
            printConsoleMessage(MessageTypes.ERROR, false,"STATUS ||         MESSAGE        ||             ACTION DON              ");
            printConsoleMessage(MessageTypes.ERROR, false,"========================================================================");
            printConsoleMessage(MessageTypes.ERROR, false,status+"    ||" + message +"   ||" + actionDone);
            printConsoleMessage(MessageTypes.ERROR, false,"========================================================================");
            return;
        }
        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
        printConsoleMessage(MessageTypes.NORMAL, false,"STATUS ||         MESSAGE        ||             ACTION DON              ");
        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
        printConsoleMessage(MessageTypes.NORMAL, false,status+"    ||" + message +"   ||" + actionDone);
        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");

    }
}