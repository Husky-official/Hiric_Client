package views;

import clientconnector.ClientServerConnector;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.MessageTypes;
import models.Payment;
import models.RequestBody;

import java.time.LocalDate;
import java.util.Scanner;

import static utils.MessagePrinter.printConsoleMessage;

public class BillingView {

    public void makePayment() throws Exception {

        Scanner scanner = new Scanner(System.in);
        printConsoleMessage(MessageTypes.NORMAL, false, "\tPAY FOR THE JOB YOU'VE GIVEN");
        printConsoleMessage(MessageTypes.NORMAL, false,"\t-----------------------");
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter the id for the job: ");
        String inJobId = scanner.nextLine();
        Long jobId = Long.parseLong(inJobId);

        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter the original amount for the job: ");
        String inOgAmount = scanner.nextLine();
        Double originalAmount = Double.parseDouble(inOgAmount);

        paymentMethod();
        String chosenPaymentMethod = paymentMethod();

        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter the amount deducted from employee if any. If no, enter 0: ");
        String inReducedAmount = scanner.nextLine();
        Double reducedAmount = Double.parseDouble(inReducedAmount);

        LocalDate dateOfPayment = LocalDate.now();
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter the employee's id to pay: ");
        String inEmployeeId = scanner.nextLine();
        Long employeeId = Long.parseLong(inEmployeeId);
        printConsoleMessage(MessageTypes.NORMAL, false,"\tEnter your id as employer: ");
        String inEmployerId = scanner.nextLine();
        Long employerId = Long.parseLong(inEmployerId);

        Payment payment = new Payment();
        payment.setJobId(jobId);
        payment.setOriginalAmount(originalAmount);
        payment.setPaymentMethod(chosenPaymentMethod);
        payment.setReducedAmount(reducedAmount);
        payment.setDateOfPayment(dateOfPayment);
        payment.setEmployeeId(employeeId);
        payment.setEmployerId(employerId);

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

}
