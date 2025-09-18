package wheezy.command;

import wheezy.tasklist.TaskList;

/**
 * Abstract base class for all commands.
 */
public abstract class Command {
    /**
     * Executes this command against the given TaskList and returns the response string.
     *
     * @param taskList TaskList that stores all the tasks.
     * @return String representing the output message for this command.
     */
    public abstract String execute(TaskList taskList);

    /**
     * Whether executing this command should exit the application loop.
     *
     * @return boolean indicating if the application should exit after this command.
     */
    public boolean isExit() {
        return false;
    }
}
