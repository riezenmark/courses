package iterator;

import java.util.ArrayList;
import java.util.Iterator;

public class IteratorsConvert {

    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        ArrayList<Integer> result = new ArrayList<>();
        it.forEachRemaining(integerIterator -> integerIterator.forEachRemaining(result::add));
        return result.iterator();
    }

}
