package CSVFiles;

import java.io.File;

import edu.duke.*;

import org.apache.commons.csv.*;
public class CSVMax {
    public CSVRecord hottestHourInFile(CSVParser parser){
        //start with largestSoFar as nothing
        CSVRecord largestSoFar = null;
        //For each row(currentRow) in the CSV file
        for(CSVRecord currentRow : parser){
            //If largestSoFar is nothing
            /*if(largestSoFar == null){
                largestSoFar = currentRow;
                //Otherwise
            }else{
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
                // check if currentRow's temperature > largestSoFar
                if(currentTemp > largestTemp){
                    //if so update the largestSoFar to currentRow
                    largestSoFar = currentRow;
                }
                
            }*/
            largestSoFar = getLargestOfTwo(currentRow,largestSoFar);
        }
        //The largestSoFar is the answer
        return largestSoFar;
    }
    
    public void testHottestInDay(){
        FileResource fr = new FileResource();
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("Hottest Temperature was " +largest.get("TemperatureF")+"at " +largest.get("TimeEST"));
        System.out.println("-----------------------------------------------------------------------------");
    }
    
    public CSVRecord hottestInManyDays(){
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        //Iterate over files
        for(File file:dr.selectedFiles()){
            FileResource fr = new FileResource(file);
            //use methods to get largest in file
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            largestSoFar = getLargestOfTwo(currentRow,largestSoFar);
            }
        return largestSoFar;
    }
    
    public CSVRecord getLargestOfTwo(CSVRecord currentRow,CSVRecord largestSoFar){
        if(largestSoFar == null){
            largestSoFar = currentRow;
        }else{
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            // check if currentRow's temperature > largestSoFar
            if(currentTemp > largestTemp){
                //if so update the largestSoFar to currentRow
                largestSoFar = currentRow;
            }
            
        }
    return largestSoFar;
    }

    public void testHottestInManyDays(){
        //FileResource fr = new FileResource();
        CSVRecord largest = hottestInManyDays();
        System.out.println("Hottest Temperature was " +largest.get("TemperatureF")+"at " +largest.get("DateUTC"));
        System.out.println("-------------------------------------------");
    }
    
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestSoFar = null;
        for(CSVRecord currentRow: parser){
            //coldestSoFar = ColdestOfTwo(coldestSoFar,currentRow);
            if(coldestSoFar == null){
                coldestSoFar = currentRow;
            }else {
                String currentTempVal = currentRow.get("TemperatureF");
                if(currentTempVal.equals("-9999")){
                    continue;
                }
                double currentTemp = Double.parseDouble(currentTempVal);
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if(currentTemp < coldestTemp){
                    coldestSoFar = currentRow;
                }
            }
        }
        
        return coldestSoFar;
    }
    
    /*public CSVRecord ColdestOfTwo(CSVRecord coldestSoFar,CSVRecord currentRow){
        if(coldestSoFar == null){
            coldestSoFar = currentRow;
        }else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
            if(currentTemp < coldestTemp){
                coldestSoFar = currentRow;
            }
        }
            return coldestSoFar;
    }*/
  
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest Temperature was: "+ coldest.get("TemperatureF")+ "at"+coldest.get("DateUTC"));
        System.out.println("-------------------------------------------");
    }
    
    public String fileWithColdestTemparature(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldestSoFar = null;
        String fileName = null;
        for(File f:dr.selectedFiles()){
            FileResource fr = new FileResource(f); 
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            //coldestSoFar = ColdestOfTwo(coldestSoFar,currentRow);
            if(coldestSoFar == null){
                coldestSoFar = currentRow;
            }else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if(currentTemp < coldestTemp){
                    coldestSoFar = currentRow;
                    fileName = f.getName();
                }
            }
            
        }
        return fileName;
        
    }
    public void testFileWithColdestTemperature() {
        String fileName = fileWithColdestTemparature();
        System.out.println(fileName);
        File f = new File(fileName);
        FileResource fr = new FileResource(f);
        System.out.println("ColdestDay was in File: "+fileName);
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest Hour in file "+ coldest);
        System.out.println("-------------------------------------------");
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestHumidity = null;
        for(CSVRecord currentRow: parser){
            if(lowestHumidity == null){
                lowestHumidity = currentRow;
            }else{
                String currentHumid = currentRow.get("Humidity");
                if(currentHumid.equals("N/A")){
                    continue;
                }
                int currentHumidLevel = Integer.parseInt((currentHumid));
                int LowestHumidLevel = Integer.parseInt(lowestHumidity.get("Humidity"));
                if(currentHumidLevel == LowestHumidLevel){
                    continue;
                }else if(currentHumidLevel < LowestHumidLevel){
                    lowestHumidity = currentRow;
                }
            }
        }
        return lowestHumidity;
    }
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was "+csv.get("Humidity")+"at"+csv.get("DateUTC"));
        System.out.println("-------------------------------------------");
    }
    
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestHumidity = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            if(lowestHumidity == null){
                lowestHumidity = currentRow;
            }else{
                String currentHumid = currentRow.get("Humidity");
                if(currentHumid.equals("N/A")){
                    continue;
                }
                int currentHumidLevel = Integer.parseInt((currentHumid));
                int LowestHumidLevel = Integer.parseInt(lowestHumidity.get("Humidity"));
                if(currentHumidLevel == LowestHumidLevel){
                    continue;
                }else if(currentHumidLevel < LowestHumidLevel){
                    lowestHumidity = currentRow;
                }
                
            }
        }
        return lowestHumidity;
        
    }
    
    public void testLowestHumidityInManyFiles() {
        CSVRecord lowestHumidity = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was "+lowestHumidity.get("Humidity")+"at"+lowestHumidity.get("DateUTC"));
        System.out.println("-------------------------------------------");
        
    }
    
    public Double averageTemperatureInFile(CSVParser parser) {
        Double sum = 0.0;
        int count = 0;
        for(CSVRecord record : parser) {
            Double currentTemp = Double.parseDouble(record.get("TemperatureF"));
            sum = sum + currentTemp;
            count++;
        }
        Double average = sum / count;
        return average;
        
    }
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        Double average = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average Temperature in file is "+average);
        System.out.println("-------------------------------------------");
        
    }
    
    public Double averageTemperatureWithHighHumidityInFile(CSVParser parser,int value) {
        Double sum = 0.0;
        int count = 0;
        for(CSVRecord record: parser){
            int humidLevel = Integer.parseInt(record.get("Humidity"));
            Double currentTemp = Double.parseDouble(record.get("TemperatureF"));
            if(humidLevel >= value) {
                sum = sum + currentTemp;
                count++;
            }
        }
        Double average = sum / count;
        return average;
        
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        int value = 80;
        Double average = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(),value);
        if(average > 0.0){
            System.out.println("Average Temperature when high humidity is "+average);
        }else {
            System.out.println("No temperatures with that humidity");
        }
        System.out.println("-------------------------------------------");
        
    }
    
    
    
    public static void main(String[] args) {
        CSVMax csv = new CSVMax();
        //csv.testHottestInManyDays();
        csv. testAverageTemperatureWithHighHumidityInFile();
        //csv.testAverageTemperatureInFile();
        //csv.testLowestHumidityInManyFiles();
        //csv.testLowestHumidityInFile(); 
        //csv. testFileWithColdestTemperature();
        //csv.testColdestHourInFile();
    }

}
