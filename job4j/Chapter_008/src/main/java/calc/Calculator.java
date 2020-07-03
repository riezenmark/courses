package calc;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class Calculator {
    protected String operationRegex = "^[*+/-]$";
    protected HashMap<String, Operation> operations = new HashMap<>(
            Map.ofEntries(
                    new AbstractMap.SimpleEntry<String, Operation>("+", new Addition()),
                    new AbstractMap.SimpleEntry<String, Operation>("-", new Subtraction()),
                    new AbstractMap.SimpleEntry<String, Operation>("*", new Multiplication()),
                    new AbstractMap.SimpleEntry<String, Operation>("/", new Division())
            )
    );

    public String getOperationRegex() {
        return operationRegex;
    }

    public static class Addition implements Operation {
        @Override
        public double execute(double left, double right) {
            return left + right;
        }
    }

    public static class Subtraction implements Operation {
        @Override
        public double execute(double left, double right) {
            return left - right;
        }
    }

    public static class Multiplication implements Operation {
        @Override
        public double execute(double left, double right) {
            return left * right;
        }
    }

    public static class Division implements Operation {
        @Override
        public double execute(double left, double right) {
            return left / right;
        }
    }
}
