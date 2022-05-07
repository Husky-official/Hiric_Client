
import java.io.*;
import java.net.Socket;

    public class Clientthread  extends  Thread{
        private Socket socket;
        private BufferedOutputStream out;
        private BufferedReader reader;
        private BufferedInputStream fileReader;

        public Clientthread(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run(){
            try{
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new BufferedOutputStream(socket.getOutputStream());

                //READS FILENAME FROM THE CLIENT
                String fileName = reader.readLine();
                File file = new File(fileName);

                if(!file.exists()){
                    byte code = (byte) 0;
                    out.write(code);
                    socket.close();
                }else {
                    out.write((byte) 1);
                    fileReader = new BufferedInputStream(new FileInputStream(file));
                    byte[] buffer = new byte[1024];
                    int bytesRead = 0;
                    //READING CONTENT OF THE FILE
                    while ( (bytesRead = fileReader.read(buffer)) != -1){
                        //SENDING THE CONTENT OF THE FILE TO THE CLIENT
                        out.write(buffer, 0, bytesRead);
                        out.flush();
                    }

                    socket.close();
                }

            }catch (Exception e){}
        }
    }


