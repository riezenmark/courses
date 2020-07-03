package calc;

import java.util.Scanner;

public class InteractCalc {
    private double lastResult = 0;
    private String lastOperation = null;
    private final Scanner scanner = new Scanner(System.in);
    private final Calculator calculator;

    public InteractCalc(Calculator calculator) {
        this.calculator = calculator;
    }

    private double askNumber() {
        String s;
        do {
            System.out.print("Input number: ");
            s = scanner.nextLine();
        } while (!s.matches("(-*\\d+[.]*\\d*)|(^\\s*$)"));
        return s.matches("(-*\\d+[.]*\\d*)") ? Double.parseDouble(s) : lastResult;
    }

    private String askOperation() {
        String s;
        do {
            System.out.print("Input operation: ");
            s = scanner.nextLine();
        } while (!s.matches(calculator.getOperationRegex() + "|(^\\s*$)|^Exit$") && lastOperation != null);
        return s.matches(calculator.getOperationRegex() + "|^Exit$") ? s : lastOperation;
    }

    public boolean operate() {
        double left = askNumber();
        String op;
        do {
            op = askOperation();
        } while (op == null);
        lastOperation = op;
        double right = askNumber();
        if (!op.equals("Exit")) {
            lastResult = calculator.operations.get(op).execute(left, right);
            printResult(left, right, lastOperation, lastResult);
            return false;
        } else {
            return true;
        }
    }

    public void start() {
        boolean exit;
        do {
            exit = operate();
        } while (!exit);
    }

    public void printResult(double left, double right, String operation, double result) {
        System.out.println(left + " " + operation + " " + right + " = " + result);
    }
}

