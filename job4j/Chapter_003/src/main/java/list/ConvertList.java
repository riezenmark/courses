package list;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConvertList {
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = list.size() / rows;
        if (list.size() % rows > 0) {
            cells++;
        }
        int[][] array = new int[rows][cells];
        int position = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cells; j++) {
                if (position < list.size()) {
                    array[i][j] = list.get(position);
                    position++;
                } else {
                    array[i][j] = 0;
                }
            }
        }
        return array;
    }

    public static List<Integer> convert(List<int[]> list) {
        return list.stream().flatMapToInt(Arrays::stream).boxed().collect(Collectors.toList());
    }
}
