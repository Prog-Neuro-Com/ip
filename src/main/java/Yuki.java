import java.util.Scanner;

public class Yuki {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Yuki\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");

        String endString = "bye";
        String currentString = " ";
        Scanner scanner = new Scanner(System.in);
        currentString = scanner.next();
        Task[] taskList = new Task[105];
        int taskCount = 0;

        while (!currentString.equals(endString)) {
            switch (currentString) {
                case "list" -> {
                    System.out.println("____________________________________________________________\n"
                            + " Here are the tasks in your list:\n");
                    for (int i = 1; i <= taskCount; i++) {
                        System.out.println(i + ". " + taskList[i].toString());
                    }
                    System.out.println("____________________________________________________________\n");
                }
                case "mark" -> {
                    System.out.println("____________________________________________________________\n"
                            + " Nice! I've marked this task as done:\n");
                    int taskNumber = scanner.nextInt();
                    taskList[taskNumber].markAsDone();
                    System.out.println(" " + taskList[taskNumber].toString() + "\n" +
                            "____________________________________________________________\n");
                }
                case "unmark" -> {
                    System.out.println("____________________________________________________________\n"
                            + " OK, I've marked this task as not done yet:\n");
                    int taskNumber = scanner.nextInt();
                    taskList[taskNumber].markAsNotDone();
                    System.out.println(taskList[taskNumber].toString() + "\n" +
                            "____________________________________________________________\n");
                }
                default -> {
                    switch (currentString) {
                        case "todo" -> {
                            String taskName = scanner.nextLine();
                            taskList[++taskCount] = new Todo(taskName);
                        }
                        case "deadline" -> {
                            String[] array = scanner.nextLine().split("/by");
                            taskList[++taskCount] = new Deadline(array[0], array[1]);
                        }
                        case "event" -> {
                            String[] array = scanner.nextLine().replace("/to", "/from").split("/from");
                            taskList[++taskCount] = new Event(array[0], array[1], array[2]);
                        }
                    }
                    System.out.println("____________________________________________________________\n" +
                            " Got it. I've added this task:\n" +
                            "   " + taskList[taskCount].toString() + "\n" +
                            " Now you have " + taskCount + " tasks in the list.\n" +
                            "____________________________________________________________\n");
                }
            }
            currentString = scanner.next();
        }
        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }
}
