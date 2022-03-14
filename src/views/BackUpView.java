package views;


import clientconnector.ClientServerConnector;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.node.ObjectNode;
import interfaces.MessageTypes;
import models.Group;
import models.RequestBody;
import models.UserLists;
import utils.ExitApplication;
import utils.Loader;
import utils.MessagePrinter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static utils.MessagePrinter.printConsoleMessage;

/**
 * @author Biziyaremye Henriette
 * @description This is a view to allow people to back up their messages
 * */
public class BackUpView {

    public void LoadingWrapper () throws InterruptedException, IOException {
      for(int i =0; i<=20;i++){
         MessagePrinter.printConsoleMessage(MessageTypes.ACTION, true, ".");
         Thread.sleep(100);
        }
    }
    public static void MainMethod() throws IOException, InterruptedException {
        String choice;
        Scanner sc = new Scanner(System.in);

        //loading wrapper
        new BackUpView().LoadingWrapper();
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, true, "HIRIC BACKUP PAGE");
        new BackUpView().LoadingWrapper();

        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL,false,"type y/Y if you want to proceed with the backing up");

        try {
            choice = sc.next();
            if (choice.equals("y")|| choice.equals("Y")) {
                System.out.println("yes i reached here");
                //setting our request parameters using object
                RequestBody requestBody = new RequestBody();
                requestBody.setUrl("/Archives");
                requestBody.setAction("Backup");
                requestBody.setObject(null);


                String requestString = new ObjectMapper().writeValueAsString(requestBody);
                System.out.println(requestString);
                //about to send the request to the server
                ClientServerConnector clientServerConnector = new ClientServerConnector();
                String request = clientServerConnector.connectToServer(requestString);



                //printing the response to the user
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonResponse =  objectMapper.readTree(request);


                int status = jsonResponse.get("status").asInt();
                String message = jsonResponse.get("message").asText();
                String action = jsonResponse.get("actionToDo").asText();

                if(status == 201) {
                    printConsoleMessage(MessageTypes.SUCCESS, false, "========================================================================");
                    printConsoleMessage(MessageTypes.SUCCESS, false, "STATUS ||         MESSAGE        ||             ACTION DON              ");
                    printConsoleMessage(MessageTypes.SUCCESS, false, "========================================================================");
                    printConsoleMessage(MessageTypes.SUCCESS, false, status + "    ||" + message +         "   ||"+action);
                    printConsoleMessage(MessageTypes.SUCCESS, false, "========================================================================");
                }else{
                    printConsoleMessage(MessageTypes.ERROR, false, "========================================================================");
                    printConsoleMessage(MessageTypes.ERROR, false, "STATUS ||         MESSAGE        ||             ACTION DONE              ");
                    printConsoleMessage(MessageTypes.ERROR, false, "========================================================================");
                    printConsoleMessage(MessageTypes.ERROR, false, status + "    ||" + message + "   || "+action);
                    printConsoleMessage(MessageTypes.ERROR, false, "========================================================================");
                }
            }
         System.out.println(choice);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
