public class Todo extends Task {
    public Todo(String input) {
        super(input);
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String toFileString() {
        String isDone;
        if (this.getDoneStatus()) {
            isDone = "1";
        } else {
            isDone = "0";
        }
        return "T|" + isDone + "|" + this.getDescription();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
