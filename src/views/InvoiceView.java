package views;

import clientconnector.ClientServerConnector;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Invoice;
import models.RequestBody;

import java.io.IOException;
import java.sql.Date;

public class InvoiceView {
    public void mainMethod() throws Exception {
        generateInvoice();
    }
    public static void main(String[] args) {

    }
    public void generateInvoice() throws IOException {
        Invoice invoice = new Invoice();
        invoice.setInvoiceNumber(generateInvoiceNumber());
        invoice.setUserId(fetchUserId());
        invoice.setJobId(fetchJobId());
        invoice.setDate(Date.valueOf("10-02-2022"));

        RequestBody requestBody = new RequestBody();
        requestBody.setUrl("/invoice");
        requestBody.setAction("generate");
        requestBody.setObject(invoice);

        String requestString = new ObjectMapper().writeValueAsString(requestBody);
        ClientServerConnector clientServerConnector = new ClientServerConnector();
        String response = clientServerConnector.connectToServer(requestString);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode responseJson = objectMapper.readTree(response);

    }
    public long generateInvoiceNumber(){
        return 100000;
    }
    public int fetchUserId(){
        return 1000;
    }
    public int fetchJobId(){
        return 10203;
    }

}


