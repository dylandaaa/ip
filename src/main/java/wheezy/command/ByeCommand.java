package wheezy.command;

import wheezy.tasklist.TaskList;
import wheezy.ui.Ui;

public class ByeCommand extends Command {
    @Override
    public String execute(TaskList taskList) {
        return Ui.byeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
