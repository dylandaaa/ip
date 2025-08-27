package wheezy.task;

/*
 * Represents a todo task. Extends the task class.
 */
public class Todo extends Task {

    /**
     * Constructor to create a Todo task.
     *
     * @param input String representing the description of the todo task.
     */
    public Todo(String input) {
        super(input);
    }

    /**
     * Returns the type of the task as a String.
     *
     * @return String representing the type of the task.
     */
    @Override
    public String getType() {
        return "T";
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
        return "T|" + isDone + "|" + this.getDescription();
    }

    /**
     * Overrides the toString method to include the type of the task.
     *
     * @return String including the type of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
