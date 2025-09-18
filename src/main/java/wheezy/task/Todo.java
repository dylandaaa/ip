package wheezy.task;

import wheezy.priority.Priority;

/**
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
     * Constructor to create a Todo task with a priority.
     *
     * @param input String representing the description of the todo task.
     * @param priority Priority level of the task.
     */
    public Todo(String input, Priority priority) {
        super(input, priority);
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String toFileString() {
        String isDone = this.getDoneStatus() ? "1" : "0";
        String priorityStr = getPriority() != null ? "|" + getPriority().toString() : "";
        return "T|" + isDone + "|" + this.getDescription() + priorityStr;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
