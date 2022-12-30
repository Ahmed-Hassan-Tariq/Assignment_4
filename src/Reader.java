import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Reader implements Runnable{
    @Override
    public void run() {
        System.out.println("Starting Reader");
        File directoryPath = new File("C:\\Users\\Nimko-PC\\Desktop\\dataset");
        File fileList[] = directoryPath.listFiles();

//        for(File file : fileList) {
//            System.out.println("File name: "+file.getName());
//            System.out.println("File path: "+file.getAbsolutePath());
//        }




        File textFile = new File("C:\\Users\\Nimko-PC\\Desktop\\dataset\\10001.txt");
        int count = 0;
        try {
            Scanner scanner = new Scanner(textFile);

            while(scanner.hasNextLine()){
                    String nextLine = scanner.nextLine();
                    if(nextLine.split(" ").length >1) {
                        System.out.println(nextLine);
                        System.out.println(nextLine.split(" ").length);
                        count += nextLine.split(" ").length;
                    }
            }




        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(count);

    }
}
