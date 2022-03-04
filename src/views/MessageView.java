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
import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.MessageTypes;
import models.Group;
import models.RequestBody;
import models.UserLists;
import utils.ExitApplication;
import utils.Loader;
import utils.MessagePrinter;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

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
                case 1:
                    DirectMessageView();
                    break;
                case 2:
                    GroupMessageView();
                    break;
                case 3:
                    new Loader(15, "Going back ");
                    MessagePrinter.skipLines(1);
                    //function to retrieve the stack previous page
                    break;
                case 0:
                    new ExitApplication();
                    break;
                default:
                    MessagePrinter.skipLines(1);
                    MessagePrinter.printConsoleMessage(MessageTypes.ERROR, false, "Incorrect choice! Try again . . .");
                    new Loader(20, "Retrying ");
                    MessagePrinter.skipLines(2);
                    mainMethod();
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
                case 1:
                    GetAllUsers();
                    break;
                case 2:
                    GetAllActiveUsers();
                    break;
                case 3:
                    new Loader(15, "Searching ");
                    MessagePrinter.skipLines(1);
                    //search user by username
                    break;
                case 4:
                    new Loader(15, "Searching ");
                    //search user by userid
                    break;
                case 5:
                    new Loader(15, "Going back ");
                    MessagePrinter.skipLines(1);
                    //function to retrieve the stack previous page
                    break;
                case 0:
                    new ExitApplication();
                    break;
                default:
                    MessagePrinter.skipLines(1);
                    MessagePrinter.printConsoleMessage(MessageTypes.ERROR, false, "Incorrect choice! Try again . . .");
                    new Loader(20, "Retrying ");
                    MessagePrinter.skipLines(2);
                    DirectMessageView();
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
            MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t 1. Create Group");
            MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t 2. Join Group");
            MessagePrinter.printConsoleMessage(MessageTypes.ERROR, false, "\t\t 3. Delete Group");

            System.out.println("\t\t 1. Create new group ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createGroup();
                    break;
                case 2:
                    joiningGroupRequest();
                    break;
                case 3:
                    deleteGroup();
                    break;
                default:
                    MessagePrinter.printConsoleMessage(MessageTypes.ERROR, false, "\t\t Invalid Input");
            }

        } while (choice != 0);
    }

    public static void createGroup() throws Exception {

        Scanner scanner = new Scanner(System.in);
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t PROVIDE THE FOLLOWING INFORMATION TO CREATE NEW GROUP");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "Group Name");
        String name = scanner.nextLine();
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "Group Description");
        String description = scanner.nextLine();

        Group group = new Group();
        group.setGroupName(name);
        group.setDescription(description);
        group.setCreator(128);

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/group_messaging");
        requestBody.setAction("createGroup");
        requestBody.setObject(group);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestString = objectMapper.writeValueAsString(requestBody);
        System.out.println(requestString);

        String response = new ClientServerConnector().connectToServer(requestString);

        MessagePrinter.printConsoleMessage(MessageTypes.SUCCESS, false, response);

    }

    public static void joiningGroupRequest() throws Exception {

    }

    public static void deleteGroup() throws Exception {

    }

    public static UserLists GetAllUsers() {
        return null;
    }

    public static UserLists GetAllActiveUsers() {
        return null;
    }
}
