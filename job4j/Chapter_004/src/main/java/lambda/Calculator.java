package lambda;

import java.util.function.BiFunction;
import java.util.function.Consumer;

public class Calculator {

    public interface Operation {
        double calculation(int left, int right);
    }

    public void calculate(int start, int finish, int value, Operation op) {
        for (int index = start; index != finish; index++) {
            System.out.println(
                    op.calculation(value, index)
            );
        }
    }

    public void calculate(int start, int finish, int value,
                          BiFunction<Integer, Integer, Double> op,
                          Consumer<Double> media) {
        for (int index = start; index != finish; index++) {
            media.accept(op.apply(value, index));
        }
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.calculate(0, 10, 2, new Operation() {
            @Override
            public double calculation(int left, int right) {
                return left * right;
            }
        });

        calc.calculate(
                0, 10, 2,
                (value, index) -> value * index
        );

        calc.calculate(
                0, 10, 2,
                (value, index) -> {
                    int result = value * index;
                    System.out.printf("Multiple %s * %s = %s %n", value, index, result);
                    return result;
                }
        );

        calc.calculate(
                0, 10, 2,
                (value, index) -> {
                    double result = value * index;
                    System.out.printf("Multiple %s * %s = %s %n", value, index, result);
                    return result;
                },
                result -> System.out.println(result)
        );

        calc.calculate(
                0, 10, 2,
                MathUtil::add,
                result -> System.out.println(result)
        );
    }


}
