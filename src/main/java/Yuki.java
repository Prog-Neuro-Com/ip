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
        currentString = scanner.nextLine();
        Task[] taskList = new Task[105];
        int taskCount = 0;

        while (!currentString.equals(endString)) {
            if (currentString.equals("list")) {
                System.out.println("____________________________________________________________\n");
                for (int i = 1; i <= taskCount; i++) {
                    System.out.println(i + ". " + taskList[i].toString());
                }
                System.out.println("____________________________________________________________\n");
                currentString = scanner.nextLine();
                continue;
            }

            System.out.println("____________________________________________________________\n" +
                    " added: " + currentString + "\n" +
                    "____________________________________________________________\n");
            taskList[++taskCount] = new Task(currentString);
            currentString = scanner.nextLine();
        }
        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }
}
