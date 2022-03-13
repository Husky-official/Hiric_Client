package views;

/**
 * @author : Abijuru Seth,
 * @author : DABAGIRE Valens
 * @description : Main entrance to the messaging & chat module;
 * - get all main options to the group chat or direct messaging
 * - group chatting(
 * *allow user to create
 */

import clientconnector.ClientServerConnector;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.MessageTypes;
import models.*;
import utils.ExitApplication;
import utils.Loader;
import utils.MessagePrinter;

import java.io.*;
import java.lang.reflect.Array;
import java.net.Inet4Address;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static views.GroupMessagingView.*;

public class MessageView {

    public static void mainMethod() throws IOException, InterruptedException {
        int mainChoice;
        Scanner scanner = new Scanner(System.in);

        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "MESSAGING AND CHAT");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "------------------");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "Send Message:");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t1. Direct Message");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t2. Group Message");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t3. Go Back");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t0. Exit");
        MessagePrinter.skipLines(1);
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, true, "Enter choice: ");
        mainChoice = scanner.nextInt();

        try {
            switch (mainChoice) {
                case 1 -> DirectMessageView();
                case 2 -> GroupMessageView();
                case 3 -> {
                    new Loader(15, "Going back ");
                    MessagePrinter.skipLines(1);
                }
                //function to retrieve the stack previous page
                case 0 -> new ExitApplication();
                default -> {
                    MessagePrinter.skipLines(1);
                    MessagePrinter.printConsoleMessage(MessageTypes.ERROR, false, "Incorrect choice! Try again . . .");
                    new Loader(20, "Retrying ");
                    MessagePrinter.skipLines(2);
                    mainMethod();
                }
            }
        } catch (Exception error) {
            MessagePrinter.printConsoleMessage(MessageTypes.ERROR, false, error.getMessage());
            new Loader(20, "Retrying ");
            mainMethod();
        }
    }

    public static void DirectMessageView() throws IOException, InterruptedException {
        int directMessageChoice;
        Scanner scanner = new Scanner(System.in);
        MessagePrinter.skipLines(1);
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "Direct Messages");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "---------------");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t1. List all users");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t2. Active users");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t3. Search user (name)");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t4. Search user (ID)");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t5. Go Back");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t0. Exit");
        MessagePrinter.skipLines(1);
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, true, "Enter choice: ");
        directMessageChoice = scanner.nextInt();

        try {
            switch (directMessageChoice) {
                case 1 -> GetAllUsers();
                case 2 -> GetAllActiveUsers();
                case 3 -> {
                    new Loader(15, "Searching ");
                    MessagePrinter.skipLines(1);
                }
                //search user by username
                case 4 -> new Loader(15, "Searching ");

                //search user by userid
                case 5 -> {
                    new Loader(15, "Going back ");
                    MessagePrinter.skipLines(1);
                }
                //function to retrieve the stack previous page
                case 0 -> new ExitApplication();
                default -> {
                    MessagePrinter.skipLines(1);
                    MessagePrinter.printConsoleMessage(MessageTypes.ERROR, false, "Incorrect choice! Try again . . .");
                    new Loader(20, "Retrying ");
                    MessagePrinter.skipLines(2);
                    DirectMessageView();
                }
            }
        } catch (Exception error) {
            MessagePrinter.printConsoleMessage(MessageTypes.ERROR, false, error.getMessage());
            new Loader(20, "Retrying ");
            DirectMessageView();
        }
    }

    public static void GroupMessageView() throws Exception {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "What You Want To do");
            MessagePrinter.printConsoleMessage(MessageTypes.SUCCESS, false, "\t\t 1. Create Group");
            MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t 2. Join Group");
            MessagePrinter.printConsoleMessage(MessageTypes.ERROR, false, "\t\t 3. Delete Group");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> createGroup();
                case 2 -> joiningGroupRequest();
                case 3 -> deleteGroup();
                default -> MessagePrinter.printConsoleMessage(MessageTypes.ERROR, false, "\t\t Invalid Input");
            }

        } while (choice != 0);
    } 

    public static void GetAllUsers() throws IOException, InterruptedException {
        try {
            RequestBody requestBody = new RequestBody();
            requestBody.setUrl("/messages");
            requestBody.setAction("users");
            requestBody.setObject(null);

            String requestString = new ObjectMapper().writeValueAsString(requestBody);
            ClientServerConnector clientServerConnector = new ClientServerConnector();
            String responseString = clientServerConnector.connectToServer(requestString);
            new Loader(17, "Loading ");
            MessagePrinter.skipLines(1);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonResponse = objectMapper.readTree(responseString);
            int status = jsonResponse.get("status").asInt();

            if (status == 404) {
                MessagePrinter.skipLines(1);
                MessagePrinter.printConsoleMessage(MessageTypes.WARNING, false, "No users found");
                MessagePrinter.skipLines(1);
                DirectMessageView();
                return;
            }

            String usersString = jsonResponse.get("object").asText();
            String[] arr = usersString.split(" ");

            ArrayList<String> users = new ArrayList<>(Arrays.asList(arr));
            MessagePrinter.skipLines(1);
            MessagePrinter.printConsoleMessage(MessageTypes.SUCCESS, false, "Users to chat with:");
            MessagePrinter.skipLines(1);

            for (int i = 0; i < users.size(); i++) {
                MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t"+(i+1)+". "+users.get(i));
            }

            MessagePrinter.skipLines(1);
            MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, true, "Who do you want to chat with (ID): ");

            Scanner scanner = new Scanner(System.in);
            int chatWithChoice;
            chatWithChoice = scanner.nextInt();
            --chatWithChoice;
            String directMessageTo = users.get(chatWithChoice);

            if (directMessageTo != null) {
                DirectChatWithMemberView(directMessageTo, chatWithChoice);
            } else {
                MessagePrinter.skipLines(1);
                MessagePrinter.printConsoleMessage(MessageTypes.ERROR, false, "Unable to find this user! Try checking the ID.");
                new Loader(15, "Re-trying ");
                MessagePrinter.skipLines(1);
                DirectMessageView();
            }

        } catch(Exception error) {
            MessagePrinter.printConsoleMessage(MessageTypes.ERROR, false, error.getMessage());
            new Loader(15, "Re-trying ");
            MessagePrinter.skipLines(1);
            DirectMessageView();
        }
    }

    public static void DirectChatWithMemberView(String memberName ,int memberID) throws IOException, InterruptedException {

        if (CheckUserExistence(memberName, memberID)) {

            MessagePrinter.skipLines(1);
            new Loader(12, "Initializing chat ");
            MessagePrinter.skipLines(1);

            boolean firstTime = false;
            if (GetChattingLifeSpan()) {
                IntializeFirstTimeChat();
                firstTime = true;
            }

            if (firstTime) {
                MessagePrinter.printConsoleMessage(MessageTypes.ACTION, false, "You can now start chating with "+memberName);
            }

            InitialChattingEnvironment(memberName, memberID);


        } else {
            MessagePrinter.skipLines(1);
            MessagePrinter.printConsoleMessage(MessageTypes.ERROR, false, "Unable to find this user!");
            new Loader(15, "Re-trying ");
            MessagePrinter.skipLines(1);
            DirectMessageView();
        }

    }

    public static Boolean CheckUserExistence(String memberName, int memberID) throws IOException, InterruptedException {
        CheckUserExistence checkUserExistence = new CheckUserExistence();
        checkUserExistence.setMemberID(memberID);
        checkUserExistence.setMemberName(memberName);

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/messages");
        requestBody.setAction("checkUser");
        requestBody.setObject(checkUserExistence);

        String requestString = new ObjectMapper().writeValueAsString(requestBody);
        ClientServerConnector clientServerConnector = new ClientServerConnector();
        String responseString = clientServerConnector.connectToServer(requestString);
        MessagePrinter.skipLines(1);
        new Loader(10, "Checking membership ");
        MessagePrinter.skipLines(1);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(responseString);
        int status = jsonResponse.get("status").asInt();

        try {

            if (status == 200) {
                MessagePrinter.skipLines(1);
                MessagePrinter.printConsoleMessage(MessageTypes.SUCCESS, false, "Membership found!");
                return true;
            }

            if (status == 404) {
                MessagePrinter.skipLines(1);
                MessagePrinter.printConsoleMessage(MessageTypes.ERROR, false, "Invalid membership!");
                return false;
            }

        } catch(Exception error) {
            MessagePrinter.printConsoleMessage(MessageTypes.ERROR, false, error.getMessage());
            new Loader(15, "Re-trying ");
            MessagePrinter.skipLines(1);
            DirectMessageView();
        }

        return false;
    }

    public static UserLists GetAllActiveUsers() {
        return null;
    }

    public static void IntializeFirstTimeChat() throws IOException, InterruptedException {
        MessagePrinter.skipLines(1);
        MessagePrinter.printConsoleMessage(MessageTypes.SUCCESS, false, "THIS IS YOUR FIRST TIME CHATTING.");
        new Loader(6, "Setting up you chat environment ");
        MessagePrinter.skipLines(1);

        Inet4Address myIpAddress = (Inet4Address) Inet4Address.getLocalHost();
        ClientChat client = new ClientChat();
        client.setHostname(myIpAddress.toString().split("/")[1]);
        client.setUserId(1); //get user id when logged in
        Random random = new Random();
        int portNumber = random.nextInt(999999);
        while (portNumber > 65535) {
            portNumber = random.nextInt(999999);
        }
        client.setPort(portNumber);
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/messages");
        requestBody.setAction("firstChatInit");
        requestBody.setObject(client);

        String requestString = new ObjectMapper().writeValueAsString(requestBody);
        ClientServerConnector clientServerConnector = new ClientServerConnector();

        String responseString = clientServerConnector.connectToServer(requestString);
        new Loader(25, "Storing info ");
        MessagePrinter.skipLines(1);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(responseString);
        int status = jsonResponse.get("status").asInt();

        if (status != 200) {
            MessagePrinter.skipLines(1);
            MessagePrinter.printConsoleMessage(MessageTypes.ERROR, false, "Unable to initial your chat environment.");
            new Loader(30, "Re-trying ");
            MessagePrinter.skipLines(1);
            DirectMessageView();
        }

    }

    public static Boolean GetChattingLifeSpan() {
        return true;
    }

    public static void InitialChattingEnvironment(String memberName, int memberID) throws IOException, InterruptedException {
        /**
         * @description : initializing a socket between the client chatting
         * - using the stored ip address and randomly chosen ports
         * */

        MessagePrinter.skipLines(1);
        MessagePrinter.printConsoleMessage(MessageTypes.ACTION, false, "START CHATTING");
        MessagePrinter.printConsoleMessage(MessageTypes.ACTION, false, "--------------");
        MessagePrinter.skipLines(1);
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "==============================================================");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "+ Press 1: Quit chat | Press 2: Get help | Press 3: Exit app +");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "==============================================================");

        Scanner scanner = new Scanner(System.in);
        String message;
        MessagePrinter.printConsoleMessage(MessageTypes.SUCCESS, true, "Seth: "); //get user name from login
        message = scanner.nextLine();
        Boolean continued = false;

        if (Objects.equals(message, "1") || Objects.equals(message, "2") || Objects.equals(message, "3")) {

            MessagePrinter.skipLines(1);
            Scanner scanner_one = new Scanner(System.in);
            MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, true, "Executing chat options (y/n): ");
            String choice;
            choice = scanner_one.nextLine();

            switch (choice) {
                case "y":
                    continued = true;
                    switch (message) {
                        case "1":
                            mainMethod();
                            break;
                        case "2":
                            ShowQuickChattingHelp();
                            break;
                        case "3":
                            new ExitApplication();
                            break;
                        default:
                            MessagePrinter.skipLines(1);
                            MessagePrinter.printConsoleMessage(MessageTypes.ERROR, false, "Incorrect choice!");
                            new Loader(7, "Retrying ");
                            MessagePrinter.skipLines(2);
                            DirectMessageView();
                    }
                    break;
                default:
                    MessagePrinter.printConsoleMessage(MessageTypes.ACTION, false, "Ok continuing ....");
            }

        }

        if (!continued) {
            //create replying func

            Message newMessage = new Message();
            newMessage.setMessageContent(message);
            newMessage.setMessageType("reply");
            newMessage.setOriginalMessage(1); //set original message id
            newMessage.setSender(1); //get user id from login
            newMessage.setReceiver(memberID);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            newMessage.setSentAt(dtf.format(now));

            RequestBody requestBody = new RequestBody();
            requestBody.setUrl("/messages");
            requestBody.setAction("sendMessage");
            requestBody.setObject(newMessage);
            String requestString = new ObjectMapper().writeValueAsString(requestBody);
            ClientServerConnector clientServerConnector = new ClientServerConnector();
            String responseString = clientServerConnector.connectToServer(requestString);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonResponse = objectMapper.readTree(responseString);
            int status = jsonResponse.get("status").asInt();
            JsonNode userData = jsonResponse.get("object");
            Iterator<Map.Entry<String, JsonNode>> iterator = userData.fields();
            iterator.next();
            iterator.next();
            String messageContent = iterator.next().toString().split("=")[1];
            iterator.next();
            String senderID = iterator.next().toString().split("=")[1];
            String receiverID = iterator.next().toString().split("=")[1];

            if (Integer.parseInt(senderID) == 1) { //get user id from login
                if (status != 200) {
                    MessagePrinter.skipLines(1);
                    MessagePrinter.printConsoleMessage(MessageTypes.ERROR, false, "Message not sent!");
                    new Loader(30, "Re-trying ");
                    MessagePrinter.skipLines(1);
                    DirectMessageView();
                }
            }

            while (Integer.parseInt(senderID) != Integer.parseInt(receiverID)) {

                if (Integer.parseInt(receiverID) == memberID && Integer.parseInt(senderID) != 1) { //1 will be replaced by my id from login
                    MessagePrinter.skipLines(1);
                    MessagePrinter.printConsoleMessage(MessageTypes.WARNING, true, memberName+": ");
                    MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, messageContent);
                    MessagePrinter.skipLines(1);
                }

            }

        } else {
            MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, true, "Continue chatting (y/n): ");
            String choice_cont;
            choice_cont = scanner.nextLine();
            switch (choice_cont) {
                case "y":
                    //including the reply func
                    break;
                default:
                    MessagePrinter.skipLines(1);
                    MessagePrinter.printConsoleMessage(MessageTypes.ERROR, false, "Unable to initial your chat environment.");
                    new Loader(30, "Re-trying ");
                    MessagePrinter.skipLines(1);
                    DirectMessageView();
            }
        }
    }

    public static void ShowQuickChattingHelp() throws IOException {
        MessagePrinter.skipLines(1);
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false,"==============================================================");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "+         CHATTING HELP AND COMMANDS ALONG SHORTCUTS         +");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "==============================================================");
        MessagePrinter.printConsoleMessage(MessageTypes.WARNING, false, "\t Tips:");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t 1. Immediately after initializing the chatting env start typing you message next to your name");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t 2. When you enter a message that is similar to the chat shortcuts you will choose to continue or not");
        MessagePrinter.printConsoleMessage(MessageTypes.WARNING, false, "\t Commands and shortcuts:");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t 1. Press 1 to Quit chatting with the user");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t 2. Press 2 to Get help using the chat");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t 3. Press 3 to Quit the whole application");
        MessagePrinter.skipLines(1);
    }

}
