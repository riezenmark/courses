package map;

import list.SimpleLinkedList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Iterable<V> {

    private Node<K, V>[] hashTable;
    private int size = 0;
    private boolean empty = true;
    private float threshold;
    private int modCount = 0;

    @SuppressWarnings("unchecked")
    public SimpleMap() {
        this.hashTable = new Node[16];
        this.threshold = hashTable.length * 0.75f;
    }

    public boolean put(final K key, final V value) {
        modCount++;
        if (size + 1 >= threshold) {
            threshold *= 2;
            grow();
        }
        Node<K, V> newNode = new Node<>(key, value);
        int index = hash(key);
        if (hashTable[index] == null) {
            return simpleAdd(index, newNode);
        }
        SimpleLinkedList<Node<K, V>> list = hashTable[index].getNodes();
        for (Node<K, V> node : list) {
            if (keyExistButNewValue(node, newNode) || collisionProcessing(node, newNode, list)) {
                return true;
            }
        }
        return false;
    }

    private boolean keyExistButNewValue(
            final Node<K, V> nodeFromList,
            final Node<K, V> newNode
    ) {
        if (
                newNode.getKey().equals(nodeFromList.getKey())
                && !newNode.getValue().equals(nodeFromList.getValue())
        ) {
            nodeFromList.setValue(newNode.getValue());
            return true;
        }
        return false;
    }

    private boolean collisionProcessing(
            final Node<K, V> nodeFromList,
            final Node<K, V> newNode,
            final SimpleLinkedList<Node<K, V>> list
    ) {
        if (
                newNode.hashCode() == nodeFromList.hashCode()
                && !newNode.key.equals(nodeFromList.key)
                && !newNode.getValue().equals(nodeFromList.getValue())
        ) {
            list.add(newNode);
            size++;
            if (empty) {
                empty = false;
            }
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private void grow() {
        Node<K, V>[] oldHashTable = hashTable;
        hashTable = new Node[oldHashTable.length * 2];
        size = 0;
        for (Node<K, V> node : oldHashTable) {
            if (node != null) {
                for (Node<K, V> n : node.getNodes()) {
                    put(n.key, n.value);
                }
            }
        }
    }

    private boolean simpleAdd(int index, Node<K, V> node) {
        hashTable[index] = new Node<>(null, null);
        hashTable[index].getNodes().add(node);
        size++;
        if (empty) {
            empty = false;
        }
        return true;
    }

    public V get(final K key) {
        int index = hash(key);
        if (index < hashTable.length && hashTable[index] != null) {
            SimpleLinkedList<Node<K, V>> list = hashTable[index].getNodes();
            for (Node<K, V> node : list) {
                if (key.equals(node.getKey())) {
                    return node.getValue();
                }
            }
        }
        return null;
    }

    public boolean delete(final K key) {
        int index = hash(key);
        if (hashTable[index] == null) {
            return false;
        }
        if (hashTable[index].getNodes().size() == 1) {
            hashTable[index].getNodes().delete(0);
            modCount++;
            size--;
            if (size == 0) {
                empty = true;
            }
            return true;
        }
        SimpleLinkedList<Node<K, V>> nodeList = hashTable[index].getNodes();
        for (Node<K, V> node : nodeList) {
            if (key.equals(node.getKey())) {
                nodeList.delete(node);
                modCount++;
                size--;
                if (size == 0) {
                    empty = true;
                }
                return true;
            }
        }
        return false;
    }

    private int hash(final K key) {
        int hash = 31;
        hash = hash * 17 + key.hashCode();
        return hash % hashTable.length;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return empty;
    }

    public Iterator<V> iterator() {
        return new Iterator<>() {
            private int arrayCounter = -1;
            private int valueCounter;
            Iterator<Node<K, V>> subIterator = null;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (valueCounter == size) {
                    return false;
                }
                if (subIterator == null || !subIterator.hasNext()) {
                    if (moveToNextCell()) {
                        subIterator = hashTable[arrayCounter].getNodes().iterator();
                    } else {
                        return false;
                    }
                }
                return subIterator.hasNext();
            }

            private boolean moveToNextCell() {
                do {
                    arrayCounter++;
                } while (arrayCounter < hashTable.length && hashTable[arrayCounter] == null);
                return arrayCounter < hashTable.length && hashTable[arrayCounter] != null;
            }

            @Override
            public V next() {
                if (expectedModCount == modCount) {
                    if (hasNext()) {
                        valueCounter++;
                        return subIterator.next().getValue();
                    } else {
                        throw (new NoSuchElementException());
                    }
                } else {
                    throw (new ConcurrentModificationException());
                }
            }
        };
    }

    private static class Node<K, V> {
        private final SimpleLinkedList<Node<K, V>> nodes;
        private int hash;
        private final K key;
        private V value;

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
            nodes = new SimpleLinkedList<>();
        }

        private SimpleLinkedList<Node<K, V>> getNodes() {
            return nodes;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node<K, V> node = (Node<K, V>) o;
            return (
                    key.equals(node.getKey())
                    && value.equals(node.getValue())
                    && hash == node.hashCode()
            );
        }

        @Override
        public int hashCode() {
            hash = 31;
            hash = hash * 17 + key.hashCode();
            return hash;
        }
    }
}
