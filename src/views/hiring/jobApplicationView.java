package views.hiring;

import clientconnector.ClientServerConnector;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.MessageTypes;
import models.hiring.JobApplication;
import models.hiring.JobPosting;
import models.RequestBody;

import java.io.IOException;
import java.util.Scanner;
import static utils.MessagePrinter.printConsoleMessage;
/*
This is job application view
@author Ariane Itetero
 */
public class jobApplicationView {
    public static void main() throws IOException {
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

    private static void viewApplications() throws IOException {
        printConsoleMessage(MessageTypes.ACTION,false,"\t\t\t\t LIST OF JOB POSTS\t");
//           System.out.format("+-------+-----------------+-----------------+---------------------------+---------------------------+--------------+-----------------+---------------------------+-----------------+%n");
//           printConsoleMessage(MessageTypes.ACTION,false,String.format("| %5s | %-15s | %-15s | %-25s | %-25s | %-12s | %-15s | %-25s | %-15s |","#Id ","User id", "Job post id","location Id","","Gender","User category","Birth date","User Location"));
//           System.out.format("+-------+-----------------+-----------------+---------------------------+---------------------------+--------------+-----------------+---------------------------+-----------------+%n");
//
                       //String.format("| %5s | %-15s | %-15s | %-25s | %-25s | %-12s | %-15s | %-25s | %-15s |",


        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/viewApplications");
        requestBody.setAction("viewApplications");

        String requestString = new ObjectMapper().writeValueAsString(requestBody);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        String response = clientServerConnector.connectToServer(requestString);

        //System.out.println("Response : " +response);

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
    private static void viewPosts() {
    }

    public static void applyForJob() throws IOException{
        Scanner scanner=new Scanner(System.in);
        printConsoleMessage(MessageTypes.ACTION, false,"\t\tENTER DETAILS OF EMPLOYMENT DESIRED\n");
        printConsoleMessage(MessageTypes.NORMAL, false,"Job Post Id: ");
        String jobIdd=scanner.nextLine();
        int jobId=Integer.parseInt(jobIdd);
        printConsoleMessage(MessageTypes.NORMAL, false,"User Id: ");
        String userId=scanner.nextLine();
        int userrId=Integer.parseInt(userId);
        printConsoleMessage(MessageTypes.NORMAL, false,"Location name: ");
        String locid=scanner.nextLine();
        int locId=Integer.parseInt(locid);
        printConsoleMessage(MessageTypes.NORMAL, false,"Payment method: ");
        String paymentMethod=scanner.nextLine();

        printConsoleMessage(MessageTypes.ACTION, false,"\n\t\tENTER DETAILS OF REFERENCES\n");
        printConsoleMessage(MessageTypes.NORMAL, false,"Full names: ");
        String refNames=scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"Contact ");
        String contact=scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"\n\n Attach the resume ");
        String resume=scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"\n\n Attach the certificate ");
        String certificate=scanner.nextLine();

        JobApplication apply = new JobApplication();
         apply.setJobPostId(jobId);
         apply.setUserId(userrId);
         apply.setLocationId(locId);
         apply.setPaymentMethod(paymentMethod);
         apply.setReferenceName(refNames);
         apply.setReferencePhone(contact);
         apply.setCertificate(certificate);
         apply.setResume(resume);

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/createApplication");
        requestBody.setAction("createApplication");
        requestBody.setObject(apply);

        String requestString = new ObjectMapper().writeValueAsString(requestBody);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        String response = clientServerConnector.connectToServer(requestString);

        //System.out.println("Response : " +response);

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
