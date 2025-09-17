package wheezy.command;

import wheezy.tasklist.TaskList;

public class MarkCommand extends Command {
    private final String input;
    private final boolean mark;

    public MarkCommand(String input, boolean mark) {
        this.input = input;
        this.mark = mark;
    }

    @Override
    public String execute(TaskList taskList) {
        return taskList.handleMark(input, mark);
    }
}
