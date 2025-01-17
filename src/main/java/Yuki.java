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
                    String addString = scanner.nextLine();
                    currentString = currentString + addString;
                    System.out.println("____________________________________________________________\n" +
                            " added: " + currentString + "\n" +
                            "____________________________________________________________\n");
                    taskList[++taskCount] = new Task(currentString);
                }
            }
            currentString = scanner.next();
        }
        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }
}
