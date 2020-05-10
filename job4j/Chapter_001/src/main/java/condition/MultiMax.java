package condition;

public class MultiMax {
    public int max(int first, int second, int third) {
        int result = first;
        if (second > result) {
            result = second;
        }
        if (third > result) {
            result = third;
        }
        return result;
    }
}
