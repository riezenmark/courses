package oop;

public class DummyDic {
    public String engToRus(String eng) {
        String translate = "Неизвестное слово - " + eng;
        return translate;
    }

    public static void main(String[] args) {
        DummyDic dictionary = new DummyDic();
        String word = dictionary.engToRus("Sun");
        System.out.println(word);
    }
}
