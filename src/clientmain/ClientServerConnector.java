package clientmain;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *@author: DABAGIRE Valens
 * @description : Create new socket instance to talk to the server
 * */

public class ClientServerConnector {

    public String connectToServer(String requestBody) throws IOException {

        try{
            Socket socket = new Socket("localhost", 8888);
            DataOutputStream requestOut = new DataOutputStream(socket.getOutputStream());
            DataInputStream responseIn = new DataInputStream(socket.getInputStream());

            requestOut.writeUTF(requestBody);

            return responseIn.readUTF();

        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
