package CSVParser;

import org.apache.commons.csv.*;

import edu.duke.FileResource;

public class FirstCSVExample {
    public void readFood() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord record: parser) {
            System.out.print(record.get("Name")+" ");
            System.out.print(record.get("Favorite Color")+" " +"Likes"+ " ");
            System.out.println(record.get("Favorite Food")+"");
            
        }
    }
    public static void main(String[] args) {
        FirstCSVExample f1 = new FirstCSVExample();
        f1.readFood();
    }

}
