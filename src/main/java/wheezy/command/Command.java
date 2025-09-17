package wheezy.command;

import wheezy.tasklist.TaskList;

/**
 * Abstract base class for all commands.
 */
public abstract class Command {
    /**
     * Executes this command against the given TaskList and returns the response string.
     */
    public abstract String execute(TaskList taskList);

    /**
     * Whether executing this command should exit the application loop.
     */
    public boolean isExit() {
        return false;
    }
}
