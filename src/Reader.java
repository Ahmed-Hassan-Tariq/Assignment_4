import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
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
        System.out.println("Reader Started");
        Instant start = Instant.now();
        File directoryPath = new File("C:\\Users\\Nimko-PC\\Desktop\\CS\\Semester_3\\Object-Oriented-Programming\\dataset");
        File fileList[] = directoryPath.listFiles();
        int count = 0;

        //Search word and count
        String searchWord = "is";

        ArrayList<String> wordLocation = new ArrayList<>();
        for (int i = fromFileNo;i<toFileNo;i++ ) {
            int lineNumber = 0;
            try {
                Scanner scanner = new Scanner(fileList[i].getAbsoluteFile());
                while (scanner.hasNextLine()) {
                    String nextLine = scanner.nextLine();
                    if (nextLine.split(" ").length > 1) {
                        lineNumber++;
                        for(int j = 0; j<nextLine.split(" ").length;j++){
                            if(nextLine.split(" ")[j].equalsIgnoreCase(searchWord)){
                                Aggregator.wordLocation.add("File: "+fileList[i].getName()+" "+"Line:"+ lineNumber+" "+"Location: "+(j+1));
                            }
                        }


                        count += nextLine.split(" ").length;
                        Aggregator.totalCount+=nextLine.split(" ").length;
                    }
                }
                scanner.close();


            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
        Instant end = Instant.now();
        Duration totalTime = Duration.between(start, end);
        System.out.println("Thread Count: "+count+", "+"Time: "+totalTime.toSeconds()+"s");

        for(int i=0;i<wordLocation.size();i++){
            System.out.println(wordLocation.get(i));
        }

        return count;
    }

}
