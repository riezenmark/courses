package array;

public class Square {
    public int[] calculate(int bound) {
        int[] rst = new int[bound];
        for (int i = 0; i < bound;) {
            rst[i] = (int) Math.pow(++i, 2);
        }
        return rst;
    }
}
