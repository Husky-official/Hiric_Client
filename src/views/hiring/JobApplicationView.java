package views.hiring;

import clientconnector.ClientServerConnector;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.MessageTypes;
import models.ResponseBody;
import models.hiring.Job;
import models.hiring.JobApplication;
import models.RequestBody;
import models.hiring.JobApply;
import models.hiring.JobPosting;

import javax.xml.stream.Location;
import java.io.IOException;
import java.util.Scanner;
import static utils.MessagePrinter.printConsoleMessage;
/*
This is job application view
@author Ariane Itetero
 */
public class JobApplicationView {
    public static void main() throws Exception {
        printConsoleMessage(MessageTypes.ACTION, false,"====================================");
        printConsoleMessage(MessageTypes.ACTION, false,"\t\t\tJOB APPLICATION MENU         \n");
        printConsoleMessage(MessageTypes.ACTION, false,"------------------------------------");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t1.VIEW ALL JOBS POSTED            \n");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t2.APPLY FOR A JOB   \n");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t3.VIEW YOUR APPLICATIONS          \n");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t4.UPDATE APPLICATION           \n");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t5.DELETE APPLICATION             \n");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t\t6.RETURN HOME            \n");
        printConsoleMessage(MessageTypes.NORMAL, false,"====================================");
        printConsoleMessage(MessageTypes.NORMAL, false,"\n\n\t\tEnter your choice: ");
        Scanner input=new Scanner(System.in);
        Integer choice=input.nextInt();
        switch(choice){
            case 1:
                getJobPosts();
            case 2:
                applyForJob();
//            case 3:
//                getApplications();
//            case 4:
//                updateApplication();
            case 5:
                deleteApplication();
            case 6:
                goToHome();
        }
    }

    private static void goToHome() {
    }

    private static void deleteApplication() {
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

    public static void applyForJob() throws Exception {
        Scanner scanner=new Scanner(System.in);
        printConsoleMessage(MessageTypes.ACTION, false,"\t\tENTER DETAILS OF EMPLOYMENT DESIRED\n");
       // getJobPosts();
        printConsoleMessage(MessageTypes.NORMAL, false,"Job Post Id: ");
        String jobIdd=scanner.nextLine();
        int jobId=Integer.parseInt(jobIdd);
        printConsoleMessage(MessageTypes.NORMAL, false,"User Id: ");
        String userId=scanner.nextLine();
        int userrId=Integer.parseInt(userId);
        printConsoleMessage(MessageTypes.NORMAL, false,"Location id: ");
        String locid=scanner.nextLine();
        int locId=Integer.parseInt(locid);
        printConsoleMessage(MessageTypes.NORMAL, false,"Payment method: ");
        String paymentMethod=scanner.nextLine();
        printConsoleMessage(MessageTypes.ACTION, false,"\n\t\tENTER DETAILS OF REFERENCES\n");
        printConsoleMessage(MessageTypes.NORMAL, false,"Full names: ");
        String refNames=scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"Contact ");
        String contact=scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"\n\n Attach the certificate ");
        String certificate=scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"\n\n Attach the resume ");
        String resume=scanner.nextLine();

        JobApply apply = new JobApply();
        apply.setJobPostId(jobId);
        apply.setUserId(userrId);
        apply.setLocationId(locId);
        apply.setPaymentMethod(paymentMethod);
        apply.setReferenceName(refNames);
        apply.setReferencePhone(contact);
        apply.setCertificate(certificate);
        apply.setResume(resume);

        RequestBody requestBody2= new RequestBody();
        requestBody2.setUrl("/createApplication");
        requestBody2.setAction("createApplication");
        requestBody2.setObject(apply);

        String requestString = new ObjectMapper().writeValueAsString(requestBody2);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        String response = clientServerConnector.connectToServer(requestString);

        System.out.println("Response : " +response);

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