package tree;

import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

public class SimpleTree<E extends Comparable<E>> implements Iterable<E> {
    private final Leaf<E> root;
    private final List<E> list;
    private int size = 0;
    private boolean empty = true;

    public SimpleTree() {
        list = new LinkedList<>();
        root = new Leaf<>(null);
    }

    public boolean add(E element) {
       if (empty) {
           return initiateRoot(element);
       }
       Leaf<E> newNode = new Leaf<>(element);
       Leaf<E> lastNode = findLastLeaf(root, newNode);
       if (lastNode == null) {
           return false;
       }
       size++;
       if (empty) {
           empty = false;
       }
       newNode.parent = lastNode;
       if (lastNode.compareTo(newNode) < 0) {
           lastNode.right = newNode;
       } else {
           lastNode.left = newNode;
       }
       return true;
    }

    private Leaf<E> findLastLeaf(final Leaf<E> oldLeaf, final Leaf<E> newLeaf) {
        Leaf<E> lastLeaf = oldLeaf;
        int compare = oldLeaf.compareTo(newLeaf);
        if (compare < 0 && oldLeaf.right != null) {
            lastLeaf = findLastLeaf(oldLeaf.right, newLeaf);
            return lastLeaf;
        }
        if (compare > 0 && oldLeaf.left != null) {
            lastLeaf = findLastLeaf(oldLeaf.left, newLeaf);
            return lastLeaf;
        }
        if (compare == 0) {
            return null;
        }
        return lastLeaf;
    }

    private boolean initiateRoot(final E element) {
        root.element = element;
        size++;
        empty = false;
        return true;
    }

    public List<E> get() {
        for (E e : this) {
            list.add(e);
        }
        return list;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return empty;
    }

    public Leaf<E> find(E e) {
        Leaf<E> eLeaf = new Leaf<>(e);
        return search(root, eLeaf);
    }

    private Leaf<E> search(Leaf<E> leaf, Leaf<E> eLeaf) {
        int compare = leaf.compareTo(eLeaf);
        if (compare < 0 && leaf.right != null) {
            return search(leaf.right, eLeaf);
        }
        if (compare > 0 && leaf.left != null) {
            return search(leaf.left, eLeaf);
        }
        if (compare == 0) {
            return eLeaf;
        }
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            final Iterator<Leaf<E>> iterator = new TreeIterator<>(root);

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public E next() {
                return iterator.next().element;
            }
        };
    }

    private static class TreeIterator<E> implements Iterator<Leaf<E>> {
        private Leaf<E> next;

        private TreeIterator(Leaf<E> root) {
            next = root;
            goToTheMostLeft();
        }

        private void goToTheMostLeft() {
            while (next.left != null) {
                next = next.left;
            }
        }

        @Override
        public boolean hasNext() {
            return next != null && next.element != null;
        }

        @Override
        public Leaf<E> next() {
            Leaf<E> r = next;
            if (next.right != null) {
                return goRight(r);
            }
            return goUp(r);
        }

        private Leaf<E> goRight(Leaf<E> r) {
            next = next.right;
            while (next.left != null) {
                next = next.left;
            }
            return r;
        }

        private Leaf<E> goUp(Leaf<E> r) {
            while (true) {
                if (next.parent == null) {
                    next = null;
                    return r;
                }
                if (next.parent.left == next) {
                    next = next.parent;
                    return r;
                }
                next = next.parent;
            }
        }
    }

    static class Leaf<E> implements Comparable<E> {
        private Leaf<E> parent;
        private Leaf<E> right;
        private Leaf<E> left;
        private E element;

        private Leaf(E element) {
            this.element = element;
        }

        public E getElement() {
            return element;
        }

        @SuppressWarnings("unchecked")
        @Override
        public int compareTo(Object obj) {
            Leaf<E> node = (Leaf<E>) obj;
            return this.hashCode() - node.hashCode();
        }

        @Override
        public int hashCode() {
            int hash = 31;
            hash = hash * 17 + element.hashCode();
            return hash;
        }
    }

}
