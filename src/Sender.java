import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Callable;


public class Sender implements Callable {

    private static ServerSocket server;
    private static Socket socket;
    private static File file;

    private int fromFile;
    private int toFile;

    private int wordCount = 0;



    public Sender(int fromFile,int toFile) throws Exception {
        this.fromFile = fromFile;
        this.toFile = toFile;
        System.out.println("Sender Started");
        server = new ServerSocket(1018);
        System.out.println("Waiting for Client to Connect");
        socket = server.accept();
        System.out.println("Client Connected");
        Instant start = Instant.now();

        //Files sender
        File directoryPath = new File("C:\\Users\\Nimko-PC\\Desktop\\dataset");
        File fileList[] = directoryPath.listFiles();
        int count = 0;
        int searchCount = 0;

        for(int i=fromFile; i<toFile;i++){
            this.file = fileList[i];
            call();
        }
//        socket.close();
//        server.close();
        Instant end = Instant.now();
        Duration duration = Duration.between(start,end);
        System.out.println("Thread Count: "+wordCount+", "+duration.toSeconds()+"s");
    }

    @Override
    public Object call() throws Exception {
        int response = 0;
        if (socket.isConnected()) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(file);


            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            response = (int) objectInputStream.readObject();
            wordCount += response;

        }
        return wordCount;
    }
}

