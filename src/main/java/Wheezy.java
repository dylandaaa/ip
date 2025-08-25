import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Wheezy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        printWelcome();

        try {
            taskList = FileHandler.loadContent(taskList);
        } catch (FileNotFoundException e) {
            System.out.println("No previous tasks found!");
            try {
                FileHandler.createDirectory();
            } catch (IOException ioe) {
                System.out.println("Unable to create file");
            }
        }

        while (true) {
            try {
                String input = scanner.nextLine();
                CommandType commandType = CommandParser.parseCommand(input);

                switch (commandType) {
                case BYE -> {
                    System.out.println(Message.byeMessage());
                    break;
                }
                case LIST -> {
                    System.out.println(Message.listMessage(taskList));
                }
                case MARK -> {
                    CommandHandler.handleMark(input, taskList, true);
                }
                case UNMARK -> {
                    CommandHandler.handleMark(input, taskList, false);
                }
                case DELETE -> {
                    CommandHandler.handleDelete(input, taskList);
                }
                case ADD_TASK -> {
                    CommandHandler.handleAdd(input, taskList);
                }
                case TODO -> {
                    CommandHandler.handleTodoWithErrorCheck(input, taskList);
                }
                case DEADLINE -> {
                    CommandHandler.handleDeadlineWithErrorCheck(input, taskList);
                }
                case EVENT -> {
                    CommandHandler.handleEventWithErrorCheck(input, taskList);
                }
                case INVALID -> {
                    ErrorHandler.printError("I don't understand that command. Try 'list', 'todo <description>', 'delete <number>', or 'bye'.");
                }
                }

                if (commandType == CommandType.BYE) {
                    break;
                }
            } catch (Exception e) {
                ErrorHandler.printError("Something went wrong! Please try again");
            }
        }
    }

    private static void printWelcome() {
        String logo = """
                         __      __..__                                 \s
                        /  \\    /  \\  |__   ____   ____ __________.__.
                        \\   \\/\\/   /  |  \\_/ __ \\_/ __ \\\\____ |   |  |
                         \\        /|   Y  \\  ___/\\  ___/ /    /\\___  |
                          \\__/\\__/ |___|__/\\____/ \\____/ |____| /____|
                """;
        System.out.println("        __________________________________________________________");
        System.out.println("        Hello I'm\n" + logo);
        System.out.println("        What can I do for you?");
        System.out.println("        __________________________________________________________\n");
    }
}