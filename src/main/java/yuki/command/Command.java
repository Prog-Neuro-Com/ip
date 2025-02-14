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
    final String[] commands;
    private final String description;

    /**
     * Creates a command object.
     *
     * @param commands The commands that can be used to execute the command.
     * @param description The description of the command.
     * @param isExit Whether the command is an exit command.
     */
    public Command(String[] commands, String description, boolean isExit) {
        this.commands = commands;
        this.description = description;
    }

    public String getCommand(int i) {
        return commands[i];
    }

    public String getDescription() {
        return description;
    }

    public abstract boolean isExit();

    public abstract String execute(TaskList<Task> tasks, Ui ui, Storage storage) throws YukiException;
}
