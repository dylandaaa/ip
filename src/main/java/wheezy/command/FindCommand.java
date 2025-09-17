package wheezy.command;

import wheezy.tasklist.TaskList;

public class FindCommand extends Command {
    private final String input;

    public FindCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList taskList) {
        return taskList.handleFindWithErrorCheck(input);
    }
}
