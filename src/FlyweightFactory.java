/**
 * Created by User on 23.12.2019.
 */
import java.io.Serializable;
import java.util.HashMap;

public class FlyweightFactory implements Serializable {
    private HashMap<Integer, Adapter> adapterHashMap;

    FlyweightFactory() {
        adapterHashMap = new HashMap<>();
    }

    FlyweightFactory(HashMap<Integer, Adapter> adapterHashMap) {
        this.adapterHashMap = adapterHashMap;

    }

    Adapter addWord(String word) {
        int hashWord = word.hashCode();
        Adapter adapter = adapterHashMap.get(hashWord);
        if (adapter != null) {
            adapter.increaseCount();
        } else {
            adapter = new Adapter(word);
            adapterHashMap.put(hashWord, adapter);
        }
        return adapter;
    }

    boolean removeWord(String word) {
        int hashWord = word.hashCode();
        if (!adapterHashMap.containsKey(hashWord)) {
            return false;
        }
        Adapter adapter = adapterHashMap.get(hashWord);
        if (adapter.getCount() == 1) {
            adapterHashMap.remove(hashWord);
        } else {
            adapter.decreaseCount();
        }
        return true;
    }

    void printDictionary() {
        adapterHashMap.entrySet().forEach(entry -> {
            entry.getValue().print();
        });
    }

    HashMap<Integer, Adapter> makeCopy() {
        return (HashMap<Integer, Adapter>) adapterHashMap.clone();
    }

    int getHashSize(){
        return adapterHashMap.size();
    }
}
