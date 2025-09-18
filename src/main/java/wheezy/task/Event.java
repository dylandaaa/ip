package wheezy.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import wheezy.priority.Priority;

/**
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
     * Constructor to create an event task with a priority.
     *
     * @param input String representing the description of the event.
     * @param from String representing the "from" date.
     * @param until String representing the "to" date.
     * @param priority Priority level of the task.
     */
    public Event(String input, String from, String until, Priority priority) {
        super(input, priority);
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

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toFileString() {
        String isDone = this.getDoneStatus() ? "1" : "0";
        String priorityStr = getPriority() != null ? "|" + getPriority().toString() : "";
        return "E|" + isDone + "|" + this.getDescription() + "|" + this.from + "|" + this.until + priorityStr;
    }

    @Override
    public String toString() {
        String status = getDoneStatus() ? "[X]" : "[ ]";
        String priorityStr = getPriority() != null ? " (Priority: " + getPriority() + ")" : "";
        return "[E]" + status + " " + getDescription() + 
               " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + 
               " to: " + this.until.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")" +
               priorityStr;
    }
}
