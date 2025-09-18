package wheezy.command;

import wheezy.tasklist.TaskList;

/**
 * Command that creates and adds a todo task.
 */
public class TodoCommand extends Command {
    private final String input;

    /**
     * Constructs a TodoCommand with the raw user input.
     *
     * @param input Raw user input for the todo command.
     */
    public TodoCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the todo command and returns the outcome message.
     *
     * @param taskList TaskList that stores all the tasks.
     * @return String representing the result of adding a todo task.
     */
    @Override
    public String execute(TaskList taskList) {
        return taskList.handleTodoWithErrorCheck(input);
    }
}
