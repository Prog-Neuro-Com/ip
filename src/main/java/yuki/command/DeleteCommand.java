package yuki.command;

import yuki.Storage;
import yuki.TaskList;
import yuki.Ui;
import yuki.YukiException;
import yuki.task.Task;

public class DeleteCommand extends Command {
    Task deletedTask = null;

    public DeleteCommand(String[] commands, String description, boolean isExit) {
        super(commands, description, isExit);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList<Task> tasks, Ui ui, Storage storage) throws YukiException {
        return handleDeleteCommand(tasks);
    }

    private String handleDeleteCommand(TaskList<Task> tasks) {
        int taskNumber = getValidatedTaskNumber();
        if (taskNumber >= tasks.size()) return "Task number does not exist";

        StringBuilder output = new StringBuilder();
        output.append(tasks.getDescription(taskNumber)).append("\n");
        this.deletedTask = tasks.remove(taskNumber);
        output.append("Noted. I've removed this task:\n");
        output.append("Now you have ").append(tasks.size()).append(" tasks in the list.\n");
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
    public String undo(TaskList<Task> tasks) throws YukiException {
        if (deletedTask == null) {
            throw new YukiException("No task has been deleted yet.");
        }
        tasks.add(deletedTask);
        return "Task has been added back to the list.";
    }
}