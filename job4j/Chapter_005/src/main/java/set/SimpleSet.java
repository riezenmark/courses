package set;

import list.SimpleArrayList;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {

    SimpleArrayList<E> container = new SimpleArrayList<>();

    public void add(E element) {
        if (!hasDuplicates(element)) {
            int index = 0;
            for (E e : container) {
                if (element.hashCode() > e.hashCode()) {
                    index++;
                } else {
                    break;
                }
            }
            container.add(index, element);
        }
    }

    public int size() {
        return container.size();
    }

    public boolean isEmpty() {
        return container.isEmpty();
    }

    private boolean hasDuplicates(E element) {
        boolean result = false;
        for (E el: container) {
            if (el.equals(element)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return container.iterator();
    }
}
