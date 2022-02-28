package clientconnector;

/**
 * @author: DABAGIRE Valens
 * @author: ABIJURU Seth
 * @description : Create new socket instance to talk to the server
 */

import interfaces.MessageTypes;
import utils.MessagePrinter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.List;

public class ClientServerConnector {

    public String connectToServer(String requestBody) throws IOException {

        try{
            Socket socket = new Socket("localhost", 8888);
            DataOutputStream requestOut = new DataOutputStream(socket.getOutputStream());
            DataInputStream responseIn = new DataInputStream(socket.getInputStream());

            requestOut.writeUTF(requestBody);

            return responseIn.readUTF();

        } catch(Exception error) {
            MessagePrinter.printConsoleMessage(MessageTypes.ERROR, false, error.getMessage());
        }
        MessagePrinter.printConsoleMessage(MessageTypes.ERROR,  false,"Connection to the server interrupted!");
        return "";
    }
}
