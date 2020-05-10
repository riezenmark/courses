package array;

public class SortSelected {
    public static int[] sort(int[] data) {
        for (int current = 0; current < data.length; current++) {
            int min = MinDiapason.findMin(data, current, data.length - 1);
            int index = FindLoop.indexOf(data, min, current, data.length - 1);
            int tmp = data[current];
            data[current] = data[index];
            data[index] = tmp;
        }
        return data;
    }
}
