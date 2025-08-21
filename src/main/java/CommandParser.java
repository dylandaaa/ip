public class CommandParser {
    public static CommandType parseCommand(String input) {
        String command = input.trim().toLowerCase();

        if (command.equals("bye")) return CommandType.BYE;
        if (command.equals("list")) return CommandType.LIST;
        if (command.startsWith("mark ")) return CommandType.MARK;
        if (command.startsWith("unmark ")) return CommandType.UNMARK;
        if (command.trim().isEmpty()) return CommandType.INVALID;

        return CommandType.ADD_TASK;
    }

    public static int extractTaskNumber(String input) throws NumberFormatException {
        String[] parts = input.split(" ");
        if (parts.length != 2) throw new NumberFormatException("Invalid Format");
        return Integer.parseInt(parts[1]) - 1;
    }
}
