package views;

import clientconnector.ClientServerConnector;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.MessageTypes;
import models.RequestBody;
import utils.MessagePrinter;

import java.io.IOException;
import java.util.Scanner;

import static utils.MessagePrinter.printConsoleMessage;

/**
 * @author Biziyaremye Henriette
 * @description contract view
 *
 * */
public class ContractView {

 Scanner sc = new Scanner(System.in);



    /**
     * @author Henriette Biziyaremye
     * @description loading wrapper for the view
     * */
    public void LoadingWrapper () throws InterruptedException, IOException {
        for (int i = 0; i <= 20; i++) {
            MessagePrinter.printConsoleMessage(MessageTypes.ACTION, true, ".");
            Thread.sleep(100);
        }
    }

    /**
     * @author Henriette Biziyaremye
     * @description get all jobs request on the server
     * */
    public String GetAllJobs(Integer userId) throws JsonProcessingException {
        RequestBody requestBody = new RequestBody();

        requestBody.setUrl("/Contracts");
        requestBody.setAction("GetAllJobs");
        requestBody.setObject(userId);

        String RequestString = new ObjectMapper().writeValueAsString(requestBody);
        System.out.println(RequestString);
        return RequestString;

    }

    public static void mainMethod() throws IOException, InterruptedException {
        int choice;
        ContractView contractView = new ContractView();
        new ContractView().LoadingWrapper();
        MessagePrinter.printConsoleMessage(MessageTypes.ACTION,true , "CONTRACT SIGNING PAGE");
        new ContractView().LoadingWrapper();
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL, false," ");


        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL,false,"Choose one of below roles");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL,false,"1.Employer");
        MessagePrinter.printConsoleMessage(MessageTypes.NORMAL,false,"2.Employee");

       choice = contractView.sc.nextInt();
        if(choice == 1){
            MessagePrinter.printConsoleMessage(MessageTypes.NORMAL,false,"Welcome dear employer");
            MessagePrinter.printConsoleMessage(MessageTypes.NORMAL,false,"Enter you id...");
            choice = contractView.sc.nextInt();
            new ContractView().GetAllJobs(choice);

            //sending the request to the server
            ClientServerConnector clientServerConnector = new ClientServerConnector();
            String request = clientServerConnector.connectToServer(new ContractView().GetAllJobs(choice));

            //Getting the response
            ObjectMapper  objectMapper = new ObjectMapper();
            JsonNode jsonResponse =  objectMapper.readTree(request);

            int status = jsonResponse.get("status").asInt();
            String message = jsonResponse.get("message").asText();
            String action = jsonResponse.get("actionToDo").asText();

            if(status == 302) {
                printConsoleMessage(MessageTypes.SUCCESS, false, "========================================================================");
                printConsoleMessage(MessageTypes.SUCCESS, false, "STATUS ||         MESSAGE        ||             ACTION DON              ");
                printConsoleMessage(MessageTypes.SUCCESS, false, "========================================================================");
                printConsoleMessage(MessageTypes.SUCCESS, false, status + "    ||" + message +         "   ||"+action);
                printConsoleMessage(MessageTypes.SUCCESS, false, "========================================================================");
            }else{
                printConsoleMessage(MessageTypes.ERROR, false, "========================================================================");
                printConsoleMessage(MessageTypes.ERROR, false, "STATUS ||         MESSAGE        ||             ACTION DONE              ");
                printConsoleMessage(MessageTypes.ERROR, false, "========================================================================");
                printConsoleMessage(MessageTypes.ERROR, false, status + "    ||" + message + "   || "+action);
                printConsoleMessage(MessageTypes.ERROR, false, "========================================================================");
            }




        }
        else if(choice == 2){
            MessagePrinter.printConsoleMessage(MessageTypes.NORMAL,false,"You are an employee");
        }


    }
}
