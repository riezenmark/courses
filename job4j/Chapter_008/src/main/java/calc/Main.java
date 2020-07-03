package calc;

public class Main {
    public static void main(String[] args) {
        InteractCalc calc = new InteractCalc(new EngineerCalculator());
        calc.start();
    }
}
