package views.hiring;

import clientconnector.ClientServerConnector;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.MessageTypes;
import models.RequestBody;
import models.hiring.JobPosting;
import java.util.Scanner;

import static utils.MessagePrinter.printConsoleMessage;

public class JobPostingView {
    public void mainMethod() throws Exception {}

    public static void createJobPost() throws Exception {
        Scanner scanner = new Scanner(System.in);
        printConsoleMessage(MessageTypes.NORMAL, false, "\tCREATE A JOB POST");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t-----------------------");
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter Job Title");
        String jobTitle = scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter Job Description");
        String jobDescription = scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter Job Requirements");
        String jobRequirements = scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter Job location");
        String location = scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter the starting date");
        String startDate = scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter the number of working hours");
        String duration = scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter the salary");
        int salary = scanner.nextInt();
        JobPosting jobPosting = new JobPosting();
        jobPosting.setJobTitle(jobTitle);
        jobPosting.setJobDescription(jobDescription);
        jobPosting.setJobRequirements(jobRequirements);
        jobPosting.setStartDate(startDate);
        jobPosting.setDuration(duration);
        jobPosting.setLocation(location);
        jobPosting.setSalary(salary);
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/jobPost");
        requestBody.setAction("createJobPost");
        requestBody.setObject(jobPosting);

        String requestString = new ObjectMapper().writeValueAsString(requestBody);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        String response = clientServerConnector.connectToServer(requestString);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(response);

        int status = jsonResponse.get("status").asInt();
        String message = jsonResponse.get("message").asText();
        String actionDone = jsonResponse.get("actionToDo").asText();

        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
        printConsoleMessage(MessageTypes.NORMAL, false,"STATUS ||         MESSAGE        ||             ACTION DON              ");
        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
        printConsoleMessage(MessageTypes.NORMAL, false,status+"    ||" + message +"   ||" + actionDone);
        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
    }
}
