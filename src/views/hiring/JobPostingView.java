package views.hiring;

import clientconnector.ClientServerConnector;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.MessageTypes;
import models.RequestBody;
import models.hiring.Job;
import models.hiring.JobPosting;
import models.hiring.Location;
import models.hiring.LocationLevel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static utils.MessagePrinter.printConsoleMessage;
/*
 * author: Gashugi Aderline, MPANO Christian
 * desc: This is a controller that handles requests regarding creating, reading, updating and deleting a job post.
 *+
 *
 */
public class  JobPostingView {
    public static void mainMethod() throws Exception {
        printConsoleMessage(MessageTypes.NORMAL, false, "\tJOB POSTING");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t-----------------------");
        printConsoleMessage(MessageTypes.NORMAL, false, "\t1.CREATE JOB POST");
        printConsoleMessage(MessageTypes.NORMAL, false, "\t2.VIEW JOB POST");
        printConsoleMessage(MessageTypes.NORMAL, false, "\t2.UPDATE JOB POST");
        printConsoleMessage(MessageTypes.NORMAL, false, "\t2.DELETE JOB POST");
        Scanner scanner = new Scanner(System.in);
        int choice;
        printConsoleMessage(MessageTypes.NORMAL, false,"\t||-------------------------------------------------------------------||");
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter your choice");
        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> createJobPost();
//            case 2 -> viewJobPosts();
            case 3 -> updateJobPost();
            case 4 -> deleteJobPost();
        }
    }

    public static void viewJobs() throws Exception {
        printConsoleMessage(MessageTypes.NORMAL, false, "\tJOBS AVAILABLE: ");
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
    public static void viewProvinces() throws Exception {
        printConsoleMessage(MessageTypes.NORMAL, false, "\tPROVINCES: ");
        Location location = new Location();
        location.getId();
        location.getLocation();
        location.getUpperLocation();
        location.getLevelId();
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/jobPost");
        requestBody.setAction("getProvinces");
        requestBody.setObject(location);

        String requestString = new ObjectMapper().writeValueAsString(requestBody);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        String response = clientServerConnector.connectToServer(requestString);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(response);
        JsonNode jsonNode = objectMapper.readTree(String.valueOf(jsonResponse.get("object")));
        Location[] locations = objectMapper.treeToValue(jsonNode, Location[].class);
        for(int i = 0; i<locations.length; i++) {
            printConsoleMessage(MessageTypes.NORMAL, false, "\t" + locations[i].id + "." + locations[i].location);
        }
    }
    public static void viewDistricts(Integer provinceId) throws Exception {
        printConsoleMessage(MessageTypes.NORMAL, false, "\tDISTRICTS: ");

        Location location = new Location();
        location.setUpperLocation(provinceId);
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/jobPost");
        requestBody.setAction("getDistricts");
        requestBody.setObject(location);

        String requestString = new ObjectMapper().writeValueAsString(requestBody);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        String response = clientServerConnector.connectToServer(requestString);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(response);
        JsonNode jsonNode = objectMapper.readTree(String.valueOf(jsonResponse.get("object")));
        Location[] locations = objectMapper.treeToValue(jsonNode, Location[].class);
        for(int i = 0; i<locations.length; i++) {
            printConsoleMessage(MessageTypes.NORMAL, false, "\t" + locations[i].id + "." + locations[i].location);
        }
    }
    public static void createJobPost() throws Exception {

        Scanner scanner = new Scanner(System.in);
        printConsoleMessage(MessageTypes.NORMAL, false, "\tCREATE A JOB POST");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t-----------------------");
        viewJobs();
        printConsoleMessage(MessageTypes.NORMAL, false,"\t-----------------------");
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter Job Id");
        String id = scanner.nextLine();
        Integer jobId = Integer.parseInt(id);
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter Job Description");
        String jobDesc = scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter Job Requirements");
        String jobRequirements = scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"\tRESIDENCE");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t-----------------------");
        viewProvinces();
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter Province ID");
        String provId = scanner.nextLine();
        Integer provinceId = Integer.parseInt(provId);
        viewDistricts(provinceId);
        String loc = scanner.nextLine();
        Integer location = Integer.parseInt(loc);
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter the starting date (Eg: 12 03 2022)");
        String date = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        LocalDate starDate = LocalDate.parse(date, formatter);
        java.sql.Date startDate = java.sql.Date.valueOf(starDate);
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter the starting time (Eg: 12 55 33)");
        String stime = scanner.nextLine();
        DateTimeFormatter formattter = DateTimeFormatter.ofPattern("HH mm ss");
        LocalTime starTime = LocalTime.parse(stime, formattter);
        java.sql.Time startTime = java.sql.Time.valueOf(starTime);
        System.out.println(startTime);
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter the number of working hours");
        String duration = scanner.nextLine();
        System.out.println(duration);
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter the salary");
        String sal = scanner.nextLine();
        Integer salary = Integer.parseInt(sal);
        printConsoleMessage(MessageTypes.NORMAL, false,"\tAre you going to pay per hour (1 || 0)");
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
        System.out.println(requestString);
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
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter JobPost Id");
        String id = scanner.nextLine();
        Integer jobPostId = Integer.parseInt(id);
        viewJobPostById(jobPostId);

    }
    public static void viewJobPostById(Integer jobPostId) throws Exception {
        printConsoleMessage(MessageTypes.NORMAL, false,"\t-----------------------");
        printConsoleMessage(MessageTypes.NORMAL, false, "\tDETAILS: ");
        JobPosting jobPosting = new JobPosting();
        jobPosting.setJobId(jobPostId);
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/jobPost");
        requestBody.setAction("getJobPostById");
        requestBody.setObject(jobPosting);
        String requestString = new ObjectMapper().writeValueAsString(requestBody);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        String response = clientServerConnector.connectToServer(requestString);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(response);
        JsonNode jsonNode = objectMapper.readTree(String.valueOf(jsonResponse.get("object")));
        JobPosting[] jobPosts = objectMapper.treeToValue(jsonNode, JobPosting[].class);
        for(int i = 0; i<jobPosts.length; i++) {
            printConsoleMessage(MessageTypes.NORMAL, false, "\t" + "jobDescription: " + jobPosts[i].jobDesc);
            printConsoleMessage(MessageTypes.NORMAL, false, "\t" + "jobRequirements: " + jobPosts[i].jobRequirements);
            printConsoleMessage(MessageTypes.NORMAL, false, "\t" + "location: " + jobPosts[i].location);
            printConsoleMessage(MessageTypes.NORMAL, false, "\t" + "startDate: " + jobPosts[i].startDate);
            printConsoleMessage(MessageTypes.NORMAL, false, "\t" + "startTime: " + jobPosts[i].startTime);
            printConsoleMessage(MessageTypes.NORMAL, false, "\t" + "Duration: " + jobPosts[i].duration);
            printConsoleMessage(MessageTypes.NORMAL, false, "\t" + "Salary: " + jobPosts[i].salary);
            printConsoleMessage(MessageTypes.NORMAL, false, "\t" + "SalaryType: " + jobPosts[i].salaryType);
            printConsoleMessage(MessageTypes.NORMAL, false, "\t" + "Workers: " + jobPosts[i].workers);
            printConsoleMessage(MessageTypes.NORMAL, false,"\t-----------------------");
        }
    }
    public static void deleteJobPost() throws Exception {
        Scanner scanner = new Scanner(System.in);
        printConsoleMessage(MessageTypes.NORMAL, false, "\tDELETE JOB POST");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t-----------------------");
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter Job Id");
        String id = scanner.nextLine();
        Integer jobId = Integer.parseInt(id);
        JobPosting jobPosting = new JobPosting();
        jobPosting.setJobId(jobId);
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/jobPost");
        requestBody.setAction("deleteJobPost");
        requestBody.setObject(jobPosting);
        String requestString = new ObjectMapper().writeValueAsString(requestBody);
        System.out.println(requestString);
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
