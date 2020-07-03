package calc;

import java.util.Scanner;

public class InteractCalc {
    private double lastResult = 0;
    private String lastOperation = null;
    private final Scanner scanner = new Scanner(System.in);
    private final Calculator calculator = new Calculator();

    private double askNumber() {
        String s;
        do {
            System.out.print("Input number: ");
            s = scanner.nextLine();
        } while (!s.matches("(\\d*)|(^\\s*$)"));
        return s.matches("(\\d+)") ? Double.parseDouble(s) : lastResult;
    }

    private String askOperation() {
        String s;
        do {
            System.out.print("Input operation: ");
            s = scanner.nextLine();
        } while (!s.matches("^[*+/-]$|(^\\s*$)|^Exit$") && lastOperation != null);
        return s.matches("^[*+/-]$|^Exit$") ? s : lastOperation;
    }

    public void start() {
        String op;
        double left;
        double right;
        do {
            left = askNumber();
            do {
                op = askOperation();
            } while (op == null);
            lastOperation = op;
            right = askNumber();
            switch (op) {
                case "+":
                    lastResult = calculator.add(left, right);
                    break;
                case "-":
                    lastResult = calculator.subtract(left, right);
                    break;
                case "/":
                    lastResult = calculator.divide(left, right);
                    break;
                case "*":
                    lastResult = calculator.multiply(left, right);
                    break;
            }
            if (!op.equals("Exit")) {
                printResult(left, right, lastOperation, lastResult);
            } else {
                break;
            }
        } while (true);
    }

    public void printResult(double left, double right, String operation, double result) {
        System.out.println(left + " " + operation + " " + right + " = " + result);
    }

    public static void main(String[] args) {
        InteractCalc calc = new InteractCalc();
        calc.start();
    }
}

