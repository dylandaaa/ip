package wheezy.command;

import wheezy.tasklist.TaskList;

public class TodoCommand extends Command {
    private final String input;

    public TodoCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList taskList) {
        return taskList.handleTodoWithErrorCheck(input);
    }
}
