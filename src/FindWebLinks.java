
import edu.duke.*;
public class FindWebLinks {
    public void findUrllinks() {
        URLResource links = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for ( String word : links.words()) {
            //System.out.println(word);
            String itemLower = word.toLowerCase();
            if (itemLower .contains("youtube.com")) {
                //System.out.println(word);
                int pos = itemLower.indexOf("youtube.com");
                if ( pos != -1) {
                    int start = word.lastIndexOf("\"" , pos);
                    int end = word.indexOf("\"" , pos+1);
                    System.out.println(word.substring(start+1, end));
                }
                
                
            }
        }
    }
    public static void main(String[] args) {
        FindWebLinks fw = new FindWebLinks();
        fw.findUrllinks();
        
    }

}
