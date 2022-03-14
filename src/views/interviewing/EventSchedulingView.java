package views.interviewing;

import clientconnector.ClientServerConnector;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.MessageTypes;
import models.Message;
import models.RequestBody;
import models.hiring.JobPosting;
import models.interviewing.EventScheduling;
import models.interviewing.EventType;
import utils.ExitApplication;
import utils.Loader;
import utils.MessagePrinter;
import views.hiring.JobPostingView;

import java.io.IOException;
import java.text.ParseException;
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
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t 1. Schedule New Event");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t 2. Get All Scheduled Events");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t 3. Add Participants");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t 4. Go Back");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, true, "\t\t Enter choice: ");
        eventScheduleChoice = scanner.nextInt();

        try{
            switch (eventScheduleChoice){
                case 1 -> ScheduleEvent();
                case 2 -> getAllScheduledEvents();
                case 3 -> addParticipant();
                case 4 -> {
                    new Loader(15, "Going back");
                    MessagePrinter.skipLines(1);
                }

                case 0 -> new ExitApplication();
                default -> {
                    MessagePrinter.skipLines(1);
                    MessagePrinter.printConsoleMessage(MessageTypes.ERROR, false, "Incorrect choice! Try again...");
                    new Loader(20, "Retrying");
                    MessagePrinter.skipLines(2);
                    mainMethod();
                }
            }
        } catch (Exception e) {
            MessagePrinter.printConsoleMessage(MessageTypes.ERROR, false, e.getMessage());
            new Loader(20, "Retrying");
            mainMethod();
        }
    }
    // choose from existing Jobposts and schedule an event
    public static void ScheduleEvent() throws IOException, InterruptedException, ParseException {
        try {
            Scanner scanner = new Scanner(System.in);
            EventType eventType = EventType.CALL;
            MessagePrinter.skipLines(1);
            MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t PROVIDE THE FOLLOWING INFORMATION TO SCHEDULE AN INTERVIEW");
            MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t --------------------------------------------------------------------");
            printConsoleMessage(MessageTypes.NORMAL, false, "\t\t CHOOSE THE JOB POST YOU WANT TO SCHEDULE AN EVENT FOR");

            JobPosting[] jobPostings;
            jobPostings = JobPostingView.getJobPosts();
            for(int i = 0; i < jobPostings.length; i++) {
                System.out.println(jobPostings[i].id + " : " + jobPostings[i].jobDesc);
            }

            printConsoleMessage(MessageTypes.NORMAL, false, "\t\t Enter Job Post ID: ");
            String jobId = scanner.nextLine();
            Integer jobPostId = Integer.parseInt(jobId);

            MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t Event Name: ");
            String eventName = scanner.nextLine();
            MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t Choose event type(call, online interview, in-person interview):");
            printConsoleMessage(MessageTypes.NORMAL, false,"\t\t 1.CALL");
            printConsoleMessage(MessageTypes.NORMAL, false,"\t\t 2.ONLINE INTERVIEW");
            printConsoleMessage(MessageTypes.NORMAL, false,"\t\t 1.IN-PERSON INTERVIEW");
            if (scanner.nextInt() == 2){
                eventType = EventType.ONLINE_INTERVIEW;
            }else if(scanner.nextInt() == 3){
                eventType = EventType.IN_PERSON_INTERVIEW;
            }
            scanner.nextLine();
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
            eventScheduling.setJobPostId(jobPostId);
            eventScheduling.setEventName(eventName);
            eventScheduling.setEventType(eventType);
            eventScheduling.setEventDate(eventDate);
            eventScheduling.setStartTime(startTime);
            eventScheduling.setEndTime(endTime);
            eventScheduling.setEventCreator(1);

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

            printConsoleMessage(MessageTypes.SUCCESS, false, "========================================================================");
            printConsoleMessage(MessageTypes.SUCCESS, false, "STATUS ||         MESSAGE        ||             ACTION DONE             ");
            printConsoleMessage(MessageTypes.SUCCESS, false, "========================================================================");
            printConsoleMessage(MessageTypes.SUCCESS, false, status + "    ||" + message + "   ||" + actionDone);
            printConsoleMessage(MessageTypes.SUCCESS, false, "========================================================================");

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
        EventScheduling[] eventSchedules = objectMapper.treeToValue(jsonNode, EventScheduling[].class);

        MessagePrinter.skipLines(1);
        printConsoleMessage(MessageTypes.SUCCESS, false, "=================================================");
        printConsoleMessage(MessageTypes.SUCCESS, false, "EVENT ID  || EVENT NAME ");
        printConsoleMessage(MessageTypes.SUCCESS, false, "=================================================");
        for (int i = 0; i < eventSchedules.length; i ++){
            printConsoleMessage(MessageTypes.SUCCESS, false, "  "+eventSchedules[i].getId()+"       || "+eventSchedules[i].getEventName());
            printConsoleMessage(MessageTypes.SUCCESS, false, "=================================================");
        }

        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
        printConsoleMessage(MessageTypes.NORMAL, false,"STATUS ||         MESSAGE        ||             ACTION DON              ");
        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
        printConsoleMessage(MessageTypes.NORMAL, false,status+"    ||" + message +"   ||" + actionToDo);
        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
        return eventSchedules;
    }

    public static void addParticipant() throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        printConsoleMessage(MessageTypes.NORMAL, false, "\t Add Participant");
        printConsoleMessage(MessageTypes.NORMAL, false, "\t ----------------");
        getAllScheduledEvents();
        printConsoleMessage(MessageTypes.NORMAL, false, "Enter event id");
        String eventId = scanner.nextLine();
        Integer scheduleId = Integer.parseInt(eventId);

    }
}