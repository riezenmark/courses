package list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedList<E> implements Iterable<E> {

    private int modCount = 0;
    private int size = 0;
    private Node<E> first;
    private Node<E> last;

    public void add(E data) {
        modCount++;
        Node<E> newNode = new Node<>(data);
        if (first == null) {
            newNode.next = null;
            newNode.prev = null;
            first = newNode;
        } else {
            last.next = newNode;
            newNode.prev = last;
        }
        last = newNode;
        size++;
    }

    public E delete() {
        modCount++;
        if (size != 0) {
            E data = last.data;
            last = last.prev;
            size--;
            return data;
        } else {
            return null;
        }
    }

    public E get(int index) {
        if (index >= 0 && index < size) {
            Node<E> result = this.first;
            for (int i = 0; i < index; i++) {
                result = result.next;
            }
            return result.data;
        }
        return null;
    }

    public int size() {
        return this.size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;

            private Node<E> cursor = first;

            @Override
            public boolean hasNext() {
                return cursor != null;
            }

            @Override
            public E next() throws NoSuchElementException {
                checkForModification();
                if (hasNext()) {
                    E data = cursor.data;
                    cursor = cursor.next;
                    return data;
                } else {
                    throw (new NoSuchElementException());
                }
            }

            private void checkForModification() throws ConcurrentModificationException {
                if (modCount != expectedModCount) {
                    throw (new ConcurrentModificationException());
                }
            }
        };
    }

    private static class Node<E> {

        E data;
        Node<E> next;
        Node<E> prev;

        Node(E data) {
            this.data = data;
        }
    }

}
