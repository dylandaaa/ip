package wheezy.command;

import wheezy.tasklist.TaskList;

public class EventCommand extends Command {
    private final String input;

    public EventCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList taskList) {
        return taskList.handleEventWithErrorCheck(input);
    }
}
