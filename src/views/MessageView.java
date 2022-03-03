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
import utils.ExitApplication;
import utils.Loader;
import utils.MessagePrinter;

import java.io.IOException;
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

    public static void DirectMessageView() throws IOException {
        int directMessageChoice;
        Scanner scanner = new Scanner(System.in);

        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "MESSAGING AND CHAT");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "------------------");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "Send Message:");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t1. Direct Message");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t2. Group Message");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t3. Go Back");
        MessagePrinter.printConsoleMessage(MessageTypes.ERROR, false, "\t0. EXIT");
        MessagePrinter.skipLines(1);
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, true, "Enter choice: ");
        directMessageChoice = scanner.nextInt();
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

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/group_messaging");
        requestBody.setAction("createGroup");
        requestBody.setObject(group);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestString = objectMapper.writeValueAsString(requestBody);

        String response = new ClientServerConnector().connectToServer(requestString);

        MessagePrinter.printConsoleMessage(MessageTypes.SUCCESS, false, response);

    }

    public static void joiningGroupRequest() throws Exception {

    }

    public static void deleteGroup() throws Exception {

    }
}
