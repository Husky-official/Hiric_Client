import java.io.*;
import java.net.Socket;

public class Fileclient {
    public static void main(String[] args){
        try {
            InputStreamReader in = new InputStreamReader(System.in);
            BufferedReader reader = new BufferedReader(in);

            String ipAddress = "";
            String fileName = "";

            System.out.print("Please enter a valid IP : ");
            ipAddress = reader.readLine();

            System.out.println("Enter a file  name: ");
            fileName = reader.readLine();

            Socket  socket = new Socket(ipAddress,9000);
            InputStream inputByte = socket.getInputStream();
            BufferedInputStream input = new BufferedInputStream(inputByte);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            //Sends the filename to the server
            out.println(fileName);

            //Reads the status code from the server
            int code = input.read();

            if(code == 1) {
                FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\student\\Downloads\\" + fileName);
                BufferedOutputStream outPutFile = new BufferedOutputStream(fileOutputStream);
                byte[] buffer = new byte[1024];
                int bytesRead = 0;

                //RECEIVING CONTENT OF THE FILE FROM THE SERVER
                while((bytesRead = input.read(buffer)) != -1 ) {
                    System.out.println(".");
                    //WRITING THE CONTENT TO THE DESTINATION
                    outPutFile.write(buffer,0, bytesRead);
                    outPutFile.flush();
                }
                System.out.println();
                System.out.println("File: " + fileName + " was successfully downloaded");
            }else {
                System.out.println("File isn't present on the server");
            }


        }catch (Exception exception){
            System.out.println(exception.toString());
        }
    }
}