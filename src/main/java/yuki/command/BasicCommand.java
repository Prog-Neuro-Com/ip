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
    public BasicCommand(String[] command, String description, boolean isExit) {
        super(command, description, isExit);
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
        StringBuilder output = new StringBuilder();
        switch (this.getCommand(0)) {
            case "list" -> {
                output.append("Here are the tasks in your list:\n");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.getDescription(i));
                    output.append((i + 1)).append(". ").append(tasks.getDescription(i)).append("\n");
                }
                return output.toString();
            }
            case "mark" -> {
                try {
                    int taskNumber = Integer.parseInt(getCommand(1));
                    tasks.get(taskNumber - 1).markAsDone();
                    output.append("Nice! I've marked this task as done:\n");
                    output.append(tasks.getDescription(taskNumber - 1)).append("\n");
                    return output.toString();
                } catch (IndexOutOfBoundsException e) {
                    return "Task number does not exist";
                } catch (NumberFormatException e) {
                    return "Please enter a valid task number";
                }

            }
            case "unmark" -> {
                try {
                    int taskNumber = Integer.parseInt(getCommand(1));
                    tasks.get(taskNumber - 1).markAsNotDone();
                    output.append("Nice! I've marked this task as not done:\n");
                    output.append(tasks.getDescription(taskNumber - 1)).append("\n");
                    return output.toString();
                } catch (IndexOutOfBoundsException e) {
                    return "Task number does not exist";
                } catch (NumberFormatException e) {
                    return "Please enter a valid task number";
                }
            }
            case "delete" -> {
                try {
                    int taskNumber = Integer.parseInt(getCommand(1));
                    output.append(tasks.getDescription(taskNumber - 1)).append("\n");
                    tasks.remove(taskNumber - 1);
                    output.append("Noted. I've removed this task:\n");
                    output.append("Now you have ").append(tasks.size()).append(" tasks in the list.\n");
                    return output.toString();
                } catch (IndexOutOfBoundsException e) {
                    return "Task number does not exist";
                } catch (NumberFormatException e) {
                    return "Please enter a valid task number";
                }
            }
            case "find" -> {
                try {
                    String keyword = getCommand(1);
                    output.append("Here are the matching tasks in your list:\n");
                    for (int i = 0; i < tasks.size(); i++) {
                        if (tasks.getDescription(i).contains(keyword)) {
                            output.append((i + 1)).append(". ").append(tasks.getDescription(i)).append("\n");
                            System.out.println((i + 1) + ". " + tasks.getDescription(i));
                        }
                    }
                    return output.toString();
                } catch (IndexOutOfBoundsException e) {
                    return "Please enter a keyword to search for";
                }
            }
        }
        return "Invalid command";
    }
}
