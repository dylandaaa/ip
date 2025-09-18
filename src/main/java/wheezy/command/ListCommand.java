package wheezy.command;

import wheezy.tasklist.TaskList;
import wheezy.ui.Ui;

/**
 * Command that lists all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command and returns the formatted list of tasks.
     *
     * @param taskList TaskList that stores all the tasks.
     * @return String representing the list of tasks.
     */
    @Override
    public String execute(TaskList taskList) {
        return Ui.listMessage(taskList);
    }
}
