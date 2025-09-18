package wheezy.command;

import wheezy.tasklist.TaskList;
import wheezy.ui.Ui;

/**
 * Command representing an invalid or unrecognized user input.
 */
public class InvalidCommand extends Command {
    /**
     * Executes the invalid command and returns a helpful error message.
     *
     * @param taskList TaskList that stores all the tasks.
     * @return String representing the error message for invalid commands.
     */
    @Override
    public String execute(TaskList taskList) {
        return Ui.printError("I don't understand that command. " +
                "Try 'list', 'todo <description>', 'delete <number>', or 'bye'.");
    }
}
