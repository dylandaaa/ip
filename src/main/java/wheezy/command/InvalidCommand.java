package wheezy.command;

import wheezy.tasklist.TaskList;
import wheezy.ui.Ui;

public class InvalidCommand extends Command {
    @Override
    public String execute(TaskList taskList) {
        return Ui.printError("I don't understand that command. " +
                "Try 'list', 'todo <description>', 'delete <number>', or 'bye'.");
    }
}
