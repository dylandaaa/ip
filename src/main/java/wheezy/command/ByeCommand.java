package wheezy.command;

import wheezy.tasklist.TaskList;
import wheezy.ui.Ui;

/**
 * Command that handles exiting the application.
 */
public class ByeCommand extends Command {
    /**
     * Executes the bye command and returns the farewell message.
     *
     * @param taskList TaskList that stores all the tasks.
     * @return String representing the bye message.
     */
    @Override
    public String execute(TaskList taskList) {
        return Ui.byeMessage();
    }

    /**
     * Indicates that executing this command should terminate the application loop.
     *
     * @return true to indicate the application should exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
