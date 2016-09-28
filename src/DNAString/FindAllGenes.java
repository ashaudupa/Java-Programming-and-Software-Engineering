package DNAString;

public class FindAllGenes {
    public void printAllStarts(String dna) {
        int start = 0;
        while(true) {
            int loc = dna.indexOf("atg",start);
            if(loc == -1) {
                break;
            }
            System.out.println("starts at " + loc);
            
            start = loc +3;
            
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
        
        int stop1 = dna.indexOf("tga",index);
        if(stop1 == -1 || (stop1-index) % 3 != 0) {
            stop1 = dna.length();
        }
        
        int stop2 = dna.indexOf("taa",index);
        if(stop2 == -1 || (stop2 - index) % 3 != 0) {
            stop2 = dna.length();
        }
        
        int stop3 = dna.indexOf("tag", index);
        if( stop3 == -1 || (stop3 - index) % 3 != 0) {
            stop3 = dna.length();
        }
        
        return Math.min(stop1, Math.min(stop2, stop3));
    }

}
