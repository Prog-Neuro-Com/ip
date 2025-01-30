package yuki;

/**
 * Ui class deals with interactions with the user.
 */
public class Ui {
    private static final String BORDER = "____________________________________________________________";

    public Ui() {
    }

    public void showWelcome() {
        System.out.println(BORDER);
        System.out.println("Hello! I'm Yuki");
        System.out.println("What can I do for you?");
        System.out.println(BORDER);
    }

    public void showLine() {
        System.out.println(BORDER + "\n");
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public String readCommand() {
        return new java.util.Scanner(System.in).nextLine();
    }

    public void showLoadingError() {
        System.out.println("Error loading file. Starting with an empty task list.");
    }

    /**
     * Prints the message to the console.
     *
     * @param message The message to be printed.
     */
    public void print(String message) {
        System.out.println(message);
    }
}
