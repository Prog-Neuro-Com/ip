import java.util.Scanner;

public class Yuki {
    public static void main(String[] args) throws YukiException {
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
                    try {
                        int taskNumber = scanner.nextInt();
                        if (taskNumber > taskCount) {
                            throw new YukiException("____________________________________________________________\n" +
                                    " Please enter a valid task number\n" +
                                    "____________________________________________________________\n");
                        }
                        System.out.println("____________________________________________________________\n"
                                + " Nice! I've marked this task as done:\n");
                        taskList[taskNumber].markAsDone();
                        System.out.println(" " + taskList[taskNumber].toString() + "\n" +
                                "____________________________________________________________\n");
                    } catch (Exception e) {
                        System.out.println("____________________________________________________________\n" +
                                " Please enter a valid task number\n" +
                                "____________________________________________________________\n");
                        scanner.next();
                    }

                }
                case "unmark" -> {
                    try {
                        int taskNumber = scanner.nextInt();
                        if (taskNumber > taskCount) {
                            throw new YukiException("____________________________________________________________\n" +
                                    " Please enter a valid task number\n" +
                                    "____________________________________________________________\n");
                        }
                        System.out.println("____________________________________________________________\n"
                                + " OK, I've marked this task as not done yet:\n");
                        taskList[taskNumber].markAsNotDone();
                        System.out.println(taskList[taskNumber].toString() + "\n" +
                                "____________________________________________________________\n");
                    } catch (Exception e) {
                        System.out.println("____________________________________________________________\n" +
                                " Please enter a valid task number\n" +
                                "____________________________________________________________\n");
                        scanner.next();

                    }
                }
                default -> {
                    switch (currentString) {
                        case "todo" -> {
                            String taskName = scanner.nextLine();
                            try {
                                if (taskName.isEmpty()) {
                                    throw new YukiException("____________________________________________________________\n" +
                                            " The description of a todo cannot be empty.\n" +
                                            " Example: todo read book\n" +
                                            "____________________________________________________________\n");
                                }
                            } catch (YukiException e) {
                                System.out.println(e.getMessage());
                                break;
                            }
                            taskList[++taskCount] = new Todo(taskName);
                            System.out.println("____________________________________________________________\n" +
                                    " Got it. I've added this task:\n" +
                                    "   " + taskList[taskCount].toString() + "\n" +
                                    " Now you have " + taskCount + " tasks in the list.\n" +
                                    "____________________________________________________________\n");
                        }
                        case "deadline" -> {
                            String[] array = scanner.nextLine().split("/by");
                            try {
                                if (array.length < 2) {
                                    throw new YukiException("____________________________________________________________\n" +
                                            " The description of a deadline must include a deadline.\n" +
                                            " Example: deadline return book /by Sunday\n" +
                                            "____________________________________________________________\n");
                                }
                            } catch (YukiException e) {
                                System.out.println(e.getMessage());
                                break;
                            }
                            taskList[++taskCount] = new Deadline(array[0], array[1]);
                            System.out.println("____________________________________________________________\n" +
                                    " Got it. I've added this task:\n" +
                                    "   " + taskList[taskCount].toString() + "\n" +
                                    " Now you have " + taskCount + " tasks in the list.\n" +
                                    "____________________________________________________________\n");
                        }
                        case "event" -> {
                            String[] array = scanner.nextLine().replace("/to", "/from").split("/from");
                            try {
                                if (array.length < 3) {
                                    throw new YukiException("____________________________________________________________\n" +
                                            " The description of an event must include a start and end time.\n" +
                                            " Example: event project meeting /from Mon 2pm /to 4pm\n" +
                                            "____________________________________________________________\n");
                                }
                            } catch (YukiException e) {
                                System.out.println(e.getMessage());
                                break;
                            }
                            taskList[++taskCount] = new Event(array[0], array[1], array[2]);
                            System.out.println("____________________________________________________________\n" +
                                    " Got it. I've added this task:\n" +
                                    "   " + taskList[taskCount].toString() + "\n" +
                                    " Now you have " + taskCount + " tasks in the list.\n" +
                                    "____________________________________________________________\n");
                        }
                        default -> {
                            try {
                                throw new YukiException("____________________________________________________________\n" +
                                        " You need to specify the type of task you want to add among(todo, deadline, event)\n" +
                                        "____________________________________________________________\n");
                            } catch (YukiException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }
                }
            }
            currentString = scanner.next();
        }
        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }
}
