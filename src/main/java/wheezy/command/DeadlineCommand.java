package wheezy.command;

import wheezy.tasklist.TaskList;

public class DeadlineCommand extends Command {
    private final String input;

    public DeadlineCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList taskList) {
        return taskList.handleDeadlineWithErrorCheck(input);
    }
}
