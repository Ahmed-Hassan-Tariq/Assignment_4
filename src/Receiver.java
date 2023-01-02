import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class Receiver {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Socket socket = null;
        ObjectOutputStream outputStream = null;
        ObjectInputStream objectInputStream = null;
        //establish socket connection to server
        socket = new Socket("localhost", 1018);
//        socket.setKeepAlive(true);

        //write to socket using ObjectOutputStream
        Instant start = Instant.now();
        while (socket.isConnected()) {
            try {
                objectInputStream = new ObjectInputStream(socket.getInputStream());
            } catch (SocketException e){
                objectInputStream.close();
                socket.close();
                break;
            }

                File receivedFile = (File) objectInputStream.readObject();
                System.out.println("Server File: " + receivedFile.getName());

                Scanner scanner = new Scanner(receivedFile.getAbsoluteFile());
                int count = 0;
                int searchCount = 0;
                String searchWord = "eBook";
                while (scanner.hasNextLine()) {
                    String nextLine = scanner.nextLine();
                    if (nextLine.split(" ").length > 1) {
                        for(int j = 0; j<nextLine.split(" ").length;j++){
                            if(nextLine.split(" ")[j].equalsIgnoreCase(searchWord)){
                                searchCount+=1;
                            }
                        }


                        count += nextLine.split(" ").length;
                    }
                }
                scanner.close();
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(count);
        }
        System.out.println("Client Disconnected");
        Instant end = Instant.now();
        Duration duration = Duration.between(start,end);
        System.out.println(duration.toSeconds()+"s");
    }
}