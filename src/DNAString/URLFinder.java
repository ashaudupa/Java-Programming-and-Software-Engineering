package DNAString;

import edu.duke.StorageResource;
import edu.duke.URLResource;

public class URLFinder {
    public StorageResource findUrls(String url){
        URLResource page = new URLResource(url);
        String source = page.asString();
        StorageResource store = new StorageResource();
        int start = 0;
        while(true) {
            int index = source.indexOf("href=", start);
            if(index == -1){
                break;
            }
            int firstQuote = index + 6;//after "href="
            int endQuote = source.indexOf("\"",firstQuote);
            String sub = source.substring(firstQuote,endQuote);
            if(sub.startsWith("http")){
                //System.out.println(sub);
                store.add(sub);
            }
            start = endQuote + 1;
            
        }
        return store;
    }
    public void testURL() {
        StorageResource s1 = findUrls("https://whitehouse.gov");
        StorageResource s2 = findUrls("http://doctorswithoutborders.org");
        System.out.println("size1=" + s1.size());
        System.out.println("size2="+ s2.size());
        for(String link: s2.data()) {
            System.out.println(link);
        }
    }
    public static void main(String[] args){
        URLFinder url = new URLFinder();
        url.testURL();
    }

}

/*
 * FileResource fr = new FileResource("/data/confucius.txt");
 * StorageResource unique = new StrorageResource();
 * for(String word: fr.words) {
 *    if(! unique.contains(word)){
 *    unique.add(word);
 *    }
 *    }
 *    
 */
