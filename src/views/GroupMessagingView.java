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

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

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
        int userId = 3;

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

    public static void leaveGroup() throws Exception{

        Scanner scanner = new Scanner(System.in);
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t PROVIDE THE FOLLOWING INFORMATION TO LEAVE GROUP");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "Group Id");
        int groupId = scanner.nextInt();
        int userId = 3;

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

    public static void groupChatting() throws Exception{
        Scanner scanner = new Scanner(System.in);
        int choice;

            MessagePrinter.printConsoleMessage(MessageTypes.SUCCESS, false, "\t\tWhat You Want To do?");
            MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t=====================");
            MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t 1. Send Message");
            MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t 2. Edit Message");
            MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t 3. List Group Messages");
            MessagePrinter.printConsoleMessage(MessageTypes.ERROR, false, "\t\t 4. Delete Message");
            MessagePrinter.printConsoleMessage(MessageTypes.ERROR, false, "\t\t --------------------");
            MessagePrinter.printConsoleMessage(MessageTypes.ERROR, false, "\t\t 0. Exit");

            choice = scanner.nextInt();

            switch (choice){
                case 1 -> sendMessage();
                case 0 -> new ExitApplication();
                default -> {
                    MessagePrinter.skipLines(1);
                    MessagePrinter.printConsoleMessage(MessageTypes.ERROR, false, "Incorrect choice! Try again . . .");
                    new Loader(20, "Retrying ");
                    MessagePrinter.skipLines(2);
                    groupChatting();
                }
            }
    }

    public static boolean isMember(int id, int groupId) throws Exception{

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

        return  isAMember;
    }

    public static void sendMessage() throws Exception{

        Scanner scanner = new Scanner(System.in);
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t PROVIDE THE FOLLOWING INFORMATION TO CREATE NEW GROUP");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "Group ID");
        int groupID = scanner.nextInt();
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "Message Type");
        String messageType = scanner.next();
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "Content");
        String content = scanner.nextLine();

        if(isMember(1, groupID)){
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
        } else{
            new Loader(15, "wait ");
            MessagePrinter.skipLines(2);

            MessagePrinter.printConsoleMessage(MessageTypes.ERROR, false, "You can not send message in this group bcz you are not its member.");
        }
    }

}
