import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private final LocalDate deadline;

    public Deadline(String taskName, String deadline) throws YukiException {
        super(taskName);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (Exception e) {
            throw new YukiException("Invalid date format. Please use yyyy-mm-dd");
        }
    }

    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | " + deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
