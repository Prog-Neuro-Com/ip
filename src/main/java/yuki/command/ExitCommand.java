package yuki.command;

import yuki.Storage;
import yuki.TaskList;
import yuki.Ui;
import yuki.YukiException;
import yuki.task.Task;

/**
 * Represents a command to handle invalid commands.
 */
public class ExitCommand extends Command {

    public ExitCommand(String[] commands, String description, boolean isExit) {
        super(commands, description, isExit);
    }

    @Override
    public String execute(TaskList<Task> tasks, Ui ui, Storage storage) throws YukiException {
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
