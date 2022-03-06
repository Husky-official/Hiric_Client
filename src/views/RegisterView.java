/**
 * @author:
 **/

package views;

import clientconnector.ClientServerConnector;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.MessageTypes;
import models.RegisterUser;
import models.RequestBody;
import models.UserUtils.UserGender;
import models.UserUtils.UserRoles;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static utils.MessagePrinter.ResponsePrinter;
import static utils.MessagePrinter.printConsoleMessage;

/**
 * The type RegisterUser view.
 */
public class RegisterView {

    public void mainMethod() throws Exception {
    }

    /**
     * Login user.
     *
     * @throws Exception the exception
     */
    public void loginUser() throws Exception {

        Scanner scanner = new Scanner(System.in);
        printConsoleMessage(MessageTypes.NORMAL, false, "\tUSER LOGIN");
        printConsoleMessage(MessageTypes.NORMAL, false, "\t-----------------------");
        printConsoleMessage(MessageTypes.NORMAL, false, "\tEnter your email");
        String email = scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false, "\tEnter your password");
        String password = scanner.nextLine();
        RegisterUser user = new RegisterUser();
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

        printConsoleMessage(MessageTypes.NORMAL, false, "========================================================================");
        printConsoleMessage(MessageTypes.NORMAL, false, "STATUS ||         MESSAGE        ||             ACTION DON              ");
        printConsoleMessage(MessageTypes.NORMAL, false, "========================================================================");
        printConsoleMessage(MessageTypes.NORMAL, false, status + "    ||" + message + "   ||" + actionDone);
        printConsoleMessage(MessageTypes.NORMAL, false, "========================================================================");
    }


    /**
     * Register user.
     * @description register a new user
     * @author UWENAYO ALain Pacifique
     * @throws Exception the exception
     */
    public static void registerUser() throws Exception {

        Scanner scanner = new Scanner(System.in);
        UserRoles userRole = UserRoles.EMPLOYER;
        UserGender userGender = UserGender.MALE;
        printConsoleMessage(MessageTypes.NORMAL, false, "\tUSER REGISTER");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t-----------------------");
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter your first name[ex:UWENAYO]");
        String firstName = scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter your last name[ex:Alain Pacifique]");
        String lastName = scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter your gender[1:Male,2:Female]");
        if(scanner.nextInt() == 2){
            userGender = UserGender.FEMALE;
        }
        scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter your email[ex:example@domain.com]");
        String email = scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter your password[strong password sugggested]");
        String password = scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter your Date of Birth[ex:01/12/1990]");
        Date dateOfBirth = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.nextLine());
        printConsoleMessage(MessageTypes.NORMAL, false,"\tChoose account type[1:employer,2:employee]");
        if (scanner.nextInt() == 2){
            userRole = UserRoles.EMPLOYEE;
        }
        RegisterUser user = new RegisterUser(1,firstName,lastName,email,password,userGender,userRole, dateOfBirth);
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/users");
        requestBody.setAction("register");
        requestBody.setObject(user);

        String requestString = new ObjectMapper().writeValueAsString(requestBody);
        System.out.println(requestString);
        ClientServerConnector clientServerConnector = new ClientServerConnector();
        String response = clientServerConnector.connectToServer(requestString);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(response);

        int status = jsonResponse.get("status").asInt();
        String message = jsonResponse.get("message").asText();
        String actionDone = jsonResponse.get("actionToDo").asText();
        ResponsePrinter(status,message,actionDone);
    }

}

