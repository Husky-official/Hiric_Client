/**
 * @author UWENAYO ALain Pacifique
 * @description a class that contains all the handlers
 */
package utils;

import clientconnector.ClientServerConnector;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import static utils.MessagePrinter.ResponsePrinter;

/** The type Handlers. */
public class Handlers {
  /**
   * Request handler.
   *
   * @param requestString the request string
   * @throws IOException the io exception
   * @role request handling and response printing
   */
  public static void RequestHandler(String requestString) throws IOException {
        ClientServerConnector clientServerConnector = new ClientServerConnector();
        String response = clientServerConnector.connectToServer(requestString);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(response);

        int status = jsonResponse.get("status").asInt();
        String message = jsonResponse.get("message").asText();
        String actionDone = jsonResponse.get("actionToDo").asText();
        ResponsePrinter(status,message,actionDone);
    }
}
