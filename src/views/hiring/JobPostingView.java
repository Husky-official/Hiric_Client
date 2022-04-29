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

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import utils.*;

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
        printConsoleMessage(MessageTypes.NORMAL, false, "\t1.VIEW JOB POSTS");
        printConsoleMessage(MessageTypes.NORMAL, false, "\t2.CREATE JOB POST");
        printConsoleMessage(MessageTypes.NORMAL, false, "\t3.UPDATE JOB POST");
        printConsoleMessage(MessageTypes.NORMAL, false, "\t4.DELETE JOB POST");
        Scanner scanner = new Scanner(System.in);
        int choice;
        printConsoleMessage(MessageTypes.NORMAL, false,"\t||-------------------------------------------------------------------||");
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter your choice");
        choice = scanner.nextInt();
        switch (choice) {
            case 1 -> viewJobPosts();
            case 2 -> createJobPost();
            case 3 -> updateJobPost();
            case 4 -> deleteJobPost();
        }
    }
    public static Job[] getJobs() throws Exception {
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

        return jobs;
    }
    public static Location[] getLocations() throws Exception {
        Location location = new Location();
        location.getId();
        location.getLevelId();
        location.getUpperLocation();
        location.getLocation();
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/jobPost");
        requestBody.setAction("getLocations");
        requestBody.setObject(location);

        String requestString = new ObjectMapper().writeValueAsString(requestBody);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        String response = clientServerConnector.connectToServer(requestString);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(response);
        JsonNode jsonNode = objectMapper.readTree(String.valueOf(jsonResponse.get("object")));
        Location[] locations = objectMapper.treeToValue(jsonNode, Location[].class);

        return locations;
    }
    public static void viewJobs() throws Exception {
        printConsoleMessage(MessageTypes.NORMAL, false, "\tJOBS AVAILABLE: ");

        Job[] jobs = getJobs();
//        new Loader(20, "\tLoading");
//        MessagePrinter.skipLines(1);
        for(int i = 0; i<jobs.length; i++) {
            printConsoleMessage(MessageTypes.NORMAL, false, "\t" + jobs[i].id + "." + jobs[i].jobTitle);
        }
    }
    public static void viewJobPosts() throws Exception {
        printConsoleMessage(MessageTypes.NORMAL, false,"\t-----------------------");
        printConsoleMessage(MessageTypes.NORMAL, false, "\tJOB POSTS: ");

//        new Loader(17, "\tLoading");
//        MessagePrinter.skipLines(1);
        printConsoleMessage(MessageTypes.NORMAL, false, "======================================================================================================================================================================================================================================================");
        printConsoleMessage(MessageTypes.NORMAL, false, "ID ||          JobTitle          ||          JobDescription          ||       JobRequirements       ||       Location            ||       StartDate     ||     StartTime     ||   Duration   ||     Salary     ||     SalaryType     ||   Workers   ||");
        printConsoleMessage(MessageTypes.NORMAL, false, "======================================================================================================================================================================================================================================================");
        JobPosting[] jobPosts = getJobPosts();
        Job[] jobs = getJobs();
        Location[] locations = getLocations();
        String title, location;
        for(int j = 0; j< jobs.length; j++) {
            for (int i = 0; i < jobPosts.length; i++) {
                for(int k = 0; k<locations.length; k++) {
                if((jobPosts[i].jobId == jobs[j].id) && (jobPosts[i].location == locations[k].id)) {
                    title = jobs[j].jobTitle;
                    location = locations[k].location;
                    printConsoleMessage(MessageTypes.NORMAL, false, i+1 + "  ||"+ title + "                     ||" + jobPosts[i].jobDesc + "          ||" + jobPosts[i].jobRequirements + "             ||" + location + "                  ||" + jobPosts[i].startDate + "           ||" + jobPosts[i].startTime + "           ||" + jobPosts[i].duration + "           ||" + jobPosts[i].salary + "           ||" + jobPosts[i].salaryType + "            ||" + jobPosts[i].workers + "            ||") ;
                    printConsoleMessage(MessageTypes.NORMAL, false, "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                }
                }
            }
        }
    }
    public static JobPosting[] getJobPosts() throws Exception {
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/jobPost");
        requestBody.setAction("getJobPosts");

        String requestString = new ObjectMapper().writeValueAsString(requestBody);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        String response = clientServerConnector.connectToServer(requestString);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode res = objectMapper.readTree(response);

        ObjectMapper objectMapper1 = new ObjectMapper();
        JsonNode jsonResponse = objectMapper1.readTree(response);
        JsonNode jsonNode = objectMapper1.readTree(String.valueOf(jsonResponse.get("object")));
        JobPosting[] jobPosts = objectMapper1.treeToValue(jsonNode, JobPosting[].class);

        return jobPosts;
    }
    /*
    * @Author: MPANO Christian
    * */
    public static JobPosting[] getJobPostsById() throws Exception {
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
        JobPosting[] jobPostings = objectMapper1.treeToValue(jsonNode, JobPosting[].class);
//        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
//        printConsoleMessage(MessageTypes.NORMAL, false,"STATUS ||         MESSAGE        ||             ACTION DON              ");
//        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
//        printConsoleMessage(MessageTypes.NORMAL, false,status+"    ||" + message +"   ||" + actionDone);
//        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
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

    public static void viewNextLocation(Integer nextLocation, String title) throws Exception {
        printConsoleMessage(MessageTypes.NORMAL, false, "\t" + title);

        Location location = new Location();
        location.setUpperLocation(nextLocation);
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/jobPost");
        requestBody.setAction("getNextLocation");
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
        printConsoleMessage(MessageTypes.NORMAL, false,"\t-----------------------");
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter Province ID");
        String provId = scanner.nextLine();
        Integer provinceId = Integer.parseInt(provId);
        printConsoleMessage(MessageTypes.NORMAL, false,"\t-----------------------");
        viewNextLocation(provinceId, "DISTRICTS:");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t-----------------------");
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter District ID");
        String distrId = scanner.nextLine();
        Integer districtId = Integer.parseInt(distrId);
        printConsoleMessage(MessageTypes.NORMAL, false,"\t-----------------------");
        viewNextLocation(districtId, "SECTORS");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t-----------------------");
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter Sector ID");
        String sectId = scanner.nextLine();
        Integer sectorId = Integer.parseInt(sectId);
        printConsoleMessage(MessageTypes.NORMAL, false,"\t-----------------------");
        viewNextLocation(sectorId, "CELLS");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t-----------------------");
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter Cell ID");
        String celId = scanner.nextLine();
        Integer cellId = Integer.parseInt(celId);
        printConsoleMessage(MessageTypes.NORMAL, false,"\t-----------------------");
        viewNextLocation(cellId, "VILLAGES:");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t-----------------------");
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter Village ID");
        String loc = scanner.nextLine();
        Integer location = Integer.parseInt(loc);
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter the starting date (dd mm yyyy)");
        String date = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        LocalDate starDate = LocalDate.parse(date, formatter);
        java.sql.Date startDate = java.sql.Date.valueOf(starDate);
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter the starting time (hh mm ss)");
        String stime = scanner.nextLine();
        DateTimeFormatter formattter = DateTimeFormatter.ofPattern("HH mm ss");
        LocalTime starTime = LocalTime.parse(stime, formattter);
        java.sql.Time startTime = java.sql.Time.valueOf(starTime);
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter the number of working hours");
        String duration = scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter the salary");
        String sal = scanner.nextLine();
        Integer salary = Integer.parseInt(sal);
        printConsoleMessage(MessageTypes.NORMAL, false,"\tAre you going to pay per hour (1 || 0)");
        String hourly = scanner.nextLine();
        String salaryType;
        if(hourly == "1") {
            salaryType = "DYNAMIC";
        }else {
            salaryType = "STATIC";
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
        viewJobPosts();
        printConsoleMessage(MessageTypes.NORMAL, false,"\tWhich jobPost do you want to update?(ID)");
        String id = scanner.nextLine();
        Integer jobPostId = Integer.parseInt(id);
        printConsoleMessage(MessageTypes.NORMAL, false,"\t Which field do you want to update?");

        printConsoleMessage(MessageTypes.NORMAL, true, "1.JOB TYPE");
        printConsoleMessage(MessageTypes.NORMAL, true, "\t2.JOB DESCRIPTION");
        printConsoleMessage(MessageTypes.NORMAL, true, "\t3.JOB REQUIREMENTS");
        printConsoleMessage(MessageTypes.NORMAL, true, "\t4.LOCATION");
        printConsoleMessage(MessageTypes.NORMAL, true, "\t5.START DATE");
        printConsoleMessage(MessageTypes.NORMAL, true, "\t6.START TIME");
        printConsoleMessage(MessageTypes.NORMAL, true, "\t7.DURATION");
        printConsoleMessage(MessageTypes.NORMAL, true, "\t8.SALARY");
        printConsoleMessage(MessageTypes.NORMAL, true, "\t9.SALARY TYPE");
        printConsoleMessage(MessageTypes.NORMAL, false, "\t10.NUMBER OF WORKERS");


        String input = scanner.nextLine();
        Integer choice = Integer.parseInt(input);


        String jobDesc, jobRequirements, duration, salaryType;
        Integer jobId, salary, workers;
        Date startDate;
        Time startTime;
        JobPosting jobPost = new JobPosting();
        switch (choice) {
            case 1 -> {
                viewJobs();
                printConsoleMessage(MessageTypes.NORMAL, false,"\t What do you want the job type to be?");
                String jobIdd = scanner.nextLine();
                jobId = Integer.parseInt(jobIdd);
                jobPost.setJobId(jobId);
            }
            case 2 -> {
                printConsoleMessage(MessageTypes.NORMAL, false,"\t What do you want the job description to be?");
                jobDesc = scanner.nextLine();
                jobPost.setJobDesc(jobDesc);
            }
            case 3 -> {
                printConsoleMessage(MessageTypes.NORMAL, false,"\t What do you want the job requirements to be?");
                jobRequirements = scanner.nextLine();
                jobPost.setJobRequirements(jobRequirements);
            }
            case 4 -> {
                printConsoleMessage(MessageTypes.NORMAL, false,"\t What do you want the job location to be?");
                viewProvinces();
                printConsoleMessage(MessageTypes.NORMAL, false,"\t Province:");
                String prov = scanner.nextLine();
                Integer province = Integer.parseInt(prov);
                viewNextLocation(province, "PROVINCES");
                printConsoleMessage(MessageTypes.NORMAL, false,"\t District:");
                String distr = scanner.nextLine();
                Integer district = Integer.parseInt(distr);
                viewNextLocation(district, "SECTORS");
                printConsoleMessage(MessageTypes.NORMAL, false,"\t Sector:");
                String sect = scanner.nextLine();
                Integer sector = Integer.parseInt(sect);
                viewNextLocation(sector, "CELLS");
                printConsoleMessage(MessageTypes.NORMAL, false,"\t Cell:");
                String cel = scanner.nextLine();
                Integer cell = Integer.parseInt(cel);
                viewNextLocation(cell, "VILLAGES");
                printConsoleMessage(MessageTypes.NORMAL, false,"\t Village:");
                String vill = scanner.nextLine();
                Integer village = Integer.parseInt(vill);
                jobPost.setLocation(village);
            }
            case 5 -> {
                printConsoleMessage(MessageTypes.NORMAL, false,"\t What do you want the start date to be?");
                String date = scanner.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
                LocalDate starDate = LocalDate.parse(date, formatter);
                startDate = java.sql.Date.valueOf(starDate);
                jobPost.setStartDate(startDate);
            }
            case 6 -> {
                printConsoleMessage(MessageTypes.NORMAL, false,"\t What do you want the job start time to be?");
                String stime = scanner.nextLine();
                DateTimeFormatter formattter = DateTimeFormatter.ofPattern("HH mm ss");
                LocalTime starTime = LocalTime.parse(stime, formattter);
                startTime = java.sql.Time.valueOf(starTime);
                jobPost.setStartTime(startTime);
            }
            case 7 -> {
                printConsoleMessage(MessageTypes.NORMAL, false,"\t What do you want the duration to be?");
                duration = scanner.nextLine();
                jobPost.setDuration(duration);
            }
            case 8 -> {
                printConsoleMessage(MessageTypes.NORMAL, false,"\t What do you want the salary to be?");
                String sal = scanner.nextLine();
                salary = Integer.parseInt(sal);
                jobPost.setSalary(salary);
            }
            case 9 -> {
                printConsoleMessage(MessageTypes.NORMAL, false,"\t What do you want the salary type to be?(STATIC || DYNAMIC)");
                salaryType = scanner.nextLine();
                jobPost.setSalaryType(salaryType);
            }
            case 10 -> {
                printConsoleMessage(MessageTypes.NORMAL, false,"\t What do you want the number of workers to be?");
                String work = scanner.nextLine();
                workers = Integer.parseInt(work);
                jobPost.setWorkers(workers);
            }
        }
        jobPost.setId(jobPostId);
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/jobPost");
        requestBody.setAction("updateJobPost");
        requestBody.setObject(jobPost);
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
        new Loader(17, "\t");
        for(int i = 0; i<jobPosts.length; i++) {
            printConsoleMessage(MessageTypes.NORMAL, false, "\t" + "\n \tjobDescription: " + jobPosts[i].jobDesc);
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
        viewJobPosts();
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter jobPost Id");
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
