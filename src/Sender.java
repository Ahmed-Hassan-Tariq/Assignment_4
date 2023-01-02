import java.io.*;
import java.net.Proxy;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Callable;


public class Sender implements Callable {

    private ServerSocket server;
    private Socket socket;
    private int fromFile;
    private int toFile;

    private int wordCount = 0;



    public Sender(int fromFile,int toFile) throws Exception {
        this.fromFile = fromFile;
        this.toFile = toFile;
    }

    @Override
    public Object call() throws Exception {
        System.out.println("Sender Started");
        server = new ServerSocket(1018);
        System.out.println("Waiting for Client to Connect");
        socket = server.accept();
        System.out.println("Client Connected");
        Instant start = Instant.now();

        //Files sender
        File directoryPath = new File("C:\\Users\\Nimko-PC\\Desktop\\CS\\Semester_3\\Object-Oriented-Programming\\dataset");
        File fileList[] = directoryPath.listFiles();
        int count = 0;
        int searchCount = 0;

        for(int i=fromFile; i<toFile;i++) {
            int response = 0;
            if (socket.isConnected()) {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(fileList[i]);


                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                response = (int) objectInputStream.readObject();
                wordCount += response;
                Aggregator.totalCount+=response;

            }
        }
        Instant end = Instant.now();
        Duration duration = Duration.between(start,end);
        System.out.println("Received Count: "+wordCount+", "+duration.toSeconds()+"s");
        return wordCount;
    }
}

