package yuki.command;

import java.util.Arrays;
import java.util.stream.Collectors;

import yuki.Storage;
import yuki.TaskList;
import yuki.Ui;
import yuki.YukiException;
import yuki.task.Deadline;
import yuki.task.Event;
import yuki.task.Task;
import yuki.task.Todo;


/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    public AddCommand(String[] commands, String description, boolean isExit) {
        super(commands, description, isExit);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList<Task> tasks, Ui ui, Storage storage) throws YukiException {
        StringBuilder output = new StringBuilder();
        switch (this.getCommand(0)) {
            case "todo" -> {
                String result = Arrays.stream(commands, 1, commands.length)
                        .collect(Collectors.joining(" "));
                if (result.isEmpty()) {
                    throw new YukiException("The description of a todo cannot be empty.");
                }
                Task newTodo = new Todo("0", result);
                tasks.add(newTodo);
                output.append("Got it. I've added this task:\n");
                output.append(newTodo).append("\n");
                output.append("Now you have ").append(tasks.size()).append(" tasks in the list.\n");
                return output.toString();
            }
            case "deadline" -> {
                String result = Arrays.stream(commands, 1, commands.length)
                        .collect(Collectors.joining(" "));
                String[] c = result.split("/by");
                c = Arrays.stream(c).map(String::trim).toArray(String[]::new);
                if (c.length != 2) {
                    throw new YukiException(
                            "Invalid format for deadline command. Please enter in the format: deadline "
                                    + "<description> /by <date>");
                }
                Task newDeadline = new Deadline("0", c[0], c[1]);
                tasks.add(newDeadline);
                output.append("Got it. I've added this task:\n");
                output.append(newDeadline).append("\n");
                output.append("Now you have ").append(tasks.size()).append(" tasks in the list.\n");
                return output.toString();
            }
            case "event" -> {
                String result = Arrays.stream(commands, 1, commands.length)
                        .collect(Collectors.joining(" "));
                String[] c = result.replace("/to", "/from ").split("/from");
                c = Arrays.stream(c).map(String::trim).toArray(String[]::new);
                if (c.length != 3) {
                    throw new YukiException(
                            "Invalid format for event command. Please enter in the format: event <description> "
                                            + "/from <start date> /to <end date>");
                }
                Task newEvent = new Event("0", c[0], c[1], c[2]);
                tasks.add(newEvent);
                output.append("Got it. I've added this task:\n");
                output.append(newEvent).append("\n");
                output.append("Now you have ").append(tasks.size()).append(" tasks in the list.\n");
                return output.toString();
            }
        }
        return "";
    }
}
