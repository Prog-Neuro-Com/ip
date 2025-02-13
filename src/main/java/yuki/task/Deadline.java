package yuki.task;

import yuki.YukiException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task{
    private final LocalDate deadline;

    public Deadline(String isDone, String taskName, String deadline) throws YukiException {
        super(taskName, isDone.equals("1"));
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (Exception e) {
            throw new YukiException("Invalid date format. Please use yyyy-mm-dd");
        }
    }

    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | "
                + deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
