package list;

public class SimpleStack<T> extends SimpleLinkedList<T> {

    public T pop() {
        return super.delete();
    }

    public void push(T data) {
        super.add(data);
    }

}
