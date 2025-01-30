public class BasicCommand extends Command{
    public BasicCommand(String[] command, String description, boolean isExit) {
        super(command, description, isExit);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList<Task> tasks, Ui ui, Storage storage) throws YukiException {
        switch (this.getCommand(0)) {
            case "list" -> {
                ui.showLine();
                ui.print("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.getDescription(i));
                }
                ui.showLine();
            }
            case "mark" -> {
                try {
                    int taskNumber = Integer.parseInt(getCommand(1));
                    tasks.get(taskNumber).markAsDone();
                    ui.showLine();
                    ui.print("Nice! I've marked this task as done:");
                    ui.print(tasks.getDescription(taskNumber));
                    ui.showLine();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Task number does not exist");
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid task number");
                }

            }
            case "unmark" -> {
                try {
                    int taskNumber = Integer.parseInt(getCommand(1));
                    tasks.get(taskNumber).markAsNotDone();
                    ui.showLine();
                    ui.print("Nice! I've marked this task as not done:");
                    ui.print(tasks.getDescription(taskNumber));
                    ui.showLine();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Task number does not exist");
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid task number");
                }
            }
            case "delete" -> {
                try {
                    int taskNumber = Integer.parseInt(getCommand(1));
                    ui.showLine();
                    ui.print("Noted. I've removed this task:");
                    ui.print(tasks.getDescription(taskNumber));
                    tasks.remove(taskNumber);
                    ui.print("Now you have " + tasks.size() + " tasks in the list.");
                    ui.showLine();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Task number does not exist");
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid task number");
                }
            }
        }

    }
}
