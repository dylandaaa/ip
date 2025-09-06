package wheezy.gui;

import java.time.format.DateTimeParseException;

import wheezy.commandtype.CommandType;
import wheezy.parser.Parser;
import wheezy.tasklist.TaskList;
import wheezy.ui.Ui;

public class CommandHandler {
    public static String handleResponse(String input, TaskList taskList) {
        CommandType commandType = Parser.parseCommand(input);

        return handleCommand(commandType, taskList, input);
    }

    public static String handleCommand(CommandType commandType, TaskList taskList, String input) {
        try {
            switch (commandType) {
            case BYE -> {
                return Ui.byeMessage();
            }
            case LIST -> {
                return Ui.listMessage(taskList);
            }
            case MARK -> {
                return taskList.handleMark(input, true);
            }
            case UNMARK -> {
                return taskList.handleMark(input, false);
            }
            case DELETE -> {
                return taskList.handleDelete(input);
            }
            case ADD_TASK -> {
                return taskList.handleAdd(input);
            }
            case TODO -> {
                return taskList.handleTodoWithErrorCheck(input);
            }
            case DEADLINE -> {
                return taskList.handleDeadlineWithErrorCheck(input);
            }
            case EVENT -> {
                return taskList.handleEventWithErrorCheck(input);
            }
            case FIND -> {
                return taskList.handleFindWithErrorCheck(input);
            }
            case INVALID -> {
                return Ui.printError("I don't understand that command. " +
                        "Try 'list', 'todo <description>', 'delete <number>', or 'bye'.");
            }
            }

        } catch (DateTimeParseException dtpe) {
            return Ui.printError("Date is in the incorrect format!: \n" +
                    "        It should be in <yyyy>-<mm>-<dd>.");
        } catch (Exception e) {
            return Ui.printError("Something went wrong! Please try again.");
        }
        return input; // Should not reach here
    }
}
