/**
 * Created by User on 23.12.2019.
 */
import java.io.Serializable;

public class Adapter implements Serializable {
    private String word;
    private int count;

    Adapter(String word) {
        this.word = word;
        count = 1;
    }

    void increaseCount() {
        this.count++;
    }

    void decreaseCount() {
        this.count--;
    }

    String getWord() {
        return this.word;
    }

    int getCount() {
        return this.count;
    }

    void print() {
        System.out.println("word: " + this.word + " count: " + this.count);
    }
}
