package views;

import com.fasterxml.jackson.databind.JsonNode;
import clientmain.clientconnector.ClientServerConnector;
import static utils.MessagePrinter.printConsoleMessage;

import java.util.Scanner;

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
}
