package yuki.command;

import yuki.Storage;
import yuki.TaskList;
import yuki.Ui;
import yuki.YukiException;
import yuki.task.Task;

/**
 * Represents a command to add a task to the task list.
 */
public abstract class Command {
    final String[] command;
    private final String description;

    public Command(String[] command, String description, boolean isExit) {
        this.command = command;
        this.description = description;
    }

    public String getCommand(int i){
        return command[i];
    }

    public String getDescription() {
        return description;
    }

    public abstract boolean isExit();

    public abstract String execute(TaskList<Task> tasks, Ui ui, Storage storage) throws YukiException;
}
