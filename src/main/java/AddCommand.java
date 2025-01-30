import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
                if (this.command.length != 2) {
                    throw new YukiException("Invalid format for todo command. Please enter in the format: todo <description>");
                }
                Task newTodo = new Todo("0", this.getCommand(1));
                tasks.add(newTodo);
                ui.showLine();
                ui.print("Got it. I've added this task:");
                ui.print(newTodo.toString());
                ui.print("Now you have " + tasks.size() + " tasks in the list.");
                ui.showLine();
            }
            case "deadline" -> {
                List<String> list = new ArrayList<>(Arrays.asList(command));
                list.remove("/by");
                String[] c = list.toArray(new String[0]);
                if (c.length != 3) {
                    throw new YukiException("Invalid format for deadline command. Please enter in the format: deadline <description> /by <date>");
                }
                Task newDeadline = new Deadline("0", c[1], c[2]);
                tasks.add(newDeadline);
                ui.showLine();
                ui.print("Got it. I've added this task:");
                ui.print(newDeadline.toString());
                ui.print("Now you have " + tasks.size() + " tasks in the list.");
                ui.showLine();
            }
            case "event" -> {
                List<String> list = new ArrayList<>(Arrays.asList(command));
                list.remove("/from");
                list.remove("/to");
                String[] c = list.toArray(new String[0]);
                if (c.length != 4) {
                    throw new YukiException("Invalid format for event command. Please enter in the format: event <description> /from <date> /to <date>");
                }
                Task newEvent = new Event("0", c[1], c[2], c[3]);
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
