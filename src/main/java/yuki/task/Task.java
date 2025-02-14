package yuki.task;

/**
 * Represents a task in the task list.
 */
public class Task {
    private final String taskName;
    private boolean isDone;

    /**
     * Creates a task with the given task name and whether it is done.
     *
     * @param taskName The name of the task.
     * @param isDone   Whether the task is done.
     */
    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String toFileString() {
        return (isDone ? "1" : "0") + " | " + taskName;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : "   ") + "] " + taskName;
    }
}

