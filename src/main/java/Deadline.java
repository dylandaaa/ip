public class Deadline extends Task {
    String deadline;

    public Deadline(String input, String deadline) {
        super(input);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: Sunday)";
    }
}
