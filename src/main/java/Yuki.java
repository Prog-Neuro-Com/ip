import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

public class Yuki {
    private static final String BORDER = "____________________________________________________________";
    private static final String END_COMMAND = "bye";
    private static final String FILE_PATH = "src/main/java/data/Yuki.txt";

    public static void main(String[] args) throws YukiException {
        printWelcomeMessage();

        ArrayList<Task> taskList = new ArrayList<>();
        readTasksFromFile(taskList);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.next();
            if (command.equals(END_COMMAND)) break;

            handleCommand(command, scanner, taskList);
        }

        saveTasksToFile(taskList);
        printExitMessage();
    }

    private static void printWelcomeMessage() {
        System.out.println(BORDER + "\n Hello! I'm Yuki\n What can I do for you?\n" + BORDER);
    }

    private static void readTasksFromFile(ArrayList<Task> taskList) {
        try {
            File file = new File(FILE_PATH);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String task = scanner.nextLine();
                String[] parts = task.split("\\|");
                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].trim();
                }
                switch (parts[0]) {
                    case "T" -> {
                        if (parts.length < 3 || parts[2].isEmpty()) {
                            throw new YukiException(BORDER + "\n The description of a todo cannot be empty.\n Example: todo read book\n" + BORDER);
                        }
                        taskList.add(new Todo(parts[2]));
                    }
                    case "D" -> {
                        if (parts.length < 4 || parts[3].isEmpty()) {
                            throw new YukiException(BORDER + "\n The description of a deadline must include a deadline.\n Example: deadline return book /by Sunday\n" + BORDER);
                        }
                        taskList.add(new Deadline(parts[2], parts[3]));
                    }
                    case "E" -> {
                        if (parts.length < 5 || parts[3].isEmpty() || parts[4].isEmpty()) {
                            throw new YukiException(BORDER + "\n The description of an event must include a start and end time.\n Example: event project meeting /from Mon 2pm /to 4pm\n" + BORDER);
                        }
                        taskList.add(new Event(parts[2], parts[3], parts[4]));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(BORDER + "\n File not found. Creating a new file...\n" + BORDER);
        } catch (YukiException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(BORDER + "\n Tasks loaded from file.\n" + BORDER);
    }

    private static void saveTasksToFile(ArrayList<Task> taskList) {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : taskList) {
                writer.write(task.toFileString() + "\n");
            }
            writer.close();
            System.out.println(BORDER + "\n Tasks saved to file.\n" + BORDER);
        } catch (Exception e) {
            System.out.println(BORDER + "\n An error occurred while saving tasks to file.\n" + BORDER);
        }
    }

    private static void printExitMessage() {
        System.out.println(BORDER + "\n Bye. Hope to see you again soon!\n" + BORDER);
    }

    private static void handleCommand(String command, Scanner scanner, ArrayList<Task> taskList) throws YukiException {
        switch (command) {
            case "list" -> listTasks(taskList);
            case "mark" -> markTask(scanner, taskList);
            case "unmark" -> unmarkTask(scanner, taskList);
            case "delete" -> deleteTask(scanner, taskList);
            case "todo", "deadline", "event" -> addTask(command, scanner, taskList);
            default -> printInvalidCommandMessage();
        }
    }

    private static void listTasks(ArrayList<Task> taskList) {
        System.out.println(BORDER + "\n Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
        System.out.println(BORDER);
    }

    private static void markTask(Scanner scanner, ArrayList<Task> taskList) {
        try {
            int taskNumber = scanner.nextInt() - 1; // Adjust for 0-based indexing
            validateTaskNumber(taskNumber, taskList);

            taskList.get(taskNumber).markAsDone();
            System.out.println(BORDER + "\n Nice! I've marked this task as done:");
            System.out.println(" " + taskList.get(taskNumber) + "\n" + BORDER);
        } catch (Exception e) {
            printInvalidTaskNumberMessage();
        }
    }

    private static void unmarkTask(Scanner scanner, ArrayList<Task> taskList) {
        try {
            int taskNumber = scanner.nextInt() - 1; // Adjust for 0-based indexing
            validateTaskNumber(taskNumber, taskList);

            taskList.get(taskNumber).markAsNotDone();
            System.out.println(BORDER + "\n OK, I've marked this task as not done yet:");
            System.out.println(" " + taskList.get(taskNumber) + "\n" + BORDER);
        } catch (Exception e) {
            printInvalidTaskNumberMessage();
        }
    }

    private static void deleteTask(Scanner scanner, ArrayList<Task> taskList) {
        try {
            int taskNumber = scanner.nextInt() - 1; // Adjust for 0-based indexing
            validateTaskNumber(taskNumber, taskList);

            System.out.println(BORDER + "\n Noted. I've removed this task:");
            System.out.println("   " + taskList.get(taskNumber) + "\n");
            taskList.remove(taskNumber);
            System.out.println(" Now you have " + taskList.size() + " tasks in the list.\n" + BORDER);
        } catch (Exception e) {
            printInvalidTaskNumberMessage();
        }
    }

    private static void addTask(String command, Scanner scanner, ArrayList<Task> taskList) {
        String description = scanner.nextLine().trim();
        try {
            switch (command) {
                case "todo" -> addTodoTask(description, taskList);
                case "deadline" -> addDeadlineTask(description, taskList);
                case "event" -> addEventTask(description, taskList);
            }
        } catch (YukiException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addTodoTask(String description, ArrayList<Task> taskList) throws YukiException {
        if (description.isEmpty()) {
            throw new YukiException(BORDER + "\n The description of a todo cannot be empty.\n Example: todo read book\n" + BORDER);
        }
        taskList.add(new Todo(description));
        printTaskAddedMessage(taskList.get(taskList.size() - 1), taskList.size());
    }

    private static void addDeadlineTask(String description, ArrayList<Task> taskList) throws YukiException {
        String[] parts = description.split("/by");
        if (parts.length < 2) {
            throw new YukiException(BORDER + "\n The description of a deadline must include a deadline.\n Example: deadline return book /by Sunday\n" + BORDER);
        }
        taskList.add(new Deadline(parts[0].trim(), parts[1].trim()));
        printTaskAddedMessage(taskList.get(taskList.size() - 1), taskList.size());
    }

    private static void addEventTask(String description, ArrayList<Task> taskList) throws YukiException {
        String[] parts = description.replace("/to", "/from").split("/from");
        if (parts.length < 3) {
            throw new YukiException(BORDER + "\n The description of an event must include a start and end time.\n Example: event project meeting /from Mon 2pm /to 4pm\n" + BORDER);
        }
        taskList.add(new Event(parts[0].trim(), parts[1].trim(), parts[2].trim()));
        printTaskAddedMessage(taskList.get(taskList.size() - 1), taskList.size());
    }

    private static void validateTaskNumber(int taskNumber, ArrayList<Task> taskList) throws YukiException {
        if (taskNumber < 0 || taskNumber >= taskList.size()) {
            throw new YukiException(BORDER + "\n Please enter a valid task number\n" + BORDER);
        }
    }

    private static void printInvalidCommandMessage() {
        System.out.println(BORDER + "\n You need to specify a valid command (e.g., todo, deadline, event, list, mark, unmark, delete).\n" + BORDER);
    }

    private static void printInvalidTaskNumberMessage() {
        System.out.println(BORDER + "\n Please enter a valid task number\n" + BORDER);
    }

    private static void printTaskAddedMessage(Task task, int taskCount) {
        System.out.println(BORDER + "\n Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list.\n" + BORDER);
    }
}
