package wheezy.command;

import wheezy.tasklist.TaskList;
import wheezy.ui.Ui;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList taskList) {
        return Ui.listMessage(taskList);
    }
}
