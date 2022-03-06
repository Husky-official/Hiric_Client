package views.hiring;

import clientconnector.ClientServerConnector;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.MessageTypes;
import models.RequestBody;
import models.hiring.Job;
import models.hiring.JobPosting;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Time;
import java.sql.Date;

import static utils.MessagePrinter.printConsoleMessage;
/*
 * author: Gashugi Aderline
 * desc: This is a controller that handles requests regarding creating, reading, updating and deleting a job post.
 *
 */
public class JobPostingView {
    public static void mainMethod() throws Exception {
        printConsoleMessage(MessageTypes.NORMAL, false, "\tJOB POSTING");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t-----------------------");
        printConsoleMessage(MessageTypes.NORMAL, false, "\t1.CREATE JOB POST");
        printConsoleMessage(MessageTypes.NORMAL, false, "\t2.VIEW JOB POST");
        printConsoleMessage(MessageTypes.NORMAL, false, "\t2.UPDATE JOB POST");
        printConsoleMessage(MessageTypes.NORMAL, false, "\t2.DELETE JOB POST");
        Scanner scanner = new Scanner(System.in);
        int choice;
        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t||-------------------------------------------------------------------||");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t\t\t  Enter your choice");
        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> createJobPost();
//            case 2 -> viewJobPosts();
            case 3 -> updateJobPost();
            case 4 -> deleteJobPost();
        }
    }

    public static void getJobs() throws Exception {
        printConsoleMessage(MessageTypes.NORMAL, false, "\tJOBS AVAILABLE");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t-----------------------");
        Job job = new Job();
        job.getJobTitle();
        job.getId();
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/jobPost");
        requestBody.setAction("getJobs");
        requestBody.setObject(job);

        String requestString = new ObjectMapper().writeValueAsString(requestBody);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        String response = clientServerConnector.connectToServer(requestString);
        System.out.println("Response from server: " + response);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(response);
        System.out.println("Json response" + jsonResponse);
        int status = jsonResponse.get("status").asInt();
        String message = jsonResponse.get("message").asText();
        String actionDone = jsonResponse.get("actionToDo").asText();

        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
        printConsoleMessage(MessageTypes.NORMAL, false,"STATUS ||         MESSAGE        ||             ACTION DON              ");
        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
        printConsoleMessage(MessageTypes.NORMAL, false,status+"    ||" + message +"   ||" + actionDone);
        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
    }
    public static ArrayList<JobPosting> getJobPosts() throws Exception {
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/short_listing?userId="+1);
        requestBody.setAction("get jobs");

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
        return null;
    }
    public static void createJobPost() throws Exception {

        Scanner scanner = new Scanner(System.in);
        printConsoleMessage(MessageTypes.NORMAL, false, "\tCREATE A JOB POST");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t-----------------------");
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter Job id");
        Integer jobId = scanner.nextInt();
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter Job Title");
        String jobTitle = scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter Job Description");
        String jobDesc = scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter Job Requirements");
        String jobRequirements = scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter Job location");
        String location = scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter the starting date");
        String date = scanner.nextLine();
        Date startDate = Date.valueOf(date);
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter the number of working hours");
        String time = scanner.nextLine();
        Time duration = Time.valueOf(time);
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter the salary");
        Integer salary = scanner.nextInt();
        JobPosting jobPosting = new JobPosting();
        jobPosting.setJobId(jobId);
        jobPosting.setJobTitle(jobTitle);
        jobPosting.setJobDesc(jobDesc);
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
    public static void updateJobPost() throws  Exception {
        Scanner scanner = new Scanner(System.in);
        printConsoleMessage(MessageTypes.NORMAL, false, "\tUPDATE A JOB POST");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t-----------------------");
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter Job Id");
        String jobId = scanner.nextLine();
        int id = Integer.parseInt(jobId);
    }

    public static void deleteJobPost() throws Exception {

    }
}
