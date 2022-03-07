/*
* @author: ITETERO Ariane,MPANO Christian*/
package views.hiring;

import clientconnector.ClientServerConnector;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.MessageTypes;
import models.RequestBody;
import models.hiring.JobPosting;

import java.io.IOException;
import java.util.Scanner;
import static utils.MessagePrinter.printConsoleMessage;

public class jobApplicationView {
    public static void main(String[] args) throws IOException {
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
                viewPosts();
            case 2:
                applyForJob();
            case 3:
                viewApplications();
            case 4:
                updateApplication();
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

    private static void updateApplication() {
    }

    private static void viewApplications() {
    }

    public static JobPosting[] getApplicationsForJob(int jobId) throws Exception {
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/get_job_applications?postId="+1);
        requestBody.setAction("get job applications");

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
        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
        printConsoleMessage(MessageTypes.NORMAL, false,"STATUS ||         MESSAGE        ||             ACTION DON              ");
        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
        printConsoleMessage(MessageTypes.NORMAL, false,status+"    ||" + message +"   ||" + actionDone);
        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
        return jobPostings;
    }
    private static void viewPosts() {
    }

    public static void applyForJob() throws IOException{
        Scanner scanner=new Scanner(System.in);
        printConsoleMessage(MessageTypes.ACTION, false,"\t\tENTER DETAILS OF EMPLOYMENT DESIRED\n");
        printConsoleMessage(MessageTypes.NORMAL, false,"Current address: ");
        String currentAddress=scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"Position: ");
        String position=scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"Available date: ");
        String availableDate=scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"Salary desired: ");
        String salary=scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"Payment method: ");
        String paymentMethod=scanner.nextLine();
        printConsoleMessage(MessageTypes.ACTION, false,"\n\t\tENTER DETAILS OF PREVIOUS EMPLOYMENT\n");
        printConsoleMessage(MessageTypes.NORMAL, false,"Previous employer: ");
        String prevEmp=scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"Previous employer phone Number or email: ");
        String prevEmpPhone=scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"Reason for leaving: ");
        String reason=scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"Previous salary: ");
        String prevSalary=scanner.nextLine();
        printConsoleMessage(MessageTypes.ACTION, false,"\n\t\tENTER DETAILS OF REFERENCES\n");
        printConsoleMessage(MessageTypes.NORMAL, false,"Full names: ");
        String prevNames=scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"Contact ");
        String contact=scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"\n\n Attach the resume ");
        String resume=scanner.nextLine();
    }
}
