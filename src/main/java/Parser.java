public class Parser {
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
        if (command.trim().isEmpty()) {
            return CommandType.INVALID;
        }

        return CommandType.ADD_TASK;
    }

    public static int extractTaskNumber(String input) throws NumberFormatException {
        // taskNumber returns in 0 format
        String[] parts = input.split(" ");
        if (parts.length != 2) throw new NumberFormatException("Invalid Format");
        return Integer.parseInt(parts[1]) - 1;
    }

    public static String extractTodoDescription(String input) {
        return input.substring(5).trim();
    }

    public static String extractDeadlineDescription(String input) {
        String withoutCommand = input.substring(9).trim();
        int byIndex = withoutCommand.indexOf("/by ");
        if (byIndex == -1) {
            throw new IllegalArgumentException("Deadline format should be: deadline <description> /by <date>");
        }
        return withoutCommand.substring(0, byIndex).trim();
    }

    public static String extractDeadlineDate(String input) {
        String withoutCommand = input.substring(9).trim();
        int byIndex = withoutCommand.indexOf("/by ");
        if (byIndex == -1) {
            throw new IllegalArgumentException("Deadline format should be: deadline <description> /by <date>");
        }
        return withoutCommand.substring(byIndex + 4).trim(); // Get everything after "/by "
    }

    public static String extractEventDescription(String input) {
        String withoutCommand = input.substring(6).trim();
        int fromIndex = withoutCommand.indexOf("/from ");
        if (fromIndex == -1) {
            throw new IllegalArgumentException("Event format should be: event <description> /from <start> /to <end>");
        }
        return withoutCommand.substring(0, fromIndex).trim();
    }

    public static String extractEventStartTime(String input) {
        String withoutCommand = input.substring(6).trim();
        int fromIndex = withoutCommand.indexOf("/from ");
        int toIndex = withoutCommand.indexOf("/to ");

        if (fromIndex == -1 || toIndex == -1) {
            throw new IllegalArgumentException("Event format should be: event <description> /from <start> /to <end>");
        }

        return withoutCommand.substring(fromIndex + 6, toIndex).trim();
    }

    public static String extractEventEndTime(String input) {
        String withoutCommand = input.substring(6).trim();
        int toIndex = withoutCommand.indexOf("/to ");

        if (toIndex == -1) {
            throw new IllegalArgumentException("Event format should be: event <description> /from <start> /to <end>");
        }

        return withoutCommand.substring(toIndex + 4).trim();
    }

}
