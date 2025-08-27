package wheezy.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
 * Represents an event task. Extends the Task class. An event contains
 * a description and a "from" date and a "to" date in LocalDate format.
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate until;

    /**
     * Constructor to create an event task.
     *
     * @param input String representing the description of the event.
     * @param from String representing the "from" date.
     * @param until String representing the "to" date.
     */
    public Event(String input, String from, String until) {
        super(input);
        this.from = LocalDate.parse(from);
        this.until = LocalDate.parse(until);
    }

    /**
     * Getter to get the "from" date in LocalDate format.
     *
     * @return "From" date in LocalDate format.
     */
    public LocalDate getFrom() {
        return this.from;
    }

    /**
     * Getter to get the "to" date in LocalDate format.
     *
     * @return "To" date in LocalDate format.
     */
    public LocalDate getUntil() {
        return this.until;
    }

    /**
     * Returns the type of task as a string.
     *
     * @return String representing the type of the task.
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Creates a file-format representation of the task.
     *
     * @return String representing the file-format representation.
     */
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

    /**
     * Overrides the toString method of the Task class.
     * Shows event dates in the following format: MMM dd yyyy.
     *
     * @return String to be displayed representing the deadline.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                " to: " + this.until.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
