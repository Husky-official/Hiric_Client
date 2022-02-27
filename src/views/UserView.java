package views;

import com.fasterxml.jackson.databind.JsonNode;
import clientconnector.ClientServerConnector;
import static utils.MessagePrinter.printConsoleMessage;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.MessageTypes;
import models.RequestBody;
import models.User;

public class UserView {
    public void mainMethod() throws Exception {

    }

    public void loginUser() throws Exception {

        Scanner scanner = new Scanner(System.in);
        printConsoleMessage(MessageTypes.NORMAL, false, "\tUSER LOGIN");;
        printConsoleMessage(MessageTypes.NORMAL, false,"\t-----------------------");
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter your name");
        String name = scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter your Id");
        int id = scanner.nextInt();
        User user = new User();
        user.setName(name);
        user.setId(id);

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/users");
        requestBody.setAction("login");
        requestBody.setObject(user);

        String requestString = new ObjectMapper().writeValueAsString(requestBody);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        String response = clientServerConnector.connectToServer(requestString);

        //System.out.println("Response : " +response);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(response);

        int status = jsonResponse.get("status").asInt();
        String message = jsonResponse.get("message").asText();
        String actionDone = jsonResponse.get("actionToDo").asText();

        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
        printConsoleMessage(MessageTypes.NORMAL, false,"STATUS ||         MESSAGE        ||             ACTION DON              ");
        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
        printConsoleMessage(MessageTypes.NORMAL, false,status+"    ||" + message +"   ||" + actionDone);
        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");

    }
    public void pay() throws Exception {
        Scanner scan = new Scanner(System.in);
        int money_to_pay = 1000;
        printConsoleMessage(MessageTypes.NORMAL, false, "||  You must pay "+money_to_pay+" rwf ||");
        printConsoleMessage(MessageTypes.NORMAL,false,"choose your payment method");
        printConsoleMessage(MessageTypes.NORMAL,false,"1.Pay using MoMo ");
        printConsoleMessage(MessageTypes.NORMAL,false,"2.Pay Using paypal");
        int choice = scan.nextInt();
        if (choice == 1){

            printConsoleMessage(MessageTypes.NORMAL,true,"Enter your number: ");
            String phone_number = scan.nextLine();
            printConsoleMessage(MessageTypes.NORMAL,true,"Enter your PIN: ");
            String PIN = scan.nextLine();
            //due to it being a console version we can't pull API since they require a web interface
            RequestBody requestBody = new RequestBody();
            requestBody.setUrl("/payment");

        }else if(choice == 2){
            printConsoleMessage(MessageTypes.NORMAL,true,"Enter your email: ");
            String paypal_email = scan.nextLine();
            printConsoleMessage(MessageTypes.NORMAL,true,"Enter your password: ");
            String paypal_password = scan.nextLine();
            //due to it being a console version we can't pull API since they require a web interface
            RequestBody requestBody = new RequestBody();
            requestBody.setUrl("/payment");
        }else {
            printConsoleMessage(MessageTypes.NORMAL, false, "Invalid input !");
            TimeUnit.SECONDS.sleep(3);
            pay();
        }

    }
}