package yuki;

import java.util.ArrayList;

import yuki.task.Task;

/**
 * TaskList class to store tasks.
 * @param <T> Task type
 */
public class TaskList <T> {
    private ArrayList<T> taskList;
    public TaskList() {
        taskList = new ArrayList<>();
    }
    public TaskList(TaskList<T> storageList) {
        taskList = storageList.taskList;
    }

    /**
     * Adds a task to the task list.
     * @param task Task to be added
     */
    public void add(T task) {
        taskList.add(task);
    }

    public int size() {
        return taskList.size();
    }

    public String getDescription(int i) {
        return taskList.get(i).toString();
    }

    /**
     * Returns the task at the specified index.
     * @param taskNumber Index of the task
     * @return Task at the specified index
     */
    public Task get(int taskNumber) {
        return (Task) taskList.get(taskNumber);
    }

    /**
     * Removes the task at the specified index.
     * @param taskNumber Index of the task
     */
    public void remove(int taskNumber) {
        taskList.remove(taskNumber);
    }
}
