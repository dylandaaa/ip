public class Event extends Task {
    private String from;
    private String until;

    public Event(String input, String from, String until) {
        super(input);
        this.from = from;
        this.until = until;
    }

    public String getFrom() {
        return this.from;
    }

    public String getUntil() {
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
        return "E|" + isDone + "|" + this.getDescription() + "|" + this.from + "|" + this.until;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.until + ")";
    }
}
