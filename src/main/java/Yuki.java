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
        while (!currentString.equals(endString)) {
            System.out.println("____________________________________________________________\n" +
                    " " + currentString + "\n" +
                    "____________________________________________________________\n");
            currentString = scanner.nextLine();
        }
        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }
}
