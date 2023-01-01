import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class Reader implements Callable {
    int fromFileNo;
    int toFileNo;

    public Reader(int fromFileNo, int toFileNo) {
        this.fromFileNo = fromFileNo;
        this.toFileNo = toFileNo;
    }

    @Override
    public Object call() throws Exception {
        Counts c = new Counts();
        Instant start = Instant.now();
        File directoryPath = new File("C:\\Users\\Nimko-PC\\Desktop\\dataset");
        File fileList[] = directoryPath.listFiles();
        int count = 0;

        //Search word and count
        String searchWord = "ebook";
        int searchCount = 0;
        for (int i = fromFileNo;i<toFileNo;i++ ) {
            File textFile = new File("C:\\Users\\Nimko-PC\\Desktop\\dataset\\10001.txt");


            try {
                Scanner scanner = new Scanner(fileList[i].getAbsoluteFile());
//                System.out.println("FileName: "+fileList[i].getName());
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
//                System.out.println("Word Count: "+count);
//                System.out.println("Specific Word Count: "+searchCount);
//                System.out.println("----------------------------------");

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
        Instant end = Instant.now();
        Duration totalTime = Duration.between(start, end);
        System.out.println("Thread Count: "+count+", "+totalTime.toSeconds());

        return count;
    }

    public class Counts{
        int count;
        int searchCount;

    }
}
