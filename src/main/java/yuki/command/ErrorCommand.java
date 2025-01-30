package yuki.command;

import yuki.Storage;
import yuki.TaskList;
import yuki.Ui;
import yuki.YukiException;
import yuki.task.Task;

public class ErrorCommand extends Command{
    public ErrorCommand(String[] command, String description, boolean isExit) {
        super(command, description, isExit);
    }

    @Override
    public void execute(TaskList<Task> tasks, Ui ui, Storage storage) throws YukiException {
        ui.showError(this.getDescription());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
