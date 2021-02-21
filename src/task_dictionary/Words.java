
package task_dictionary;

import java.util.ArrayList;


public class Words {
     private String word;
    private ArrayList<String> translations;
    private int count = 0;
    
    public Words() {
    }

    public Words(String word) {
        this.word = word;
    }

    public Words(String word, ArrayList<String> translations) {
        this.word = word;
        this.translations = translations;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public ArrayList<String> getTranslations() {
        return translations;
    }

    public void setTranslations(ArrayList<String> translations) {
        this.translations = translations;
    }
    
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
