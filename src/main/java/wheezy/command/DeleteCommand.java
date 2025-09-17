package wheezy.command;

import wheezy.tasklist.TaskList;

public class DeleteCommand extends Command {
    private final String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList taskList) {
        return taskList.handleDelete(input);
    }
}
