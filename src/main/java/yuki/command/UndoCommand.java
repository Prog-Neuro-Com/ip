package yuki.command;

import yuki.Storage;
import yuki.TaskList;
import yuki.Ui;
import yuki.YukiException;
import yuki.task.Task;

/**
 * Represents a command to handle invalid commands.
 */
public class UndoCommand extends Command {
    public UndoCommand(String[] commands, String description, boolean isExit) {
        super(commands, description, isExit);
    }

    @Override
    public String execute(TaskList<Task> tasks, Ui ui, Storage storage) throws YukiException {
        return Command.lastCommand == null ? "No command to undo." : Command.lastCommand.undo(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String undo(TaskList<Task> tasks) throws YukiException {
        return "Can only undo the last command.";
    }
}