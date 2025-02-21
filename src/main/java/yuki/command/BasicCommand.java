package yuki.command;

import yuki.Storage;
import yuki.TaskList;
import yuki.Ui;
import yuki.YukiException;
import yuki.task.Task;

/**
 * Represents a command to add a task to the task list.
 */
public class BasicCommand extends Command{
    public BasicCommand(String[] commands, String description, boolean isExit) {
        super(commands, description, isExit);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command to list, mark, unmark or delete a task.
     * @param tasks TaskList containing the tasks.
     * @throws YukiException if the task number does not exist or is not a number.
     */
    @Override
    public String execute(TaskList<Task> tasks, Ui ui, Storage storage) throws YukiException {
        return switch (this.getCommand(0)) {
            case "list" -> handleListCommand(tasks);
            case "mark" -> handleMarkCommand(tasks);
            case "unmark" -> handleUnmarkCommand(tasks);
            case "delete" -> handleDeleteCommand(tasks);
            case "find" -> handleFindCommand(tasks);
            default -> "Invalid command";
        };
    }

    private String handleListCommand(TaskList<Task> tasks) {
        StringBuilder output = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            output.append((i + 1)).append(". ").append(tasks.getDescription(i)).append("\n");
        }
        return output.toString();
    }

    private String handleMarkCommand(TaskList<Task> tasks) {
        int taskNumber = getValidatedTaskNumber();
        if (taskNumber == -1) return "Please enter a valid task number";
        if (taskNumber >= tasks.size()) return "Task number does not exist";

        tasks.get(taskNumber).markAsDone();
        StringBuilder output = new StringBuilder("Nice! I've marked this task as done:\n");
        output.append(tasks.getDescription(taskNumber)).append("\n");
        return output.toString();
    }

    private String handleUnmarkCommand(TaskList<Task> tasks) {
        int taskNumber = getValidatedTaskNumber();
        if (taskNumber == -1) return "Please enter a valid task number";
        if (taskNumber >= tasks.size()) return "Task number does not exist";

        tasks.get(taskNumber).markAsNotDone();
        StringBuilder output = new StringBuilder("Nice! I've marked this task as not done:\n");
        output.append(tasks.getDescription(taskNumber)).append("\n");
        return output.toString();
    }

    private String handleDeleteCommand(TaskList<Task> tasks) {
        int taskNumber = getValidatedTaskNumber();
        if (taskNumber == -1) return "Please enter a valid task number";
        if (taskNumber >= tasks.size()) return "Task number does not exist";

        StringBuilder output = new StringBuilder();
        output.append(tasks.getDescription(taskNumber)).append("\n");
        tasks.remove(taskNumber);
        output.append("Noted. I've removed this task:\n");
        output.append("Now you have ").append(tasks.size()).append(" tasks in the list.\n");
        return output.toString();
    }

    private String handleFindCommand(TaskList<Task> tasks) {
        try {
            String keyword = getCommand(1);
            StringBuilder output = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.getDescription(i).contains(keyword)) {
                    output.append((i + 1)).append(". ").append(tasks.getDescription(i)).append("\n");
                }
            }
            return output.toString();
        } catch (IndexOutOfBoundsException e) {
            return "Please enter a keyword to search for";
        }
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

}
