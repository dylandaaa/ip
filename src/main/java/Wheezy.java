import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Wheezy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tempList = new ArrayList<>();
        Ui ui = new Ui();
        TaskList taskList = new TaskList(tempList);

        try {
            taskList = Storage.loadContent(taskList);
        } catch (FileNotFoundException e) {
            System.out.println("No previous tasks found!");
            try {
                Storage.createDirectory();
            } catch (IOException ioe) {
                System.out.println("Unable to create file");
            }
        }

        while (true) {
            try {
                String input = scanner.nextLine();
                CommandType commandType = Parser.parseCommand(input);

                switch (commandType) {
                case BYE -> {
                    System.out.println(Ui.byeMessage());
                    break;
                }
                case LIST -> {
                    System.out.println(Ui.listMessage(taskList));
                }
                case MARK -> {
                    taskList.handleMark(input, true);
                }
                case UNMARK -> {
                    taskList.handleMark(input, false);
                }
                case DELETE -> {
                    taskList.handleDelete(input);
                }
                case ADD_TASK -> {
                    taskList.handleAdd(input);
                }
                case TODO -> {
                    taskList.handleTodoWithErrorCheck(input);
                }
                case DEADLINE -> {
                    taskList.handleDeadlineWithErrorCheck(input);
                }
                case EVENT -> {
                    taskList.handleEventWithErrorCheck(input);
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
}