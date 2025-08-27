package wheezy.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
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

    /**
     * Getter to get the deadline.
     *
     * @return Date in LocalDate format.
     */
    public LocalDate getDeadline() {
        return this.deadline;
    }

    /**
     * Returns the type of the task as a String.
     *
     * @return String representing the type of task.
     */
    @Override
    public String getType() {
        return "D";
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
        return "D|" + isDone + "|" + this.getDescription() + "|" +
                this.deadline;
    }

    /**
     * Overrides the toString method of the Task class.
     * Shows deadline in the following format: MMM dd yyyy.
     *
     * @return String to be displayed representing the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
