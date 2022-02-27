package hiricclient.views;

import com.fasterxml.jackson.databind.JsonNode;
import hiricclient.ClientServerConnector;
import hiricclient.models.RequestBody;
import hiricclient.models.User;

import java.util.Scanner;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserView {
    public void mainMethod() throws Exception {

    }

    public void loginUser() throws Exception {

        Scanner scanner = new Scanner(System.in);
        System.out.println("\tUSER LOGIN");
        System.out.println("\t-----------------------");
        System.out.println("\tEnter your name");
        String name = scanner.nextLine();
        System.out.println("\tEnter your Id");
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

        System.out.println("========================================================================");
        System.out.println("STATUS ||         MESSAGE        ||             ACTION DON              ");
        System.out.println("========================================================================");
        System.out.println(status+"    ||" + message +"   ||" + actionDone);
        System.out.println("========================================================================");

    }
}
