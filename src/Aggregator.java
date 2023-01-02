import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Aggregator {
    public static int totalCount = 0;
    private static int searchWord = 0;
    public static ArrayList<String> wordLocation = new ArrayList<>();


    public static void getLocations() throws IOException {
        File file = new File("C:\\Users\\Nimko-PC\\IdeaProjects\\Assignment_4\\src\\Locations.txt");
        FileWriter fileWriter = new FileWriter(file);
        for(int i=0;i<wordLocation.size();i++){
            fileWriter.write(wordLocation.get(i)+"\n");
        }
        fileWriter.close();
    }
    public static int getSearchWord(){
        return wordLocation.size();
    }
}
