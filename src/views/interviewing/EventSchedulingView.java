package views.interviewing;

import clientconnector.ClientServerConnector;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.MessageTypes;
import models.Message;
import models.RequestBody;
import models.interviewing.EventScheduling;
import utils.ExitApplication;
import utils.Loader;
import utils.MessagePrinter;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static utils.MessagePrinter.printConsoleMessage;

/**@author I_Clarisse
 * @description event scheduling view
 */
public class EventSchedulingView {
    public void mainMethod() throws Exception{
        int eventScheduleChoice;
        Scanner scanner = new Scanner(System.in);

        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t EVENT SCHEDULING");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t ----------------");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t 1. Schedule event");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t 2. Get all scheduled events");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t 3. Go Back");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, true, "\t\t Enter choice: ");
        eventScheduleChoice = scanner.nextInt();

        try{
            switch (eventScheduleChoice){
                case 1:
                    ScheduleEvent();
                    break;
                case 2:
                    getAllScheduledEvents();
                    break;
                case 3:
                    new Loader(15, "Going back");
                    MessagePrinter.skipLines(1);
                    break;
                case 0:
                    new ExitApplication();
                    break;
                default:
                    MessagePrinter.skipLines(1);
                    MessagePrinter.printConsoleMessage(MessageTypes.ERROR, false, "Incorrect choice! Try again...");
                    new Loader(20, "Retrying");
                    MessagePrinter.skipLines(2);
                    mainMethod();
            }
        } catch (Exception e) {
            MessagePrinter.printConsoleMessage(MessageTypes.ERROR, false, e.getMessage());
            new Loader(20, "Retrying");
            mainMethod();
        }
    }

    public static void ScheduleEvent() throws IOException, InterruptedException, ParseException {
        try {
            Scanner scanner = new Scanner(System.in);

            MessagePrinter.skipLines(1);
            MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t PROVIDE THE FOLLOWING INFORMATION TO SCHEDULE AN INTERVIEW");
            MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t --------------------------------------------------------------------");
            MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t Event Name: ");
            String eventName = scanner.nextLine();
            MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t Choose event type(call, online interview, in-person interview):");
            String eventType = scanner.nextLine();

            MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t Enter the event date(dd/MM/yyyy): ");
            String eventDate = scanner.nextLine();
//            Date eventDate = new SimpleDateFormat("dd/MM/yyy").parse(myDate);

            MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t Enter starting time(\" hh:mm:ss\")");
            String startTime = scanner.nextLine();
//            Time startTime = Time.valueOf(time1);

            MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t Enter ending time(\" hh:mm:ss\"): ");
            String endTime = scanner.nextLine();
//            Time endTime = Time.valueOf(time2);

            EventScheduling eventScheduling = new EventScheduling();
            eventScheduling.setEventName(eventName);
            eventScheduling.setEventType(eventType);
            eventScheduling.setEventDate(eventDate);
            eventScheduling.setStartTime(startTime);
            eventScheduling.setEndTime(endTime);
            eventScheduling.setEventCreator(102);

            RequestBody requestBody = new RequestBody();
            requestBody.setUrl("/eventScheduling");
            requestBody.setAction("scheduleEvent");
            requestBody.setObject(eventScheduling);

            String requestString = new ObjectMapper().writeValueAsString(requestBody);
            // sending object to server
            ClientServerConnector clientServerConnector = new ClientServerConnector();
            String response = clientServerConnector.connectToServer(requestString);

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

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //get all scheduled events
    public static EventScheduling[] getAllScheduledEvents() throws IOException, InterruptedException{
        MessagePrinter.skipLines(1);
        printConsoleMessage(MessageTypes.NORMAL, false, "\t\t ALL SCHEDULED EVENTS");
        printConsoleMessage(MessageTypes.NORMAL, false, "\t\t ---------------------");

        EventScheduling eventScheduling = new EventScheduling();
        eventScheduling.getEventName();
        eventScheduling.getEventType();
        eventScheduling.getEventDate();

        RequestBody requestBody = new RequestBody();

        requestBody.setUrl("/eventScheduling");
        requestBody.setAction("getAllScheduledEvents");

        String requestString = new ObjectMapper().writeValueAsString(requestBody);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        String response = clientServerConnector.connectToServer(requestString);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(response);

        int status = jsonResponse.get("status").asInt();
        String message = jsonResponse.get("message").asText();
        String actionToDo = jsonResponse.get("actionToDo").asText();

        JsonNode jsonNode = objectMapper.readTree(String.valueOf(jsonResponse.get("object")));
//        System.out.println(jsonNode);
        EventScheduling[] eventSchedules = objectMapper.treeToValue(jsonNode, EventScheduling[].class);

        for (int i = 0; i < eventSchedules.length; i ++){
            printConsoleMessage(MessageTypes.NORMAL, false, "\t\t"+eventSchedules[i].getId()+"\t\t"+eventSchedules[i].getEventName());
            System.out.println();
        }

        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
        printConsoleMessage(MessageTypes.NORMAL, false,"STATUS ||         MESSAGE        ||             ACTION DON              ");
        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
        printConsoleMessage(MessageTypes.NORMAL, false,status+"    ||" + message +"   ||" + actionToDo);
        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
        return eventSchedules;


    }
}