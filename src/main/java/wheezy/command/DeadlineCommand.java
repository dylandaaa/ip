package wheezy.command;

import wheezy.tasklist.TaskList;

/**
 * Command that creates and adds a deadline task.
 */
public class DeadlineCommand extends Command {
    private final String input;

    /**
     * Constructs a DeadlineCommand with the raw user input.
     *
     * @param input Raw user input for the deadline command.
     */
    public DeadlineCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the deadline command and returns the outcome message.
     *
     * @param taskList TaskList that stores all the tasks.
     * @return String representing the result of adding a deadline task.
     */
    @Override
    public String execute(TaskList taskList) {
        return taskList.handleDeadlineWithErrorCheck(input);
    }
}
