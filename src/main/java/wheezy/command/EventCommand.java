package wheezy.command;

import wheezy.tasklist.TaskList;

/**
 * Command that creates and adds an event task.
 */
public class EventCommand extends Command {
    private final String input;

    /**
     * Constructs an EventCommand with the raw user input.
     *
     * @param input Raw user input for the event command.
     */
    public EventCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the event command and returns the outcome message.
     *
     * @param taskList TaskList that stores all the tasks.
     * @return String representing the result of adding an event task.
     */
    @Override
    public String execute(TaskList taskList) {
        return taskList.handleEventWithErrorCheck(input);
    }
}
