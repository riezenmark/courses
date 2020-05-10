package loop;

/**
 * Works with prime numbers.
 *
 * @author riezenmark (riezenmark@gmail.com)
 */
public class PrimeNumber {

    /**
     * Counts amount of prime numbers up to parameter.
     * @param finish Number until which counting is kept.
     * @return Amount of prime numbers.
     */
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
