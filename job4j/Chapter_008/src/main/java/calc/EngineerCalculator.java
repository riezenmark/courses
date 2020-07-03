package calc;

public class EngineerCalculator extends Calculator {

    public EngineerCalculator() {
        operationRegex = "^[*^+/-]$";
        operations.put("^", new Pow());
    }

    public static class Pow implements Operation {
        @Override
        public double execute(double left, double right) {
            return Math.pow(left, right);
        }
    }

}
