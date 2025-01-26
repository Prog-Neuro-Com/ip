public class Task {
    private final String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
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
        return "[" + (isDone ? "X" : " ") + "] " + taskName;
    }
}

