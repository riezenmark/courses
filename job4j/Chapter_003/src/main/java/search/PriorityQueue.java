package search;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определяется по полю 'приоритет'.
     * Для вставки использовать add(int index, E value)
     * @param task Задача
     */
    public void put(Task task) {
        int position = 0;
        for (Task t : tasks) {
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
