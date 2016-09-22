import java.io.File;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;


public class TagFinder {
    public String findProtein(String dna) {
        dna = dna.toLowerCase();
        //System.out.println(dna);
        int start = dna.indexOf("atg");
        if ( start == -1) { //start codon is not found
            return "";
        }
        //int stop = dna.indexOf("tag" , start+3);
        if((dna.indexOf("tag" , start+3) - start) % 3 == 0 ) {
            return dna.substring(start , dna.indexOf("tag" , start+3) + 3);
        }
        else if((dna.indexOf("tga" , start+3) - start) % 3 == 0) {
            return dna.substring(start,dna.indexOf("tga" , start+3) + 3) ;
        }
        else if ((dna.indexOf("taa" , start+3) - start) % 3 == 0) {
            return dna.substring(start , dna.indexOf("taa" , start+3) + 3);
        }
        else {
            return "" ;
        }
        
    }
    
    public String findStopCondon(String gene) {
        
        return gene.substring(gene.length()-3);
    }
    
    public void testing() {
        String a = "cccatggggttatga";
        String ap = "atggggttatga";
        String b = "ATGCCCTAG";
        String bp = "ATGCCCTAG";
        String c = "cccatggggttataa";
        String cp = "atggggttataa";
        String result1 = findProtein(a);
        if(ap.equals(result1)) {
            System.out.println("success for " + ap +" Length " +ap.length());
            if (result1 != ""){
                System.out.println ("stop condon is " + findStopCondon(result1));
            }
        }
        else {
            System.out.println("mistake for input:" +a);
            System.out.println("got: " +result1);
            System.out.println("not " +ap);
        }
        String result2 = findProtein(b);
        if(bp.equals(result2.toUpperCase())) {
            System.out.println("success for " + bp +" Length " +bp.length());
            if (result2 != ""){
                System.out.println ("stop condon is " + findStopCondon(result2));
            }
        }
        else {
            System.out.println("mistake for input:" +b);
            System.out.println("got: " +result2);
            System.out.println("not " +bp);
        }
        String result3 = findProtein(c);
        if(bp.equals(result3)) {
            System.out.println("success for " + cp +" Length " +cp.length());
            if (result3 != ""){
                System.out.println ("stop condon is " + findStopCondon(result3));
            }
        }
        else {
            System.out.println("mistake for input:" +c);
            System.out.println("got: " +result3);
            System.out.println("not " +cp);
        }
    }
    
    public void realTesting() {
        DirectoryResource dr = new DirectoryResource();
        for (File file : dr.selectedFiles()) {
            FileResource fr = new FileResource(file);
            String s = fr.asString();
            System.out.println("read " + s.length()+ "characters");
            String result = findProtein(s);
            System.out.println("found" + result);
        }
    }
    
    public static void main(String[] args){
        TagFinder t = new TagFinder();
        t.testing();
    }
    
    

}
