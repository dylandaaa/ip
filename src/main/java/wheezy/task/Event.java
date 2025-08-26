package wheezy.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate from;
    private LocalDate until;

    public Event(String input, String from, String until) {
        super(input);
        this.from = LocalDate.parse(from);
        this.until = LocalDate.parse(until);
    }

    public LocalDate getFrom() {
        return this.from;
    }

    public LocalDate getUntil() {
        return this.until;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toFileString() {
        String isDone;
        if (this.getDoneStatus()) {
            isDone = "1";
        } else {
            isDone = "0";
        }
        return "E|" + isDone + "|" + this.getDescription() + "|" +
                this.from + "|" + this.until;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                " to: " + this.until.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
