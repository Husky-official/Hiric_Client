package views.hiring;

import clientconnector.ClientServerConnector;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.MessageTypes;
import models.ResponseBody;
import models.hiring.JobApplication;
import models.RequestBody;
import models.hiring.JobPosting;

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
//                getApplicationsForJob(jobPostId);
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

    private static void updateApplication() throws IOException {
        printConsoleMessage(MessageTypes.ACTION,false,"UPDATE THE APPLICATION\n\n\n");
        printConsoleMessage(MessageTypes.ACTION,false,"Enter the id of the application to be updated");
        Scanner scan=new Scanner(System.in);
        String id=scan.nextLine();
        int appId=Integer.parseInt(id);
        RequestBody requestBody=new RequestBody();
        requestBody.setUrl("/updateApplication");
        requestBody.setAction("updateApplication");
        String requestString=new ObjectMapper().writeValueAsString(requestBody);
        ClientServerConnector server=new ClientServerConnector();
        String response=server.connectToServer(requestString);
    }

    public static JobApplication[] getApplicationsForJob(int jobPostId) throws Exception {
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
        System.out.println(jsonNode);
        JobApplication[] jobApplications = objectMapper1.treeToValue(jsonNode, JobApplication[].class);
        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
        printConsoleMessage(MessageTypes.NORMAL, false,"STATUS ||         MESSAGE        ||             ACTION DON              ");
        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
        printConsoleMessage(MessageTypes.NORMAL, false,status+"    ||" + message +"   ||" + actionDone);
        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
        return jobApplications;
    }
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
        JobPosting[] jobPostings = objectMapper1.treeToValue(jsonNode, JobPosting[].class);

        System.out.println(jsonNode);

        System.out.println("\t\t\t\t LIST OF JOBS POSTS\t");
        System.out.format("+-------+-----------------+-----------------+---------------------------+---------------------------+--------------++%n");
       String leftAlignFormat = "| %-9s | %-10s | %-13s | %-23s | %18s | %17s |%n";
        System.out.format(" | Job Id    |  jobRequirements      | location       | start date     | Duration           | Salary |%n");
        System.out.println("+---------------------------------------------------------------------------------------------------------+");
        for (int i = 0; i < jobPostings.length; i++) {
            System.out.format(leftAlignFormat,jobPostings[i].getJobId(),
                    jobPostings[i].getJobRequirements(), jobPostings[i].getLocation(),jobPostings[i].getStartDate(),
                    jobPostings[i].getDuration(),jobPostings[i].getSalary());
        }

        ResponseBody responseBody;

        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
        printConsoleMessage(MessageTypes.NORMAL, false,"STATUS ||         MESSAGE        ||             ACTION DON              ");
        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
        printConsoleMessage(MessageTypes.NORMAL, false,status+"    ||" + message +"   ||" + actionDone);
        printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
        return jobPostings;
    }

    public static void applyForJob() throws IOException{
        Scanner scanner=new Scanner(System.in);
        printConsoleMessage(MessageTypes.ACTION, false,"\t\tENTER DETAILS OF EMPLOYMENT DESIRED\n");
        printConsoleMessage(MessageTypes.NORMAL, false,"Job Application Id: ");
        String jobAppId=scanner.nextLine();
        int appId=Integer.parseInt(jobAppId);
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
        printConsoleMessage(MessageTypes.NORMAL, false,"\n\n Attach the certificate ");
        String certificate=scanner.nextLine();
        printConsoleMessage(MessageTypes.NORMAL, false,"\n\n Attach the resume ");
        String resume=scanner.nextLine();
        JobApplication apply = new JobApplication();
        apply.setId(appId);
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
