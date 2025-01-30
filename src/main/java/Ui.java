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
        return System.console().readLine();
    }

    public void showLoadingError() {
        System.out.println("Error loading file. Starting with an empty task list.");
    }

    public void print(String message) {
        System.out.println(message);
    }
}
