package views.interviewing;

import clientconnector.ClientServerConnector;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.MessageTypes;
import models.RequestBody;
import models.hiring.JobPosting;
import models.hiring.ShortlistedEmployees;
import models.interviewing.EventParticipation;
import models.interviewing.EventScheduling;
import models.interviewing.EventType;
import utils.ExitApplication;
import utils.Loader;
import utils.MessagePrinter;

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

    public static JobPosting[] getJobPosts() throws Exception {
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/get_job_posts?userId=" + 1);
        requestBody.setAction("get jobs");

        String requestString = new ObjectMapper().writeValueAsString(requestBody);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        String response = clientServerConnector.connectToServer(requestString);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode res = objectMapper.readTree(response);


        ObjectMapper objectMapper1 = new ObjectMapper();
        JsonNode jsonResponse = objectMapper1.readTree(response);
        JsonNode jsonNode = objectMapper1.readTree(String.valueOf(jsonResponse.get("object")));
        JobPosting[] jobPostings = objectMapper1.treeToValue(jsonNode, JobPosting[].class);
        return jobPostings;
    }

    // choose from existing JobPosts and schedule an event
    public static void ScheduleEvent() throws IOException, InterruptedException, ParseException {
        try {
            Scanner scanner = new Scanner(System.in);
            EventType eventType = EventType.CALL;
            MessagePrinter.skipLines(1);

            MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t PROVIDE THE FOLLOWING INFORMATION TO SCHEDULE AN INTERVIEW");
            MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t --------------------------------------------------------------------");
            printConsoleMessage(MessageTypes.NORMAL, false, "\t\t CHOOSE THE JOB POST YOU WANT TO SCHEDULE AN EVENT FOR");

            new Loader(17, "Listing");
            MessagePrinter.skipLines(1);

            JobPosting[] jobPostings;
            jobPostings = getJobPosts();
            for(int i = 0; i < jobPostings.length; i++) {
                printConsoleMessage(MessageTypes.NORMAL, false, "\t\t"+jobPostings[i].id + " : " + jobPostings[i].jobDesc);
            }
            MessagePrinter.skipLines(1);
            printConsoleMessage(MessageTypes.NORMAL, false, "\t\t Enter Job Post ID: ");
            String jobId = scanner.nextLine();
            Integer jobPostId = Integer.parseInt(jobId);

            MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t Event Name: ");
            String eventName = scanner.nextLine();
            int eventTypeChoice;
            MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false, "\t\t Choose event type:");
            printConsoleMessage(MessageTypes.NORMAL, false,"\t\t 1.CALL");
            printConsoleMessage(MessageTypes.NORMAL, false,"\t\t 2.ONLINE INTERVIEW");
            printConsoleMessage(MessageTypes.NORMAL, false,"\t\t 3.IN-PERSON INTERVIEW");

            eventTypeChoice = scanner.nextInt();
            switch (eventTypeChoice){
                case 1 -> eventType = EventType.CALL;
                case 2 -> eventType = EventType.ONLINE_INTERVIEW;
                case 3 -> eventType = EventType.IN_PERSON_INTERVIEW;
                default -> {
                    MessagePrinter.skipLines(1);
                    MessagePrinter.printConsoleMessage(MessageTypes.ERROR, false, "Incorrect choice! Try again...");
                    new Loader(20, "Retrying");
                    MessagePrinter.skipLines(2);
                }
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

            printConsoleMessage(MessageTypes.SUCCESS,false, message);
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

        JsonNode jsonNode = objectMapper.readTree(String.valueOf(jsonResponse.get("object")));
        EventScheduling[] eventSchedules = objectMapper.treeToValue(jsonNode, EventScheduling[].class);

        MessagePrinter.skipLines(1);
        printConsoleMessage(MessageTypes.NORMAL, false, "=============================================================");
        printConsoleMessage(MessageTypes.NORMAL, false, "  EVENT ID ||     EVENT NAME     ");
        printConsoleMessage(MessageTypes.NORMAL, false, "=============================================================");
        for (int i = 0; i < eventSchedules.length; i ++){
            printConsoleMessage(MessageTypes.NORMAL, false, "     "+eventSchedules[i].getId()+"     ||     "+eventSchedules[i].getEventName());
            printConsoleMessage(MessageTypes.NORMAL, false, "==========================================================");
        }
        return eventSchedules;
    }
    public static ShortlistedEmployees[] getShortlistedEmployees(int eventId) throws IOException {
        MessagePrinter.skipLines(1);
        printConsoleMessage(MessageTypes.NORMAL, false, "-----------------------");
        printConsoleMessage(MessageTypes.NORMAL, false, "Shortlisted Employees");
        printConsoleMessage(MessageTypes.NORMAL, false, "-----------------------");

        ShortlistedEmployees shortlistedEmployee = new ShortlistedEmployees();
        shortlistedEmployee.setEventId(eventId);
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/shortlistedEmployees");
        requestBody.setAction("getShortlistedEmployees");
        requestBody.setObject(shortlistedEmployee);

        String requestString = new ObjectMapper().writeValueAsString(requestBody);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        String response = clientServerConnector.connectToServer(requestString);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(response);
        JsonNode jsonNode = objectMapper.readTree(String.valueOf(jsonResponse.get("object")));
        ShortlistedEmployees[] shortlistedEmployees = objectMapper.treeToValue(jsonNode, ShortlistedEmployees[].class);
        if (shortlistedEmployees.length == 0){
            System.out.println("There are no shortlisted Employees");
        }
        for(int i = 0; i<shortlistedEmployees.length; i++) {
            printConsoleMessage(MessageTypes.NORMAL, false, "\t" + shortlistedEmployees[i].getUserId() + "." + shortlistedEmployees[i].getReferenceName());
        }
        return shortlistedEmployees;
    }
    public static void addParticipant() throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        MessagePrinter.skipLines(1);
        printConsoleMessage(MessageTypes.NORMAL, false, "\t Add Participant");
        printConsoleMessage(MessageTypes.NORMAL, false, "\t ----------------");
        getAllScheduledEvents();
        printConsoleMessage(MessageTypes.NORMAL, false, "Enter event id");
        String scheduleId = scanner.nextLine();
        Integer eventId = Integer.parseInt(scheduleId);
        printConsoleMessage(MessageTypes.NORMAL,false,"SELECT EMPLOYEE TO INTERVIEW");
        getShortlistedEmployees(eventId);
        printConsoleMessage(MessageTypes.NORMAL, false, "Enter employee's id");
        String userId = scanner.nextLine();
        Integer participantId = Integer.parseInt(userId);

        EventParticipation eventParticipation = new EventParticipation();
        eventParticipation.setScheduleId(eventId);
        eventParticipation.setParticipantId(participantId);

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/addingParticipant");
        requestBody.setAction("addParticipant");
        requestBody.setObject(eventParticipation);

        String requestString = new ObjectMapper().writeValueAsString(requestBody);
        // sending object to server
        ClientServerConnector clientServerConnector = new ClientServerConnector();
        String response = clientServerConnector.connectToServer(requestString);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(response);

        String message = jsonResponse.get("message").asText();
        printConsoleMessage(MessageTypes.SUCCESS, false, message);
    }
}