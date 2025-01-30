public class Event extends Task{
    private final String from;
    private final String to;

    public Event(String isDone, String taskName, String from, String to) {
        super(taskName, isDone.equals("1"));
        this.from = from;
        this.to = to;
    }

    @Override
    public String toFileString() {
        return "E | " + super.toFileString() + " | " + from + " | " + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + from + " to " + to + ")";
    }
}
