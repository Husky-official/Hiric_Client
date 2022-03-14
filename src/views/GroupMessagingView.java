package views;

/**
 * @author : DABAGIRE Valens
 * @description : this module helps people to create, join, leave, delete
 * and send message with in a group
 */

import clientconnector.ClientServerConnector;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.MessageTypes;
import models.Group;
import models.GroupMember;
import models.Message;
import models.RequestBody;
import utils.ExitApplication;
import utils.Loader;
import utils.MessagePrinter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import static utils.MessagePrinter.printConsoleMessage;

public class GroupMessagingView {
    public static void createGroup() throws Exception {

        Scanner scanner = new Scanner(System.in);
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t PROVIDE THE FOLLOWING INFORMATION TO CREATE NEW GROUP");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "Group Name");
        String name = scanner.nextLine();
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "Group Description");
        String description = scanner.nextLine();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = simpleDateFormat.format(new Date());

        Group group = new Group();
        group.setGroupName(name);
        group.setGroupDescription(description);
        group.setUpdatedAt(java.sql.Date.valueOf(currentDate));
        group.setCreatedAt(java.sql.Date.valueOf(currentDate));


        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/group_messaging");
        requestBody.setAction("createGroup");
        requestBody.setObject(group);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestString = objectMapper.writeValueAsString(requestBody);
        String response = new ClientServerConnector().connectToServer(requestString);

        new Loader(15, "wait ");
        MessagePrinter.skipLines(2);

