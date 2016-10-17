package babyNamesMiniProject;

import org.apache.commons.csv.CSVRecord;

import edu.duke.FileResource;

public class BabyBirth {
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
        BabyBirth babyBirth = new BabyBirth();
        babyBirth.testTotalBirths();
    }

}
