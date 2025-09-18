package wheezy.gui;

import java.time.format.DateTimeParseException;

import wheezy.command.Command;
import wheezy.parser.Parser;
import wheezy.tasklist.TaskList;
import wheezy.ui.Ui;

/**
 * Handles user input by parsing it into commands and executing them.
 */
public class CommandHandler {
    /**
     * Parses raw user input and returns the response from executing the command.
     *
     * @param input Raw user input string.
     * @param taskList TaskList that stores all the tasks.
     * @return String response from executing the parsed command.
     */
    public static String handleResponse(String input, TaskList taskList) {
        Command command = Parser.parseCommand(input);
        return handleCommand(command, taskList);
    }

    /**
     * Executes a given command and handles common exceptions, returning a user-friendly message.
     *
     * @param command Command to be executed.
     * @param taskList TaskList that stores all the tasks.
     * @return String response from executing the command.
     */
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