        MessagePrinter.printConsoleMessage(MessageTypes.SUCCESS, false, response);

    }

    public static void joiningGroupRequest() throws Exception {
        Scanner scanner = new Scanner(System.in);
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t PROVIDE THE FOLLOWING INFORMATION TO JOIN GROUP");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "Group Id");
        int groupId = scanner.nextInt();
        int userId = 51;

        GroupMember groupMember = new GroupMember(userId, groupId);

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/group_messaging");
        requestBody.setAction("joinGroup");
        requestBody.setObject(groupMember);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestString = objectMapper.writeValueAsString(requestBody);
        String response = new ClientServerConnector().connectToServer(requestString);

        new Loader(15, "wait ");
        MessagePrinter.skipLines(2);

        MessagePrinter.printConsoleMessage(MessageTypes.SUCCESS, false, response);

    }

    public static void deleteGroup() throws Exception {
        Scanner scanner = new Scanner(System.in);
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t PROVIDE THE FOLLOWING INFORMATION TO DELETE GROUP");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "Group Id");
        int groupId = scanner.nextInt();

        Group groupToDelete = new Group(groupId);

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/group_messaging");
        requestBody.setAction("deleteGroup");
        requestBody.setObject(groupToDelete);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestString = objectMapper.writeValueAsString(requestBody);
        String response = new ClientServerConnector().connectToServer(requestString);

        new Loader(15, "wait ");
        MessagePrinter.skipLines(2);

        MessagePrinter.printConsoleMessage(MessageTypes.SUCCESS, false, response);
    }

    public static void leaveGroup() throws Exception {

        Scanner scanner = new Scanner(System.in);
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t PROVIDE THE FOLLOWING INFORMATION TO LEAVE GROUP");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "Group Id");
        int groupId = scanner.nextInt();
        int userId = 51;

        GroupMember groupMember = new GroupMember(userId, groupId);

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/group_messaging");
        requestBody.setAction("leaveGroup");
        requestBody.setObject(groupMember);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestString = objectMapper.writeValueAsString(requestBody);
        String response = new ClientServerConnector().connectToServer(requestString);

        new Loader(15, "wait ");
        MessagePrinter.skipLines(2);

        MessagePrinter.printConsoleMessage(MessageTypes.SUCCESS, false, response);
    }

    public static void groupChatting() throws Exception {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||-------------------------------------------------------------------||");
            printConsoleMessage(MessageTypes.SUCCESS, false,"\t\t\t||-----------------     GROUP CHATTING             ------------------||");
            printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||-------------------------------------------------------------------||");
            printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||------------------    1. SEND MESSAGE            ------------------||");
            printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||------------------    2. EDIT MESSAGE            ------------------||");
            printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||------------------    3. CONTINUE TO A GROUP     ------------------||");
            printConsoleMessage(MessageTypes.ERROR, false,"\t\t\t||------------------    4. DELETE MESSAGE          ------------------||");
            printConsoleMessage(MessageTypes.ERROR, false,"\t\t\t||------------------    0. EXIT                    ------------------||");
            printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||-------------------------------------------------------------------||");

            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> sendMessage();
                case 2 -> editMessage();
                case 3 -> allGroupMessages();
                case 0 -> new ExitApplication();
                default -> {
                    MessagePrinter.skipLines(1);
                    MessagePrinter.printConsoleMessage(MessageTypes.ERROR, false, "Incorrect choice! Try again . . .");
                    new Loader(20, "Retrying ");
                    MessagePrinter.skipLines(2);
                    groupChatting();
                }
            }
        } while (choice != 0);
    }

    public static boolean isMember(int id, int groupId) throws Exception {

        boolean isAMember;
        GroupMember member = new GroupMember();
        member.setMember_id(id);
        member.setGroup_id(groupId);

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/group_messaging");
        requestBody.setAction("checkMemberShip");
        requestBody.setObject(member);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestString = objectMapper.writeValueAsString(requestBody);
        String response = new ClientServerConnector().connectToServer(requestString);

        ObjectMapper res = new ObjectMapper();
        JsonNode jsonNode = res.readTree(response);

        int status = Integer.parseInt(jsonNode.get("status").asText());

        isAMember = status == 200;

        return isAMember;
    }

    public static void sendMessage() throws Exception {

        Scanner scanner = new Scanner(System.in);
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t PROVIDE THE FOLLOWING INFORMATION TO SEND MESSAGE");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "Group ID");
        int groupID = scanner.nextInt();
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "Message Type");
        String messageType = scanner.next();
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "Content");
        String content = scanner.next();

        if (isMember(51, groupID)) {
            Message message = new Message();
            message.setMessageType(messageType);
            message.setReceiver(groupID);
            message.setSender(1);
            message.setMessageContent(content);
            message.setOriginalMessage(1);
            message.setSentAt("2022/03/13 19:12:24");


            RequestBody requestBody = new RequestBody();
            requestBody.setUrl("/group_messaging");
            requestBody.setAction("sendMessage");
            requestBody.setObject(message);

            ObjectMapper objectMapper = new ObjectMapper();
            String requestString = objectMapper.writeValueAsString(requestBody);
            String response = new ClientServerConnector().connectToServer(requestString);

            new Loader(15, "wait ");
            MessagePrinter.skipLines(2);

            MessagePrinter.printConsoleMessage(MessageTypes.SUCCESS, false, response);
        } else {
            new Loader(15, "wait ");
            MessagePrinter.skipLines(2);

            MessagePrinter.printConsoleMessage(MessageTypes.ERROR, false, "You can not send message in this group bcz you are not its member.");
        }
    }

    public static void editMessage() throws Exception {
        Scanner scanner = new Scanner(System.in);
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t PROVIDE THE FOLLOWING INFORMATION TO EDIT GROUP MESSAGE");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "Message ID");
        int messageID = scanner.nextInt();
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "Content");
        String content = scanner.next();

        Message message = new Message();
        message.setMessageId(messageID);
        message.setMessageContent(content);

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/group_messaging");
        requestBody.setAction("editMessage");
        requestBody.setObject(message);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestString = objectMapper.writeValueAsString(requestBody);
        String response = new ClientServerConnector().connectToServer(requestString);

        new Loader(15, "wait ");
        MessagePrinter.skipLines(2);

        MessagePrinter.printConsoleMessage(MessageTypes.SUCCESS, false, response);
    }

    public static void allGroupMessages() throws Exception{

        Scanner scanner = new Scanner(System.in);

        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t PROVIDE THE FOLLOWING INFORMATION TO VIEW ALL GROUP MESSAGES");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "Group ID");
        int groupID = scanner.nextInt();

        if(isMember(51, groupID)){
            Group group = new Group();
            group.setId(groupID);

            RequestBody requestBody = new RequestBody();
            requestBody.setUrl("/group_messaging");
            requestBody.setAction("allMessages");
            requestBody.setObject(group);

            ObjectMapper objectMapper = new ObjectMapper();
            String requestString = objectMapper.writeValueAsString(requestBody);
            String response = new ClientServerConnector().connectToServer(requestString);

            new Loader(15, "wait ");
            MessagePrinter.skipLines(2);

            MessagePrinter.printConsoleMessage(MessageTypes.SUCCESS, false, response);


            MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "");
            MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "");
            MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "");
            MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "");
            MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "");
            MessagePrinter.printConsoleMessage(MessageTypes.SUCCESS, false, "DO YOU WANT TO CHAT?");

            MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t 1. Yes ");
            MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t 2. No ");

            int choice = scanner.nextInt();

            if(choice == 1){

                chat(groupID);
            }

        }else{
            MessagePrinter.printConsoleMessage(MessageTypes.ERROR, false, "You are not allowed to view messages from private groups.");
        }
    }

    public static void chat(int groupId) throws Exception{

        Socket socket = new Socket("localhost", 8888);

        DataOutputStream requestOut = new DataOutputStream(socket.getOutputStream());
        DataInputStream responseIn = new DataInputStream(socket.getInputStream());
        Scanner scanner = new Scanner(System.in);

        String message = scanner.next();

        requestOut.flush();
        requestOut.writeUTF(message);

        System.out.println(responseIn.readUTF());

    }

}
