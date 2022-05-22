package views.hiring;

import clientconnector.ClientServerConnector;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.MessageTypes;
import models.RequestBody;
import models.hiring.JobApplication;
import models.hiring.JobPosting;
import models.hiring.ShortListApplication;

import java.util.ArrayList;
import java.util.Scanner;

import static utils.MessagePrinter.printConsoleMessage;

public class ConfirmationAndCancellingView {
    public static void mainMethod() {
        try{
            System.out.println("PLEASE SELECT JOB YOU WANT TO PERFORM CONFIRMATION AND CANCELLING");
            JobPosting[] jobPostings;
            jobPostings = JobPostingView.getJobPosts();
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
                printConsoleMessage(MessageTypes.ERROR, false, "Invalid job selected");
                return;
            }

            ShortListApplication[] shortList = ShortListingView.getShortListForJob(jobPostId);

            System.out.println("FOR EACH SHORTLISTED APPLICATION, ENTER 1 TO CONFIRM AND 0 TO CANCEL");

            if(shortList == null) {
                printConsoleMessage(MessageTypes.ERROR, false, "shortlist is empty");
                return;
            }

            ArrayList<Integer> applicationIds = new ArrayList<Integer>();

            String leftAlignFormat = "| %-4d | %-13s | %-13s | %-23s |%n";
            for (int i = 0; i < shortList.length; i++) {
                System.out.format(" Number| FirstName     | LastName      | Email                   |%n");
                System.out.println("+----------------------------------------------------------------+");
                System.out.format(leftAlignFormat, i+1,shortList[i].getFirstName(), shortList[i].getLastName(), shortList[i].getEmail());
                System.out.println("Press 1 to confirm and 0 to cancel: ");
                if (scanner.nextInt() == 1) {
                    applicationIds.add(shortList[i].getApplicationId());
                }
            }
            System.out.format("+-----------------+-------------+%n");

            confirmationAndCancelling(applicationIds);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void confirmationAndCancelling(ArrayList<Integer> confirmedArray) throws Exception {
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/confirmationAndCancelling");
        requestBody.setAction("confirmation and cancelling");
        requestBody.setObject(confirmedArray);

        String requestString = new ObjectMapper().writeValueAsString(requestBody);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        String response = clientServerConnector.connectToServer(requestString);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(response);
        int updated = jsonResponse.get("actionToDo").asInt();
        if(updated>=0) {
            printConsoleMessage(MessageTypes.SUCCESS,false,"confirmation and cancelling went successful!");
        }
    }
}
