package list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedList<E> implements Iterable<E> {

    private int modCount = 0;
    private int size = 0;
    private Node<E> first;
    private Node<E> last;
    private boolean empty = true;

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
        if (empty) {
            empty = false;
        }
    }

    public void clear() {
        modCount++;
        last = null;
        first = null;
        size = 0;
        empty = true;
    }

    public E delete() {
        if (!empty) {
            modCount++;
            E data = last.data;
            last = last.prev;
            size--;
            if (size == 0) {
                empty = true;
            }
            return data;
        } else {
            return null;
        }
    }

    public E delete(int index) {
        E result = null;
        if (!empty && index < size && index >= 0) {
            modCount++;
            if (index == 0) {
                result = first.data;
                first = first.next;
            } else if (index < size - 1) {
                Node<E> node = getNode(index);
                result = node.data;
                node.prev.next = node.next;
                node.next.prev = node.prev;
            } else if (index == size - 1) {
                result = delete();
            }
            size--;
            if (size == 0) {
                empty = true;
            }
        }
        return result;
    }

    public E get(int index) {
        Node<E> node = getNode(index);
        if (node != null) {
            return node.data;
        }
        return null;
    }

    private Node<E> getNode(int index) {
        if (!empty && index >= 0 && index < size) {
            Node<E> result = this.first;
            for (int i = 0; i < index; i++) {
                result = result.next;
            }
            return result;
        }
        return null;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.empty;
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
