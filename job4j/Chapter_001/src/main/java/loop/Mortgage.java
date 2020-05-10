package loop;

public class Mortgage {
    public int year(int amount, int salary, double percent) {
        int year = 0;
        double leftover = amount * (percent / 100 + 1);
        while (leftover > 0) {
            leftover = (leftover - salary) * (percent / 100 + 1);
            year++;
        }
        return year;
    }
}
