package views;

import clientconnector.ClientServerConnector;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.MessageTypes;
import models.Payment;
import models.RequestBody;
import models.User;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static utils.MessagePrinter.printConsoleMessage;

public class BillingView {
    public String paymentMethod(Double amount) throws Exception {
        String returnString = "";
        Scanner scan = new Scanner(System.in);
//        User payer = new User("hfdjshfsadjf","aldo@aldo.com");
//        User payee = new User("jfdksjfksf","jabes@jabes.com");
        Double money_to_pay = amount;
//        PayObject payObject = new PayObject(payee,payer,money_to_pay);
        printConsoleMessage(MessageTypes.NORMAL, false, "||  You must pay "+money_to_pay+"||");
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
            returnString = "MobileMoney";
        }else if(choice == 2){
            printConsoleMessage(MessageTypes.NORMAL,false,"Enter your email: ");
            String paypal_email = scan.nextLine();
            printConsoleMessage(MessageTypes.NORMAL,false,"Enter your password: ");
            String paypal_password = scan.nextLine();
            //due to it being a console version we can't use API since they require a web interface
            returnString = "PayPal";
        }else {
            printConsoleMessage(MessageTypes.NORMAL, false, "Invalid input !");
            TimeUnit.SECONDS.sleep(3);
            paymentMethod(amount);
        }
        return returnString;
    }

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

        String chosenPaymentMethod = paymentMethod(originalAmount);

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

    public static String paymentMethod() {
        return "payment";
    }

}
