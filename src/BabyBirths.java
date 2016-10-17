package miniproject;

import java.io.File;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class BabyBirths {
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
                uniqueBoys += 1;
            }else if(rec.get(1).equals("F")) {
                totalGirls += numBorn;
                uniqueGirls += 1;
            }
        }
        int totalNames = uniqueBoys + uniqueGirls;
        System.out.println("total Births = "+totalBirths  );
        System.out.println("Number of Girls "+totalGirls);
        System.out.println("Number of Unique Girls " + uniqueGirls);
        System.out.println("Number of Boys "+totalBoys);
        System.out.println("Number of Unique Boys "+uniqueBoys);
        System.out.println("Total names in the file "+totalNames);
        
        
    }
    
    public void testTotalBirths() {
        //String fname = "testing/yob2012short.csv";
        FileResource fr = new FileResource("us_babynames_by_year/yob1905.csv");
        //FileResource fr = new FileResource(fname);
        totalBirths(fr);
        
    }
    
    public int getRank(int year,String name,String gender){
        String fname = "us_babynames_by_year/yob"+year+".csv";
        FileResource fr = new FileResource(fname);
        int rank = 0;
        boolean found = false;
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(rec.get(1).equals(gender)){
                rank = rank +1;
                if(rec.get(0).equals(name)) {
                    found = true;
                    break;
                }
            }
 
        }
        if(found){
            return rank;
        }else {
            return -1;
        }
    }
    
    public void testGetRank(){
        int rank = getRank(1960,"Emily","F");
        if(rank == -1){
            System.out.println("Name not found with the gender");
        }else {
            //rank = rank + 1;
            System.out.println("Rank of the Name is  "+rank);
        }
        
    }
    
    public String getName(int year,int rank,String gender){
        String fname = "us_babynames_by_year/yob"+year+".csv";
        FileResource fr = new FileResource(fname);
        int rankInFile = 1;
        String name = null ;
        
        for(CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)){
                if(rankInFile == rank){
                    name = rec.get(0);
                }
                rankInFile ++;
            }
        }
        if(name == null){
            return "NO NAME";
        }else {
            return name;
        }
        
        
        
    }
    
    public void testGetName() {
        String name =  getName(1980,350,"F");
        System.out.println(name);
    }
    
    public String whatIsNameInYear(String name,int year,int newYear,String gender){
        //String fileYearTheNameBorn = "testing/yob"+year+"short.csv";
        //String fileNewYear = "testing/yob"+newYear+"short.csv";
        int rankOfNameBorn = getRank(year,name,gender);
        String newName = getName(newYear,rankOfNameBorn,gender);
        return newName;
        
    }
    
    public void testWhatIsNameInYear(){
        String oldName = "Susan";
        String gender = "F";
        int year = 1972;
        int newYear = 2014;
        String newName = whatIsNameInYear(oldName,year,newYear,gender);
        System.out.println(oldName+" born in "+year+" would be "+newName +" if she was born in"+newYear);
    }
    
    public int yearOfHighestRank(String name,String gender) {
        int yearOfHighRank = 0;
        int highRank = 0;
        int rank;
        boolean found = false;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            //FileResource fr = new FileResource(f);
            //CSVParser parser = fr.getCSVParser(false);
            int year = getYear(f); 
            rank = getRank(year,name,gender);
            if(rank != -1) {
                found = true;
            }
            if(highRank == 0){
                highRank = rank;
                yearOfHighRank = year;
            }else if(rank < highRank) {
                highRank = rank;
                yearOfHighRank = year;
                
            } 
        }
        if(found == false){
            return -1;
        }else {
            return yearOfHighRank;
        }
        
    }
    
    public void testYearOfHighestRank(){
        String name = "Genevieve";
        String gender = "F";
        int year = yearOfHighestRank(name,gender);
        if(year == -1) {
            System.out.println("name and gender are not in any of the selected files");
        }else {
            System.out.println("Year of Highest Rank is "+year);
        }
    }
    
    public int getYear(File f){
        String fileName = f.getName();
        //System.out.println(fileName);
        int year = Integer.parseInt(fileName.substring(3,7));
        return year;
    }
    
    public double getAverageRank(String name,String gender){
        int sumRank = 0;
        double averageRank = 0.0; 
        int rank = 0;
        int count = 0;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);
            int year = getYear(f); 
            rank = getRank(year,name,gender);
            //System.out.println(rank);
            if(rank != -1) {
                count++; 
                sumRank = sumRank + rank;
            }
            
        }
        averageRank = (double)sumRank / count;
        if(sumRank == -1){
            return -1;
        }else {
            return averageRank;
        }
        
    }
    
    public void testgetAverageRank() {
        String name = "Robert";
        String gender = "M";
        double average =getAverageRank(name,gender);
        if(average == -1){
            System.out.println("name is not ranked in any of the selected files");
        }else {
            System.out.println("Average rank value "+average);
        }
    }
    
    public int getTotalBirthsRankedHigher(int year,String name,String gender) {
        
        int total = 0;
        int highRank = 0;
        int rank = 0;
        //String fname = "testing/yob"+year+"short.csv";
        String fname = "us_babynames_by_year/yob"+year+".csv";
        FileResource fr = new FileResource(fname);
        CSVParser parser = fr.getCSVParser(false);
        highRank = getRank(year,name,gender);
        //System.out.println(highRank);
        for(CSVRecord rec : parser) {
            if((rec.get(1).equals(gender))) {
                rank = getRank(year,rec.get(0),rec.get(1));
                if(rank < highRank) {
                    total = total + Integer.parseInt(rec.get(2));
                }else {
                    break;
                }
            }
        }
        return total;
    }
    
    public void testGetTotalBirthsRankedHigher() {
        int totalBirthRankedHigher = getTotalBirthsRankedHigher(1990,"Drew","M");
        System.out.println("Total Births Ranked Higher than  "+totalBirthRankedHigher);
    }
    public static void main(String[] args) {
        BabyBirths babyBirth = new BabyBirths();
        //babyBirth.testTotalBirths();
       //babyBirth.testGetRank();
       //babyBirth.testGetName();
        //babyBirth.testWhatIsNameInYear();
       babyBirth.testYearOfHighestRank();
        //babyBirth.testgetAverageRank();
        //babyBirth.testGetTotalBirthsRankedHigher();
    }

}
