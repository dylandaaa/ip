package wheezy.task;

import wheezy.priority.Priority;

/**
 * Represents a task to be added. Parent class of Deadline, Event,
 * and Todo.
 * A task contains a String as a description and a boolean
 * representing whether it is done or not. The class is abstract
 * and is therefore unable to be instantiated.
 */
abstract public class Task {
    private String description;
    private boolean isDone = false;
    private Priority priority;

    /**
     * Constructor to help child classes.
     *
     * @param description String representing the description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Constructor to help child classes with a priority value.
     *
     * @param description String representing the description of the task.
     * @param priority Priority level of the task.
     */
    public Task(String description, Priority priority) {
        this.description = description;
        this.priority = priority;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Getter to the get the description of the task.
     *
     * @return String representing the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Getter to get the priority of the task.
     *
     * @return Priority representing the priority of the task.
     */
    public Priority getPriority() {
        return this.priority;
    }

    /**
     * Getter to get the status of the task.
     *
     * @return Boolean representing the status of the task.
     */
    public boolean getDoneStatus() {
        return this.isDone;
    }

    /**
     * Abstract method to get the type of the class as a String.
     * @return String representing the type of the task.
     */
    abstract public String getType();

    /**
     * Abstract method to get the representation of the task in
     * file format.
     *
     * @return String representing the task in file format.
     */
    abstract public String toFileString();

    @Override
    public String toString() {
        String status = isDone ? "[X]" : "[ ]";
        String priorityStr = priority != null ? " (Priority: " + priority + ")" : "";
        return status + " " + description + priorityStr;
    }
}
