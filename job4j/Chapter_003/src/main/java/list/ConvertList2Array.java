package list;

import java.util.List;

public class ConvertList2Array {
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
}
