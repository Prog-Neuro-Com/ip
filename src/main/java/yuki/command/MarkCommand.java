package yuki.command;

import yuki.Storage;
import yuki.TaskList;
import yuki.Ui;
import yuki.YukiException;
import yuki.task.Task;

public class MarkCommand extends Command {
    int taskNumber = getValidatedTaskNumber();
    public MarkCommand(String[] commands, String description, boolean isExit) {
        super(commands, description, isExit);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList<Task> tasks, Ui ui, Storage storage) throws YukiException {
        return handleMarkCommand(tasks);
    }

    private String handleMarkCommand(TaskList<Task> tasks) {
        int taskNumber = getValidatedTaskNumber();
        if (taskNumber >= tasks.size()) return "Task number does not exist";

        tasks.get(taskNumber).markAsDone();
        StringBuilder output = new StringBuilder("Nice! I've marked this task as done:\n");
        output.append(tasks.getDescription(taskNumber)).append("\n");
        Command.lastCommand = this;
        return output.toString();
    }

    private int getValidatedTaskNumber() {
        try {
            int taskNumber = Integer.parseInt(getCommand(1)) - 1;
            if (taskNumber < 0) throw new NumberFormatException();
            return taskNumber;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    @Override
    public String undo(TaskList<Task> tasks) {
        tasks.get(taskNumber).markAsNotDone();
        StringBuilder output = new StringBuilder("Nice! I've unmarked this task:\n");
        output.append(tasks.getDescription(taskNumber)).append("\n");
        Command.lastCommand = this;
        return output.toString();
    }
}
