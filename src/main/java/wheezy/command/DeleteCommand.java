package wheezy.command;

import wheezy.tasklist.TaskList;

/**
 * Command that deletes a task by its number.
 */
public class DeleteCommand extends Command {
    private final String input;

    /**
     * Constructs a DeleteCommand with the raw user input.
     *
     * @param input Raw user input for the delete command.
     */
    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the delete command and returns the outcome message.
     *
     * @param taskList TaskList that stores all the tasks.
     * @return String representing the result of the deletion.
     */
    @Override
    public String execute(TaskList taskList) {
        return taskList.handleDelete(input);
    }
}
