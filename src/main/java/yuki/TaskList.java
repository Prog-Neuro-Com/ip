package yuki;

import yuki.task.Task;

import java.util.ArrayList;

public class TaskList <T> {
    ArrayList<T> taskList;
    public TaskList() {
        taskList = new ArrayList<>();
    }
    public TaskList(TaskList<T> storageList) {
        taskList = storageList.taskList;
    }

    public void add(T task) {
        taskList.add(task);
    }

    public int size() {
        return taskList.size();
    }

    public String getDescription(int i) {
        return taskList.get(i).toString();
    }

    public Task get(int taskNumber) {
        return (Task) taskList.get(taskNumber);
    }

    public void remove(int taskNumber) {
        taskList.remove(taskNumber);
    }
}
