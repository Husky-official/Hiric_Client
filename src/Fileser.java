import java.net.ServerSocket;
import java.net.Socket;

public class Fileser {
    public static void main(String[] args){
        try {
            ServerSocket serverSocket = new ServerSocket(9000);
            boolean isStopped = false;
            while(!isStopped) {
                System.out.println("Server running on port 9000.......");
                Socket socket = serverSocket.accept();
                Clientthread thread = new Clientthread(socket);
                thread.start();
            }
        }catch (Exception exception) {}
    }
}
