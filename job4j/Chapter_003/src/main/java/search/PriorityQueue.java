package search;

import java.util.LinkedList;

public class PriorityQueue {
    private final LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определяется по полю 'приоритет'.
     * Для вставки использовать add(int index, E value)
     * @param task Задача
     */
    public void put(Task task) {
        int position = 0;
        for (var t : tasks) {
            if (task.getPriority() > t.getPriority()) {
                position++;
            }
        }
        tasks.add(position, task);
    }

    public Task take() {
        return this.tasks.poll();
    }
}
