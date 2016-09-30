package DNAString;
import java.lang.Math;
public class FindAllGenes {
    public void printAll(String dna) {
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
            System.out.println(dna.substring(loc,stop+3));
            
            start = stop +3;
            
        }
    }
    
    /*
     * find a valid stop codon in a dna that occurs after index
     * in no valid stop codons found ,return dna.length.
     * @params dna is string being searched 
     * @params index is index where search starts
     * @returns index of beginning of a valid stop codon
     *or dna.length if no valid codon
     */
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
    
    public void testFinder(){
        String dna1 = "ccatgccctaataaatgtctgtaatgtaga";
        String dna2 = "CATGTAATAGATGAATGACTGATAGATATGCTTGTATGCTATGAAAATGTGAAATGACCCA";
        
        System.out.println("DNA String is " + dna1);
        System.out.println("Genes found are" );
        printAll(dna1);
        System.out.println("DNA String is " + dna2);
        System.out.println("Genes found are" );
        printAll(dna2);
        
    }
    public static void main(String[] args) {
        FindAllGenes f = new FindAllGenes();
        f.testFinder();
    }
    

}
