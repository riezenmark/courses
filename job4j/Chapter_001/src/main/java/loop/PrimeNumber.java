package loop;

public class PrimeNumber {
    public int calc(int finish) {
        int count = 0;
        for (int currentNumber = 2; currentNumber <= finish; currentNumber++) {
            int dividers = 0;
            for (int j = 1; j <= currentNumber; j++) {
                if (currentNumber % j == 0) {
                    dividers++;
                }
            }
            if (dividers == 2) {
                count++;
            }
        }
        return count;
    }
}
