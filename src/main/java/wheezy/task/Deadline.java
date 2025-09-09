package wheezy.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import wheezy.priority.Priority;

/**
 * Represents a deadline task. Extends the Task class. A deadline contains
 * a description and a deadline date in LocalDate format.
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Constructor to create a deadline task.
     *
     * @param input String representing the description of the deadline.
     * @param deadline String representing the date of the deadline.
     */
    public Deadline(String input, String deadline) {
        super(input);
        this.deadline = LocalDate.parse(deadline);
    }

    public Deadline(String input, String deadline, Priority priority) {
        super(input, priority);
        this.deadline = LocalDate.parse(deadline);

    }

    /**
     * Getter to get the deadline.
     *
     * @return Date in LocalDate format.
     */
    public LocalDate getDeadline() {
        return this.deadline;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toFileString() {
        String isDone = this.getDoneStatus() ? "1" : "0";
        String priorityStr = getPriority() != null ? "|" + getPriority().toString() : "";
        return "D|" + isDone + "|" + this.getDescription() + "|" + this.deadline + priorityStr;
    }

    @Override
    public String toString() {
        String status = getDoneStatus() ? "[X]" : "[ ]";
        String priorityStr = getPriority() != null ? " (Priority: " + getPriority() + ")" : "";
        return "[D]" + status + " " + getDescription() + 
               " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")" +
               priorityStr;
    }
}
