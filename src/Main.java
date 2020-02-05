import java.io.File;

public class Main{

    public static void main(String[] args) {
        TextVersion v1 = new TextVersion();

        // Чтение сказки из файла
        v1.setText(new File("in.txt"));
        v1.printDictionary();
        // Копирование версии текста
        TextVersion v2 = v1.makeCopy();
        // Добавление текста в конец
        v1.addWord("Конец");

        // Удаление текста (удалим точку в первом предложении)
        v1.removeWord(3);
        // Вставка текста (вставим запятую на место точки)
        v1.addWord(",", 3);
        // Изменение текста (изменим "Выросла" на "выросла")
        v1.editWord(4, "выросла");
        // Сериализуем версию текста в файл
        v1.serialize("out.txt");

        System.out.println(v2.getText());
        double coef =  ((double)(v1.getDictionarySize())/(double)(v1.getSize()));
        System.out.println(coef);
        System.out.println(v1.getText());
        coef =  ((double)(v1.getDictionarySize() + v2.getDictionarySize())/(double)(v1.getSize()));
        System.out.println(coef);
        v1.addWord("JAVA", 10);
        v1.addWord("C++", 3);
        v1.addWord("PYTHON", 32);
        v1.addWord("PHP", 6);
        System.out.println(v1.getText());
        coef =  ((double)(v1.getDictionarySize() + v2.getDictionarySize())/(double)(v1.getSize()));
        System.out.println(coef);
    }
}
