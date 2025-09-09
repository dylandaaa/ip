package wheezy.parser;

import wheezy.commandtype.CommandType;
import wheezy.priority.Priority;

/**
 * Used for manipulating string input. Used when trying to extract the command
 * type of the user input. Other methods are used to extract important
 * information from the string input such as description, dates, and task
 * number.
 */
public class Parser {
    /**
     * Returns command type of user input.
     * If the input is invalid, returns an invalid command type.
     *
     * @param input User input typed from the terminal.
     * @return Command type of the input.
     */

    private static final int DEADLINE_INDEX = 9;
    private static final int TODO_INDEX = 5;
    private static final int EVENT_INDEX = 6;
    private static final int FIND_INDEX = 9;

    public static CommandType parseCommand(String input) {
        String command = input.trim().toLowerCase();

        if (command.equals("bye")) {
            return CommandType.BYE;
        }
        if (command.equals("list")) {
            return CommandType.LIST;
        }
        if (command.startsWith("mark ")) {
            return CommandType.MARK;
        }
        if (command.startsWith("unmark ")) {
            return CommandType.UNMARK;
        }
        if (command.startsWith("todo ")) {
            return CommandType.TODO;
        }
        if (command.startsWith("event ")) {
            return CommandType.EVENT;
        }
        if (command.startsWith("deadline ")) {
            return CommandType.DEADLINE;
        }
        if (command.startsWith("delete ")) {
            return CommandType.DELETE;
        }
        if (command.startsWith("find ")) {
            return CommandType.FIND;
        }

        return CommandType.INVALID;
    }

    /**
     * Extracts the task number from inputs of the format <command> <number>.
     *
     * @param input User input typed in the terminal in the above format.
     * @return Task number specified in the input.
     * @throws NumberFormatException If format is invalid.
     */
    public static int extractTaskNumber(String input) throws NumberFormatException {
        // taskNumber returns in 0 format
        String[] parts = input.split(" ");
        if (parts.length != 2) {
            throw new NumberFormatException("Invalid Format");
        }
        return Integer.parseInt(parts[1]) - 1;
    }

    /**
     * Extracts the description of todo tasks.
     *
     * @param input User input that involves the todo command.
     * @return Description of the task.
     */
    public static String extractTodoDescription(String input) {
        String withoutCommand = input.substring(TODO_INDEX).trim();
        int priorityIndex = withoutCommand.indexOf("/priority ");
        if (priorityIndex == -1) {
            return withoutCommand;
        }
        return withoutCommand.substring(0, priorityIndex).trim();
    }

    /**
     * Extracts the description of deadline tasks.
     *
     * @param input User input that involves the deadline command.
     * @return Description of the task.
     */
    public static String extractDeadlineDescription(String input) {
        String withoutCommand = input.substring(DEADLINE_INDEX).trim();
        int byIndex = withoutCommand.indexOf("/by ");
        if (byIndex == -1) {
            throw new IllegalArgumentException("Deadline format should be: deadline <description> /by <date>");
        }
        return withoutCommand.substring(0, byIndex).trim();
    }

    /**
     * Extracts the date of deadline tasks.
     *
     * @param input User input that involves the deadline command.
     * @return Deadline of the task.
     */
    public static String extractDeadlineDate(String input) {
        String withoutCommand = input.substring(DEADLINE_INDEX).trim();
        int byIndex = withoutCommand.indexOf("/by ");
        int priorityIndex = withoutCommand.indexOf("/priority ");
        
        if (byIndex == -1) {
            throw new IllegalArgumentException("Deadline format should be: deadline <description> /by <date> [/priority <priority>]");
        }

        if (priorityIndex == -1) {
            return withoutCommand.substring(byIndex + 4).trim();
        } else {
            return withoutCommand.substring(byIndex + 4, priorityIndex).trim();
        }
    }

    /**
     * Extracts the description of event tasks.
     *
     * @param input User input that involves the event command.
     * @return Description of the event.
     */
    public static String extractEventDescription(String input) {
        String withoutCommand = input.substring(EVENT_INDEX).trim();
        int fromIndex = withoutCommand.indexOf("/from ");
        if (fromIndex == -1) {
            throw new IllegalArgumentException("Event format should be: event <description> /from <start> /to <end>");
        }
        return withoutCommand.substring(0, fromIndex).trim();
    }

    /**
     * Extracts the start time of event tasks.
     *
     * @param input User input that involves the event command.
     * @return Start time of the event.
     */
    public static String extractEventStartTime(String input) {
        String withoutCommand = input.substring(EVENT_INDEX).trim();
        int fromIndex = withoutCommand.indexOf("/from ");
        int toIndex = withoutCommand.indexOf("/to ");

        if (fromIndex == -1 || toIndex == -1) {
            throw new IllegalArgumentException("Event format should be: event <description> /from <start> /to <end>");
        }

        return withoutCommand.substring(fromIndex + 6, toIndex).trim();
    }

    /**
     * Extracts the end time of event tasks.
     *
     * @param input User input that involves the event command.
     * @return End time of the event.
     */
    public static String extractEventEndTime(String input) {
        String withoutCommand = input.substring(EVENT_INDEX).trim();
        int toIndex = withoutCommand.indexOf("/to ");
        int priorityIndex = withoutCommand.indexOf("/priority ");

        if (toIndex == -1) {
            throw new IllegalArgumentException(
                    "Event format should be: event <description> /from <start> /to <end> [/priority <priority>]");
        }

        if (priorityIndex == -1) {
            return withoutCommand.substring(toIndex + 4).trim();
        } else {
            return withoutCommand.substring(toIndex + 4, priorityIndex).trim();
        }
    }

    /**
     * Extracts the task description from find commands.
     *
     * @param input String representing the user input containing the find command.
     * @return Task description to be searched.
     */
    public static String extractFindDescription(String input) {
        return input.substring(FIND_INDEX).trim();
    }

    /**
     * Extracts the priority of a task.
     *
     * @param input String representing the user input containing a priority.
     * @return Priority of a task.
     */
    public static Priority extractPriority(String input) {
        int priorityIndex = input.indexOf("/priority ");

        if (priorityIndex != -1) {
            String priorityStr = input.substring(priorityIndex + 10).trim().toLowerCase();
            switch (priorityStr) {
                case "high":
                    return Priority.HIGH;
                case "medium":
                    return Priority.MEDIUM;
                case "low":
                    return Priority.LOW;
                default:
                    throw new IllegalArgumentException("Priority must be high, medium, or low");
            }
        }

        return null; // No priority specified
    }
}
