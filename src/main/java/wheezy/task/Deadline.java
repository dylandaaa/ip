package wheezy.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate deadline;

    public Deadline(String input, String deadline) {
        super(input);
        this.deadline = LocalDate.parse(deadline);
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }

    @Override
    public String getType() {
        return "D";
    }

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

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
