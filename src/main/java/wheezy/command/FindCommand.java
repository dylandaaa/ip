package wheezy.command;

import wheezy.tasklist.TaskList;

/**
 * Command that finds tasks matching a keyword.
 */
public class FindCommand extends Command {
    private final String input;

    /**
     * Constructs a FindCommand with the raw user input.
     *
     * @param input Raw user input for the find command.
     */
    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the find command and returns the search results.
     *
     * @param taskList TaskList that stores all the tasks.
     * @return String representing the list of matching tasks.
     */
    @Override
    public String execute(TaskList taskList) {
        return taskList.handleFindWithErrorCheck(input);
    }
}
