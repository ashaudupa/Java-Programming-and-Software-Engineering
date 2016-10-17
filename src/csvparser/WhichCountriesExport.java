package csvparser;

import org.apache.commons.csv.*;
import edu.duke.FileResource;

public class WhichCountriesExport {
    public void listExporters(CSVParser parser,String exportOfInterest) {
        //for each row in the CSV file
        for(CSVRecord record : parser){
          //Look at the "Exports" column
            String exports = record.get("Exports");
            //check if it contains "exportOfInterest"
            if(exports.contains(exportOfInterest)){
              //if so , write down the "Country from that row
                String country = record.get("Country");
                System.out.println(country);
            }
            
        }
        
  }
    public void whoExportCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser,"coffee");
    }
    
    
    public String countryInfo(CSVParser parser,String country){
        String c = null;
        for(CSVRecord record : parser){
            String info = record.get("Country");
            //System.out.println(info.contains(country));
            if(info.contains(country)){
                c = toString(country +":" + record.get("Exports")+":"+record.get("Value (dollars)"));
                break;
                
            }else{
                c = toString("NOT FOUND");
            }
        }
        return c;
        
        
    }
    
    public void listExporterTwoProducts(CSVParser parser,String exportItem1,String exportItem2){
        for(CSVRecord record: parser) {
            String exports = record.get("Exports");
            if(exports.contains(exportItem1) && exports.contains(exportItem2)){
                System.out.println(record.get("Country"));
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser,String exportItem) {
        int num = 0;
        for(CSVRecord record: parser){
            String export = record.get("Exports");
            if(export.contains(exportItem)){
                num = num + 1;
            }
        }
        return num;
    }
    
    public void bigExporters(CSVParser parser,String amount){
        for(CSVRecord record : parser) {
            String value = record.get("Value (dollars)");
            if(value.length() > amount.length()) {
                System.out.println(record.get("Country")+ record.get("Value (dollars)"));
            }
        }
    }
    
    private String toString(String string) {
        // TODO Auto-generated method stub
        return string;
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        
        CSVParser parser_part1 = fr.getCSVParser();
        String country = countryInfo(parser_part1,"Nauru");
        System.out.println("Country Information: ");
        System.out.println(country);
        System.out.println("----------------------------------------------");
        
        CSVParser parser_part2 = fr.getCSVParser();
        //System.out.println("List Exporters of two products Gold and Diamond are: ");
        //listExporterTwoProducts(parser_part2,"gold","diamonds");
        System.out.println("List Exporters of two products fish and nuts are: ");
        listExporterTwoProducts(parser_part2,"fish","nuts");
        System.out.println("----------------------------------------");
        
        
        CSVParser parser_part3 = fr.getCSVParser();
        /*int number = numberOfExporters(parser_part3,"gold");
        System.out.println("Number of Exportsers of Gold: "+ number);
        System.out.println("---------------------------------------");*/
        int number = numberOfExporters(parser_part3,"sugar");
        System.out.println("Number of Exportsers of Sugar: "+ number);
        System.out.println("---------------------------------------");
        
        CSVParser parser_part4 = fr.getCSVParser();
        System.out.println("Big Exporters in the list: ");
        bigExporters(parser_part4,"$999,999,999,999");
        
    }
    
    public static void main(String[] args) {
        WhichCountriesExport w = new WhichCountriesExport();
        //w.whoExportCoffee();
        w.tester();
    }

}
