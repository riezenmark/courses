package loop;

public class Counter {
    public static int add(int start, int finish) {
        int sum = 0;
        if (start % 2 == 1) {
            start++;
        }
        for (; start <= finish; start += 2) {
            sum += start;
        }
        return sum;
    }
}
