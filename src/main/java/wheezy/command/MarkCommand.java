package wheezy.command;

import wheezy.tasklist.TaskList;

/**
 * Command that marks or unmarks a task as done.
 */
public class MarkCommand extends Command {
    private final String input;
    private final boolean mark;

    /**
     * Constructs a MarkCommand to mark or unmark a task.
     *
     * @param input Raw user input containing the task number.
     * @param mark  Boolean indicating whether to mark (true) or unmark (false).
     */
    public MarkCommand(String input, boolean mark) {
        this.input = input;
        this.mark = mark;
    }

    /**
     * Executes the mark/unmark command against the provided task list.
     *
     * @param taskList TaskList that stores all the tasks.
     * @return String representing the result of the operation.
     */
    @Override
    public String execute(TaskList taskList) {
        return taskList.handleMark(input, mark);
    }
}
