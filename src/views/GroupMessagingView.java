package views;

import clientconnector.ClientServerConnector;
import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.MessageTypes;
import models.Group;
import models.GroupMember;
import models.RequestBody;
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
}
