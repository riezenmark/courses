package iterator;

import java.util.Iterator;

public class MatrixIterator implements Iterator {
    private final int[][] ints;
    private int outerPosition = 0;
    private int innerPosition = 0;
    private int currentPosition = 0;

    public MatrixIterator(final int[][] ints) {
        this.ints = ints;
    }

    @Override
    public boolean hasNext() {
        return this.ints.length >= currentPosition;
    }

    @Override
    public Object next() {
        currentPosition++;
        if (ints[outerPosition].length == innerPosition) {
            outerPosition++;
            innerPosition = 0;
        }
        return this.ints[outerPosition][innerPosition++];
    }
}
