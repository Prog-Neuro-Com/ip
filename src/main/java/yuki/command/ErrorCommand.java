package yuki.command;

import yuki.Storage;
import yuki.TaskList;
import yuki.Ui;
import yuki.YukiException;
import yuki.task.Task;

/**
 * Represents a command to handle invalid commands.
 */
public class ErrorCommand extends Command{
    public ErrorCommand(String[] commands, String description, boolean isExit) {
        super(commands, description, isExit);
    }

    @Override
    public String execute(TaskList<Task> tasks, Ui ui, Storage storage) throws YukiException {
        return this.getDescription();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
