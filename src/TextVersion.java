/**
 * Created by User on 23.12.2019.
 */
import java.io.*;
import java.util.Vector;

public class TextVersion implements Serializable {
    private Vector<Adapter> text;
    private FlyweightFactory factory = new FlyweightFactory();

    TextVersion() {
        text = new Vector<>();
    }

    TextVersion(Vector<Adapter> text, FlyweightFactory flyweightFactory) {
        this.text = text;
        this.factory = flyweightFactory;
    }

    boolean setText(File file) {
        FileInputStream inFile;
        String txt = "";
        try {
            inFile = new FileInputStream(file);
            byte[] str = new byte[inFile.available()];
            inFile.read(str);
            txt = new String(str);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        text.clear();
        factory = new FlyweightFactory();
        return setText(txt);
    }

    boolean setText(String str) {
        if (str == null) {
            return false;
        }
        text.clear();
        factory = new FlyweightFactory();
        String[] words = str.split("\\s");
        // Сразу отделим слова от прилипших знаков пунктуации
        for(String subStr:words) {
            if(subStr.indexOf('.') != -1) {
                text.add(factory.addWord(subStr.substring(0, subStr.length() - 1)));
                text.add(factory.addWord("."));
            }
            else if(subStr.indexOf(',') != -1) {
                text.add(factory.addWord(subStr.substring(0, subStr.length() - 1)));
                text.add(factory.addWord(","));
            }
            else {
                text.add(factory.addWord(subStr));
            }
        }
        return true;
    }

    boolean addWord(String str) {
        if(str == null || str.indexOf(' ') != -1) {
            return false;
        }
        text.add(factory.addWord(str));
        return true;
    }

    boolean addWord(String str, int position) {
        if (position >= text.size() || position < 0) {
            return addWord(str);
        }
        text.add(position, (factory.addWord(str)));
        return true;
    }

    boolean removeWord(int position) {
        if (position >= text.size() || position < 0) {
            return false;
        }
        factory.removeWord(text.get(position).getWord());
        text.remove(position);
        return true;
    }

    boolean editWord(int position, String newWord) {
        if(position >= text.size() || position < 0) {
            return false;
        }
        return removeWord(position) && addWord(newWord, position);
    }

    StringBuilder getText() {
        StringBuilder txt = new StringBuilder();
        for(int i = 0; i < text.size(); ++i) {
            if(i < text.size() - 1) {
                if(text.get(i + 1).getWord() == "," || text.get(i + 1).getWord() == "."){
                    txt.append(text.get(i).getWord());
                }
                else {
                    txt.append(text.get(i).getWord());
                    txt.append(" ");
                }
            }
            else {
                txt.append(text.get(i).getWord());
                txt.append(" ");
            }
        }
        return txt;
    }

    void printDictionary() {
        factory.printDictionary();
    }

    TextVersion makeCopy() {
        return new TextVersion((Vector<Adapter>)this.text.clone(), new FlyweightFactory(factory.makeCopy()));
    }

    void serialize(String path){
        try {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    int getSize(){
        return factory.getHashSize();
    }

    int getDictionarySize(){
        return text.size();
    }
}

