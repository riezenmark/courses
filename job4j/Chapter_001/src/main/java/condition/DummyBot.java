package condition;

public class DummyBot {

    public static String answer(String question) {
        String rsl = "Это ставит меня в тупик. Задайте другой вопрос.";
        if ("Привет, Бот.".equals(question)) {  //it is impossible to
            rsl = "Привет, умник.";             //compare String objects
        } else if ("Пока".equals(question)) {   //using == operator.
            rsl = "До скорой встречи.";
        }
        return rsl;
    }
}
