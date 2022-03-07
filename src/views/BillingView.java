package views;

import clientconnector.ClientServerConnector;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.MessageTypes;
import models.Payment;
import models.RequestBody;
import models.User;
import models.hiring.JobPosting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static utils.MessagePrinter.printConsoleMessage;

public class BillingView {
    public Double jobSalary;
    public Long empId;

    public Integer paymentMethod(Long jobId) throws Exception {
        Integer returnString = 1;
        Scanner scan = new Scanner(System.in);
//        User payer = new User("hfdjshfsadjf","aldo@aldo.com");
//        User payee = new User("jfdksjfksf","jabes@jabes.com");
        Long parsedJobId = jobId;
//        PayObject payObject = new PayObject(payee,payer,money_to_pay);
        printConsoleMessage(MessageTypes.NORMAL, false, "||  You must pay for job with id "+parsedJobId+"||");
        printConsoleMessage(MessageTypes.NORMAL,false,"choose your payment method");
        printConsoleMessage(MessageTypes.NORMAL,false,"1.Pay using MoMo ");
        printConsoleMessage(MessageTypes.NORMAL,false,"2.Pay Using paypal");
        printConsoleMessage(MessageTypes.NORMAL,false,"choose: ");
        int choice = scan.nextInt();
        if (choice == 1){

            printConsoleMessage(MessageTypes.NORMAL,false,"Enter your number: ");
            scan.nextLine();
            String phone_number = scan.nextLine();
            printConsoleMessage(MessageTypes.NORMAL,false,"Enter your PIN: ");
            String PIN = scan.nextLine();
            //due to it being a console version we can't use API since they require a web interface
            returnString = 1;
        }else if(choice == 2){
            printConsoleMessage(MessageTypes.NORMAL,false,"Enter your email: ");
            scan.nextLine();
            String paypal_email = scan.nextLine();
            printConsoleMessage(MessageTypes.NORMAL,false,"Enter your password: ");
            String paypal_password = scan.nextLine();
            //due to it being a console version we can't use API since they require a web interface
            returnString = 2;
        }else {
            printConsoleMessage(MessageTypes.NORMAL, false, "Invalid input !");
            TimeUnit.SECONDS.sleep(3);
            paymentMethod(jobId);
        }
        return returnString;
    }

    public void makePayment() throws Exception {
        Scanner scanner = new Scanner(System.in);
        printConsoleMessage(MessageTypes.NORMAL, false, "\tPAY FOR THE JOB YOU'VE GIVEN");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t-----------------------");
        printConsoleMessage(MessageTypes.NORMAL, false,"\tList of jobs given: ");
        String listOfJobs = getListOfJobs();
        if (listOfJobs.equals("")) {
            printConsoleMessage(MessageTypes.NORMAL, false,"\tYou never given a job in order to pay employees, first post a job ");
            return;
        }

        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter the chosen id for the job: ");
        String inJobId = scanner.nextLine();
        Long jobId = Long.parseLong(inJobId);

        Integer chosenPaymentMethod = paymentMethod(jobId);

        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter the employee's id to pay: ");
        String inEmployeeId = scanner.nextLine();
        Long employeeId = Long.parseLong(inEmployeeId);

        Payment payment = new Payment();
        payment.setJobId(jobId);
        payment.setPaymentMethod(chosenPaymentMethod);
        payment.setEmployeeId(employeeId);
        payment.setOriginalAmount(jobSalary);
        payment.setEmployerId(empId);

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/payment");
        requestBody.setAction("payment");
        requestBody.setObject(payment);

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

    public String getListOfJobs() throws IOException {
        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/payment");
        requestBody.setAction("listJobs");
        User user = new User();
        user.setEmail("abiheloaf@gmail.com");
        user.setPassword("pass123");
        requestBody.setObject(user);

        String requestString = new ObjectMapper().writeValueAsString(requestBody);

        ClientServerConnector clientServerConnector = new ClientServerConnector();
        String response = clientServerConnector.connectToServer(requestString);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(response);

        String jobPostingList = String.valueOf(jsonResponse.get("object"));
        int status = jsonResponse.get("status").asInt();
        String message = jsonResponse.get("message").asText();
        String actionDone = jsonResponse.get("actionToDo").asText();

        if (message.equals("NOT FOUND ERROR")) {
            printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
            printConsoleMessage(MessageTypes.NORMAL, false,"STATUS ||         MESSAGE        ||             ACTION DON              ");
            printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
            printConsoleMessage(MessageTypes.NORMAL, false,status+"    ||" + message +"   ||" + actionDone);
            printConsoleMessage(MessageTypes.NORMAL, false,"========================================================================");
            return "";
        }

        String[] strRes = jobPostingList.split("JobPosting");
        List<JobPosting> jobPostings = new ArrayList<JobPosting>();
        for (int i = 1; i<strRes.length; i++) {
//            jobPostings.add();
            String jobPostStr = strRes[i].split("}")[0] + "}";
            String[] fields = jobPostStr.split(", ");
            String jobId = fields[0].split("=")[1];
            String employerId = fields[1].split("=")[1];
            empId = Long.valueOf(employerId);
            String jobDescBefore = fields[3].split("=")[1].substring(1);
            String jobDesc = jobDescBefore.substring(0, jobDescBefore.length() -1);
            String salary = fields[8].split("=")[1].replace("}", "");
            jobSalary = Double.valueOf(salary);

            System.out.println("\t" + jobId + ". \t" + jobDesc + "\t" + salary);
        }

        return Arrays.toString(jobPostingList.split("JobPosting"));
    }

}
