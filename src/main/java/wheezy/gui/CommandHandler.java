package wheezy.gui;

import java.time.format.DateTimeParseException;

import wheezy.command.Command;
import wheezy.parser.Parser;
import wheezy.tasklist.TaskList;
import wheezy.ui.Ui;

public class CommandHandler {
    public static String handleResponse(String input, TaskList taskList) {
        Command command = Parser.parseCommand(input);
        return handleCommand(command, taskList);
    }

    public static String handleCommand(Command command, TaskList taskList) {
        try {
            return command.execute(taskList);
        } catch (DateTimeParseException dtpe) {
            return Ui.printError("Date is in the incorrect format!: \n" +
                    "        It should be in <yyyy>-<mm>-<dd>.");
        } catch (Exception e) {
            return Ui.printError("Something went wrong! Please try again.");
        }
    }
}
