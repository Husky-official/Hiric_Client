/*
 * @Author: MPANO Christian
 * */
/*
 * @Author: MPANO Christian
 * */
package views.hiring;

import clientconnector.ClientServerConnector;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.MessageTypes;
import models.RequestBody;
import models.hiring.Job;
import models.hiring.JobApplication;
import models.hiring.JobPosting;
import models.hiring.ShortListApplication;

import java.util.ArrayList;
import java.util.Scanner;
import static utils.MessagePrinter.printConsoleMessage;

public class ShortListingView {
    public static void mainMethod() {
        try{
            System.out.println("SELECT THE JOB POST YOU WANT TO SHOT LIST FOR");
            JobPosting[] jobPostings;
            jobPostings = JobPostingView.getJobPosts();
            if(jobPostings == null) {
                printConsoleMessage(MessageTypes.ERROR, false, "no job posts available");
                return;
            }
            for(int i = 0; i < jobPostings.length; i++) {
                System.out.println(i + 1 + " : " + jobPostings[i].jobDesc);
            }
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            int jobPostId = 0;
            for(int i = 0; i < jobPostings.length; i++) {
                if(i + 1 == choice) {
                    jobPostId = jobPostings[i].id;
                }
            }
            if(jobPostId == 0) {
                printConsoleMessage(MessageTypes.ERROR, false, "invalid job selected");
                return;
            }
            JobApplication[] jobApplications = JobApplicationView.getApplicationsForJob(jobPostId);

            if(jobApplications == null) {
                printConsoleMessage(MessageTypes.ERROR, false, "no job applications available yet");
                return;
            }

            String leftAlignFormat = "| %-4d | %-13s | %-13s | %-23s | %18s | %17s |%n";
            System.out.format(" Number| FirstName     | LastName      | Email                   | Payment method     | referenceName     |%n");
            System.out.println("+---------------------------------------------------------------------------------------------------------+");
            for (int i = 0; i < jobApplications.length; i++) {
                System.out.format(leftAlignFormat, i + 1,jobApplications[i].firstName, jobApplications[i].lastName, jobApplications[i].email, jobApplications[i].paymentMethod, jobApplications[i].referenceName);
            }
            System.out.format("+-----------------+-------------+%n");
            System.out.println("How many job applications do you want to shortlist?");
            int shortLists = scanner.nextInt();
            if(shortLists > jobApplications.length) {
                System.out.println("choosing more applications to shortlist than the applications you have");
                return;
            }
            System.out.println("Please enter numbers of people you want to shortlist");
            ArrayList<Integer> shortListedNumbers = new ArrayList<Integer>();
            ArrayList<Integer> shortListedApplications = new ArrayList<Integer>();
            for(int i = 0; i < shortLists; i++) {
                if(i+1 == 1) {
                    System.out.print(i+1 + "st: ");
                }else if(i + 1 == 2) {
                    System.out.print(i+1 + "nd: ");
                }else if(i + 1 == 3) {
                    System.out.print(i+1 + "rd: ");
                }else{
                    System.out.print(i+1 + "th: ");
                }
                shortListedNumbers.add(scanner.nextInt());
            }
            ArrayList<JobApplication> shortList = new ArrayList<JobApplication>();
            ArrayList<Integer> shortListUserIds = new ArrayList<Integer>();
            for(int i = 0; i < shortListedNumbers.size(); i++){
                shortList.add(jobApplications[shortListedNumbers.get(i)-1]);
                shortListUserIds.add(jobApplications[shortListedNumbers.get(i)-1].userId);
            }
            addToShortList(shortListUserIds);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void addToShortList(ArrayList<Integer> shortList) throws Exception {
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/shortList");
        requestBody.setAction("add to shortlist");
        requestBody.setObject(shortList);

        String requestString = new ObjectMapper().writeValueAsString(requestBody);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        String response = clientServerConnector.connectToServer(requestString);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(response);
        int updated = jsonResponse.get("actionToDo").asInt();
        if(updated>=0) {
            printConsoleMessage(MessageTypes.SUCCESS,false,"Shortlisting went successful!");
        }
    }
    public static ShortListApplication[] getShortListForJob(int postId) throws Exception {
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/getShortList?postId="+postId);
        requestBody.setAction("get shortlist");

        String requestString = new ObjectMapper().writeValueAsString(requestBody);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        String response = clientServerConnector.connectToServer(requestString);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(response);
        JsonNode jsonNode = objectMapper.readTree(String.valueOf(jsonResponse.get("object")));
        int status = jsonResponse.get("status").asInt();
        String message = jsonResponse.get("message").asText();
        String actionDone = jsonResponse.get("actionToDo").asText();
        System.out.println(jsonNode);
        ShortListApplication[] shortList = objectMapper.treeToValue(jsonNode, ShortListApplication[].class);
        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
        printConsoleMessage(MessageTypes.NORMAL, false,"STATUS ||         MESSAGE        ||             ACTION DON              ");
        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
        printConsoleMessage(MessageTypes.NORMAL, false,status+"    ||" + message +"   ||" + actionDone);
        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
        return shortList;
    }
}