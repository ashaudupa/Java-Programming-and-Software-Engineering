package babyNamesMiniProject;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.FileResource;

public class MiniProject {
    public void readOneFile(int year){
        String fname = "data/yob" + year + ".txt";
        FileResource fr = new FileResource(fname);
        //to access record without header row
        CSVParser parser = fr.getCSVParser(false);
        for(CSVRecord record: parser) {
            String name = record.get(0);
            String gender = record.get(1);
        }
    }
    
    public void printNames() {
        FileResource fr = new FileResource();
        
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if(numBorn <= 100) {
                System.out.println("Name " + rec.get(0) + "Gender "+rec.get(1)+"Num Born "+ rec.get(2));
            }
            
        }
    }
    
    public void totalBirths(FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int uniqueBoys = 0;
        int uniqueGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if(rec.get(1).equals("M")) {
                totalBoys += numBorn;
                if(numBorn == 1){
                    uniqueBoys += 1;
                }
            }else {
                totalGirls += numBorn;
                if(numBorn == 1){
                    uniqueGirls += 1;
                }
            }
        }
        System.out.println("total Births = "+totalBirths  );
        
    }
    
    public void testTotalBirths() {
        FileResource fr = new FileResource("testing/yob2014short.csv");
        totalBirths(fr);
        
    }
    
    public static void main(String[] args) {
        MiniProject babyBirth = new MiniProject();
        babyBirth.testTotalBirths();
    }

}
