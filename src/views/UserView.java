package views;

import clientconnector.ClientServerConnector;
import com.fasterxml.jackson.databind.JsonNode;

import static utils.MessagePrinter.printConsoleMessage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.MessageTypes;
import models.*;
import models.UserUtils.*;

/**
 * @author: Shumbusho David
 * @description : The entry point for login user
 */
public class UserView {
    public void mainMethod() throws Exception {
    }

    int choice;

    public void userLoggedIn() throws Exception {
        printConsoleMessage(MessageTypes.NORMAL, false, "\t\t\t||------------------    Logged in successfully       ------------------||");
        System.out.println("\n");
        printConsoleMessage(MessageTypes.NORMAL, false, "\t\t\t||------------------    1.Logout                    ------------------||");
        printConsoleMessage(MessageTypes.NORMAL, false, "\t\t\t||------------------    2.TESTING                ------------------||");
        printConsoleMessage(MessageTypes.NORMAL, false, "\t\t\t||-----------------     3.DELETE USER            ------------------||");
        printConsoleMessage(MessageTypes.NORMAL, false, "\t\t\t\t  Enter your choice");
        Scanner Scanner = new Scanner(System.in);
        choice = Scanner.nextInt();
        switch (choice) {
            case 1:
                logoutUser();
                break;
            case 3:
                userDelete();
            default:
                printConsoleMessage(MessageTypes.ERROR, false, "Invalid input");
        }
    }
        public void logoutUser () throws IOException {
            Scanner scanner = new Scanner(System.in);
            printConsoleMessage(MessageTypes.NORMAL, false, "\tUSER LOGOUT");
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
            String message = jsonResponse.get("message").asText();
            String actionDone = jsonResponse.get("actionToDo").asText();
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
    public void sendEmail() throws Exception{
        Scanner scanner=new Scanner(System.in);
        printConsoleMessage(MessageTypes.NORMAL, false, "\tFORGOT PASSWORD");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t-----------------------");
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter your email");
        String email = scanner.nextLine();
        User user= new User();
        user.setEmail(email);
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/users");
        requestBody.setAction("sendEmail");
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
        if(status==200){
            printConsoleMessage(MessageTypes.SUCCESS, false,status+"    ||" + message +"   ||" + actionDone);
            printConsoleMessage(MessageTypes.SUCCESS, false,"========================================================================");
            verifyToken();
        }
        else{
            printConsoleMessage(MessageTypes.ERROR, false,status+"    ||" + message +"   ||" + actionDone);
            printConsoleMessage(MessageTypes.ERROR, false,"========================================================================");
            return;
        }
    }
    public void verifyToken() throws Exception{
        Scanner scanner=new Scanner(System.in);
        printConsoleMessage(MessageTypes.NORMAL, false, "\tRESET PASSWORD");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t-----------------------");
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter the token sent to the provided email");
        String token= scanner.nextLine();
        User user=new User();
        user.setToken(token);
        RequestBody requestBody=new RequestBody();
        requestBody.setUrl("/users");
        requestBody.setAction("verifyToken");
        requestBody.setObject(user);
        String requestString = new ObjectMapper().writeValueAsString(requestBody);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        String response = clientServerConnector.connectToServer(requestString);


        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(response);

        int status = jsonResponse.get("status").asInt();
        String message = jsonResponse.get("message").asText();
        String actionDone = jsonResponse.get("actionToDo").asText();

        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
        printConsoleMessage(MessageTypes.NORMAL, false,"STATUS ||         MESSAGE        ||             ACTION DON              ");
        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
        if(status==200){
            printConsoleMessage(MessageTypes.SUCCESS, false,status+"    ||" + message +"   ||" + actionDone);
            printConsoleMessage(MessageTypes.SUCCESS, false,"========================================================================");
            setNewPassword();
        }
        else{
            printConsoleMessage(MessageTypes.ERROR, false,status+"    ||" + message +"   ||" + actionDone);
            printConsoleMessage(MessageTypes.ERROR, false,"========================================================================");
        }
    }
    public void setNewPassword() throws Exception{
        Scanner scanner=new Scanner(System.in);
        printConsoleMessage(MessageTypes.NORMAL, false, "\tSET PASSWORD");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t-----------------------");
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter your new password");
        String password = scanner.nextLine();
        User user= new User();
        user.setPassword(password);
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/users");
        requestBody.setAction("setPassword");
        requestBody.setObject(user);
        String requestString = new ObjectMapper().writeValueAsString(requestBody);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        String response = clientServerConnector.connectToServer(requestString);


        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(response);

        int status = jsonResponse.get("status").asInt();
        String message = jsonResponse.get("message").asText();
        String actionDone = jsonResponse.get("actionToDo").asText();

        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
        printConsoleMessage(MessageTypes.NORMAL, false,"STATUS ||         MESSAGE        ||             ACTION DON              ");
        printConsoleMessage(MessageTypes.SUCCESS, false,"========================================================================");
        printConsoleMessage(MessageTypes.SUCCESS, false,status+"    ||" + message +"   ||" + actionDone);
        printConsoleMessage(MessageTypes.SUCCESS, false,"========================================================================");
    }

    public void userDelete() throws Exception{
        Scanner scanner=new Scanner(System.in);
        printConsoleMessage(MessageTypes.NORMAL, false, "\tDELETING A USER:");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t-----------------------");
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter the user's email you want to delete:");
        String email = scanner.nextLine();
        User userDelete = new User();
        userDelete.setEmail(email);
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/users");
        requestBody.setAction("deleteUser");
        requestBody.setObject(userDelete);

        String requestString = new ObjectMapper().writeValueAsString(requestBody);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        String response = clientServerConnector.connectToServer(requestString);

//        System.out.println("Response : " +response);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(response);

        int status = jsonResponse.get("status").asInt();
        String message = jsonResponse.get("message").asText();
        String actionDone = jsonResponse.get("actionToDo").asText();

        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
        printConsoleMessage(MessageTypes.NORMAL, false,"STATUS ||         MESSAGE        ||             ACTION DONE              ");
        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
        if(status==200){
            printConsoleMessage(MessageTypes.SUCCESS, false,status+"    ||" + message +"   ||" + actionDone);
            printConsoleMessage(MessageTypes.SUCCESS, false,"========================================================================");
            printConsoleMessage(MessageTypes.NORMAL, false, "\tDo you want to delete another user yes(1)/ no(2)");
            printConsoleMessage(MessageTypes.NORMAL, false,"\t-----------------------");
            printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter your choice:");
            Scanner Scanner = new Scanner(System.in);
            choice = Scanner.nextInt();
            switch (choice) {
                case 1:
                    userDelete();
                case 2:
                    logoutUser ();
//                    return;
                default:
                    printConsoleMessage(MessageTypes.ERROR, false, "Invalid input");
            }

        }
        else{
            printConsoleMessage(MessageTypes.ERROR, false,status+"    ||" + message +"   ||" + actionDone);
            printConsoleMessage(MessageTypes.ERROR, false,"========================================================================");
            return;
        }
    }

    public void userUpdateData() throws Exception {
        Scanner scanner = new Scanner(System.in);
        printConsoleMessage(MessageTypes.NORMAL, false, "\tUPDATE USER INFORMATION");
        printConsoleMessage(MessageTypes.NORMAL, false, "\t-----------------------------");
        printConsoleMessage(MessageTypes.NORMAL, false, "Enter the new data:\n");
        printConsoleMessage(MessageTypes.NORMAL, false, "\tEnter the user email that is to be updated:");
        String email = scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false, "\tEnter your new first name:");
        String firstName = scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false, "\tEnter your new last name:");
        String lastName = scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false, "\tEnter your gender choice [1:Male,2:Female]:");
        UserGender userGender;
        if (scanner.nextInt() == 1) {
            userGender = UserGender.MALE;
        } else {
            userGender = UserUtils.UserGender.FEMALE;
        }
        scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false, "\tEnter your new email:");
        String newEmail = scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"\tChoose account type[1:employer,2:employee]:");
        int accountType = scanner.nextInt();
        UserRoles userRole;
        if (accountType == 1){
            userRole = UserRoles.EMPLOYER;
        }else{
            userRole = UserRoles.EMPLOYEE;
        }
        printConsoleMessage(MessageTypes.NORMAL, false, "\tEnter your Date of Birth [ex:01/12/1990]");
        Date DOB = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.nextLine());
        RegisterUser userToUpdate = new RegisterUser();
        userToUpdate.setEmail(email);
        userToUpdate.setFirstName(firstName);
        userToUpdate.setLastName(lastName);
        userToUpdate.setGender(userGender);
        userToUpdate.setEmail(newEmail);
        userToUpdate.setRole(userRole);
        userToUpdate.setDob(DOB);

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/users");
        requestBody.setAction("updateUser");
        requestBody.setObject(userToUpdate);

        String requestString = new ObjectMapper().writeValueAsString(requestBody);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        String response = clientServerConnector.connectToServer(requestString);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(response);

        int status = jsonResponse.get("status").asInt();
        String message = jsonResponse.get("message").asText();
        String actionDone = jsonResponse.get("actionToDo").asText();

        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
        printConsoleMessage(MessageTypes.NORMAL, false,"STATUS ||         MESSAGE        ||             ACTION DONE              ");
        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
        if(status==200) {
            printConsoleMessage(MessageTypes.SUCCESS, false, status + "    ||" + message + "   ||" + actionDone);
            printConsoleMessage(MessageTypes.SUCCESS, false, "========================================================================");
        }else{
            printConsoleMessage(MessageTypes.ERROR, false,status+"    ||" + message +"   ||" + actionDone);
            printConsoleMessage(MessageTypes.ERROR, false,"========================================================================");
            return;
        }
    }
}