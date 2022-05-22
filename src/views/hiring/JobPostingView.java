package views.hiring;

import clientconnector.ClientServerConnector;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.MessageTypes;
import models.RequestBody;
import models.hiring.Job;
import models.hiring.JobPosting;

import java.util.Scanner;
import java.sql.Time;
import java.sql.Date;

import static utils.MessagePrinter.printConsoleMessage;
/*
 * author: Gashugi Aderline, MPANO Christian
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

    public static void viewJobs() throws Exception {
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
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(response);
        JsonNode jsonNode = objectMapper.readTree(String.valueOf(jsonResponse.get("object")));
        Job[] jobs = objectMapper.treeToValue(jsonNode, Job[].class);
        for(int i = 0; i<jobs.length; i++) {
            printConsoleMessage(MessageTypes.NORMAL, false, "\t" + jobs[i].id + "." + jobs[i].jobTitle);
        }
    }
    /*
     * @Author: MPANO Christian
     * */
    public static JobPosting[] getJobPosts() throws Exception {
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/get_job_posts?userId="+1);
        requestBody.setAction("get jobs");

        String requestString = new ObjectMapper().writeValueAsString(requestBody);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        String response = clientServerConnector.connectToServer(requestString);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode res = objectMapper.readTree(response);

        int status = res.get("status").asInt();
        String message = res.get("message").asText();
        String actionDone = res.get("actionToDo").asText();

        ObjectMapper objectMapper1 = new ObjectMapper();
        JsonNode jsonResponse = objectMapper1.readTree(response);
        JsonNode jsonNode = objectMapper1.readTree(String.valueOf(jsonResponse.get("object")));
        System.out.println(jsonNode);
        JobPosting[] jobPostings = objectMapper1.treeToValue(jsonNode, JobPosting[].class);
        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
        printConsoleMessage(MessageTypes.NORMAL, false,"STATUS ||         MESSAGE        ||             ACTION DON              ");
        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
        printConsoleMessage(MessageTypes.NORMAL, false,status+"    ||" + message +"   ||" + actionDone);
        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
        return jobPostings;
    }
    public static void createJobPost() throws Exception {

        Scanner scanner = new Scanner(System.in);
        printConsoleMessage(MessageTypes.NORMAL, false, "\tCREATE A JOB POST");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t-----------------------");
        viewJobs();
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter Job Id");
        String id = scanner.nextLine();
        Integer jobId = Integer.parseInt(id);
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter Job Description");
        String jobDesc = scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter Job Requirements");
        String jobRequirements = scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter Job location");
        String loc = scanner.nextLine();
        Integer location = Integer.parseInt(loc);
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter the starting date");
        String date = scanner.nextLine();
        Date startDate = Date.valueOf(date);
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter the starting time");
        String stime = scanner.nextLine();
        Time startTime = Time.valueOf(stime);
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter the number of working hours");
        String duration = scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter the salary");
        String sal = scanner.nextLine();
        Integer salary = Integer.parseInt(sal);
        printConsoleMessage(MessageTypes.NORMAL, false,"\tAre you going to pay per hour 1 || 0");
        String hourly = scanner.nextLine();
        String salaryType;
        if(hourly == "1") {
            salaryType = "dynamic";
        }else {
            salaryType = "static";
        }
        printConsoleMessage(MessageTypes.NORMAL, false,"\tHow many workers do you need?");
        String num = scanner.nextLine();
        Integer workers = Integer.parseInt(num);
        JobPosting jobPosting = new JobPosting();
        jobPosting.setJobId(jobId);
        jobPosting.setJobDesc(jobDesc);
        jobPosting.setJobRequirements(jobRequirements);
        jobPosting.setLocation(location);
        jobPosting.setStartDate(startDate);
        jobPosting.setStartTime(startTime);
        jobPosting.setDuration(duration);
        jobPosting.setSalary(salary);
        jobPosting.setSalaryType(salaryType);
        jobPosting.setWorkers(workers);
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
