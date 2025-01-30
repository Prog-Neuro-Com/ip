package yuki.command;

import yuki.Storage;
import yuki.TaskList;
import yuki.Ui;
import yuki.YukiException;
import yuki.task.Deadline;
import yuki.task.Event;
import yuki.task.Task;
import yuki.task.Todo;

import java.util.Arrays;
import java.util.stream.Collectors;

public class AddCommand extends Command{
    public AddCommand(String[] command, String description, boolean isExit) {
        super(command, description, isExit);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList<Task> tasks, Ui ui, Storage storage) throws YukiException {
        switch (this.getCommand(0)) {
            case "todo" -> {
                String result = Arrays.stream(command, 1, command.length).collect(Collectors.joining(" "));
                Task newTodo = new Todo("0", result);
                tasks.add(newTodo);
                ui.showLine();
                ui.print("Got it. I've added this task:");
                ui.print(newTodo.toString());
                ui.print("Now you have " + tasks.size() + " tasks in the list.");
                ui.showLine();
            }
            case "deadline" -> {
                String result = Arrays.stream(command, 1, command.length).collect(Collectors.joining(" "));
                String[] c = result.split("/by");
                c = Arrays.stream(c).map(String::trim).toArray(String[]::new);
                if (c.length != 2) {
                    throw new YukiException("Invalid format for deadline command. Please enter in the format: deadline "
                                            + "<description> /by <date>");
                }
                Task newDeadline = new Deadline("0", c[0], c[1]);
                tasks.add(newDeadline);
                ui.showLine();
                ui.print("Got it. I've added this task:");
                ui.print(newDeadline.toString());
                ui.print("Now you have " + tasks.size() + " tasks in the list.");
                ui.showLine();
            }
            case "event" -> {
                String result = Arrays.stream(command, 1, command.length).collect(Collectors.joining(" "));
                String[] c = result.replace("/to", "/from ").split("/from");
                c = Arrays.stream(c).map(String::trim).toArray(String[]::new);
                if (c.length != 3) {
                    throw new YukiException("Invalid format for event command. Please enter in the format: event <description> "
                                            + "/from <start date> /to <end date>");
                }
                Task newEvent = new Event("0", c[0], c[1], c[2]);
                tasks.add(newEvent);
                ui.showLine();
                ui.print("Got it. I've added this task:");
                ui.print(newEvent.toString());
                ui.print("Now you have " + tasks.size() + " tasks in the list.");
                ui.showLine();
            }
        }
    }
}
