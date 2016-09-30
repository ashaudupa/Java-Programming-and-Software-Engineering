package DNAString;

import edu.duke.FileResource;
import edu.duke.StorageResource;

public class FindAndStoreAllGenes {
    public StorageResource storeAll(String dna){
        StorageResource store = new StorageResource();
        dna = dna.toLowerCase();
        int start = 0;
        while(true) {
            int loc = dna.indexOf("atg",start);
            //System.out.println(loc);
            int stop = findStopIndex(dna, loc+3);
            //System.out.println(stop);
            if(loc == -1) {
                break;
            }
            if(stop == dna.length()) {
                start = start +3;
                continue;
            }
            //System.out.println(dna.substring(loc,stop+3));
            store.add(dna.substring(loc,stop+3));
            
            start = stop +3;
            
        }
        return store;
    }
    
    public int findStopIndex(String dna,int index) {
        //System.out.println(dna);
        int stop1 = dna.indexOf("tga",index);
        //System.out.println("stop1 " +stop1);
        if(stop1 == -1 || (stop1-index) % 3 != 0) {
            stop1 = dna.length();
        }
        
        int stop2 = dna.indexOf("taa",index);
        //System.out.println("stop2 "+stop2);
        if(stop2 == -1 || (stop2 - index) % 3 != 0) {
            stop2 = dna.length();
        }
        
        int stop3 = dna.indexOf("tag", index);
        //System.out.println("stop3 "+stop3);
        if( stop3 == -1 || (stop3 - index) % 3 != 0) {
            stop3 = dna.length();
        }
        //System.out.println("minimum val " +Math.min(stop1, Math.min(stop2, stop3)));
        return Math.min(stop1, Math.min(stop2, stop3));
    }
    
    
    public void testStorageFinder() {
        FileResource fr = new FileResource("brca1line.fa");
        //String dna = "ATGCCATAG";
        StorageResource store = new StorageResource();
       /* for(String dna : fr.words()){
            store = storeAll(dna);
            }*/
        store = storeAll(fr.asString());
        //store = storeAll(dna);
       // System.out.println("Genes found "+store.data());
        printGenes(store);
    }
    
    public float cgRatio(String dna) {
        int cgs = 0;
        int total = dna.length();
        for (char c : dna.toCharArray()) {
            if ((c == 'c')|| (c == 'g')) {
                cgs = cgs + 1;
            }
        }
        /*
           int countcg = 0;
           int start = 0;
           while (true) {
                int pos = dna.indexOf("c", start);
                if (pos == -1) {
                    break;
                }
                countcg += 1;
                start = pos + 1;
            }
         */
        return (float)cgs/total;
        }
    public void printGenes(StorageResource sr){
        int num1 = 0;
        int num2 = 0;
        StorageResource genesLongerThan60 = new StorageResource();
        StorageResource genesCGRatioHigherThanPoint35 = new StorageResource();
        for (String genes : sr.data()){
            if(genes.length() > 60){
                num1 = num1 + 1;
                genesLongerThan60.add(genes);
            }
           
            if(cgRatio(genes) > 0.35) {
                num2 = num2 + 1;
                genesCGRatioHigherThanPoint35.add(genes);
            }
            
        }
        System.out.println("TotalNumber of Genes "+ sr.size());
        System.out.println("All the Strings that are longer than 60 nucleotides are " );
        for(String gene: genesLongerThan60.data()){
            System.out.println(gene);
        }
        System.out.println("Number of Strings that are longer than 60 nucleotides "+ num1);
        System.out.println("Strings whose cgRatio is higher than 0.35 are ");
        for(String gene:genesCGRatioHigherThanPoint35.data()){
            System.out.println(gene);
        }
        System.out.println("Number of Strings whose C-G ratio is higher than .35 "+ num2);
    }
    
    public static void main(String[] args) {
        FindAndStoreAllGenes f = new FindAndStoreAllGenes();
        f.testStorageFinder();
    }
    

}
