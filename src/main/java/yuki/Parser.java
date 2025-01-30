package yuki;

import yuki.command.AddCommand;
import yuki.command.BasicCommand;
import yuki.command.Command;
import yuki.command.ErrorCommand;
import yuki.command.ExitCommand;

public class Parser {
    public Parser() {
    }

    public static Command parse(String input) {
        String[] command = input.split(" ");
        return switch (command[0]) {
            case "bye" -> new ExitCommand(command, "Exiting Yuki...", true);
            case "list", "mark", "unmark", "delete" -> new BasicCommand(command, "Some basic command...", false);
            case "todo", "deadline", "event" -> new AddCommand(command, "Adding a task...", false);
            default -> new ErrorCommand(command, "Invalid command...", false);
        };
    }
}
