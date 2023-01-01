import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Main {
    public static void main(String[] args) throws Exception {
        Instant start = Instant.now();
        File directoryPath = new File("C:\\Users\\Nimko-PC\\Desktop\\dataset");
        File fileList[] = directoryPath.listFiles();
        System.out.println("Files in Directory: "+fileList.length);
        Reader thread1 = new Reader(0,200);
        Reader thread2 = new Reader(200,400);
        Reader thread3 = new Reader(400,600);
        Reader thread4 = new Reader(600,800);
        Reader thread5 = new Reader(800,1000);
        Reader thread6 = new Reader(1000,1100);
        Sender thread7 = new Sender(1100,1176);

        FutureTask<Integer> ft1 = new FutureTask<Integer>(thread1);
        FutureTask<Integer> ft2 = new FutureTask<Integer>(thread2);
        FutureTask<Integer> ft3 = new FutureTask<Integer>(thread3);
        FutureTask<Integer> ft4 = new FutureTask<Integer>(thread4);
        FutureTask<Integer> ft5 = new FutureTask<Integer>(thread5);
        FutureTask<Integer> ft6 = new FutureTask<Integer>(thread6);
        FutureTask<Integer> ft7 = new FutureTask<Integer>(thread7);

        ExecutorService executor = Executors.newFixedThreadPool(7);


        executor.execute(ft1);
        executor.execute(ft2);
        executor.execute(ft3);
        executor.execute(ft4);
        executor.execute(ft5);
        executor.execute(ft6);
        executor.execute(ft7);
        boolean loop = true;
        while(loop) {
            if (ft1.isDone() && ft2.isDone() && ft3.isDone() && ft4.isDone() && ft5.isDone() && ft6.isDone() && ft7.isDone()) {
                System.out.println("Done");
                loop = false;
                executor.shutdown();
                Instant end = Instant.now();
                Duration totalTime = Duration.between(start, end);
                int totalCount = ft1.get()+ft2.get()+ft3.get()+ft4.get()+ft5.get()+ft6.get()+ft7.get();
                System.out.println("Total File Count: "+totalCount);
                System.out.println("Total Time: " + totalTime.toSeconds() + "s");
            }


        }

    }
}