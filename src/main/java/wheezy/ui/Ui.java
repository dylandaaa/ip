package wheezy.ui;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

import wheezy.tasklist.TaskList;
import wheezy.commandtype.CommandType;
import wheezy.parser.Parser;
import wheezy.task.Task;

/*
 * Represents the UI that the user will interact with. Everything that
 * displayed to the user is represented as a method in this class.
 */
public class Ui {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Starts a loop that constantly asks for user input unless user types "bye".
     * User input will be parsed and an action will be done corresponding to
     * the command type.
     *
     * @param taskList TaskList that stores all the tasks.
     */
    public static void startUi(TaskList taskList) {
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
                    Ui.printError("I don't understand that command. Try 'list', 'todo <description>', 'delete <number>', or 'bye'.");
                }
                }


                if (commandType == CommandType.BYE) {
                    break;
                }
            } catch (DateTimeParseException dtpe) {
                Ui.printError("Date is in the incorrect format!: \n" +
                        "        It should be in <yyyy>-<mm>-<dd>.");
            } catch (Exception e) {
                Ui.printError("Something went wrong! Please try again.");
            }
        }
    }

    /**
     * Helper function that displays the welcome message.
     */
    public static void printWelcome() {
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

    /**
     * Helper function that displays the bye message.
     *
     * @return String representing the bye message.
     */
    public static String byeMessage() {
        return """
                            ____________________________________________________________
                            Bye, see you around!
                            ____________________________________________________________
                    """;
    }

    /**
     * Helper function that lists the tasks in the TaskList.
     *
     * @param taskList TaskList that stores all the tasks.
     * @return String representing the list of tasks.
     */
    public static String listMessage(TaskList taskList) {

        StringBuilder message = new StringBuilder();
        message.append("        ____________________________________________________________\n");

        if (taskList.isEmpty()) {
            message.append("        Your task list is empty! Add some tasks to get started.\n");
        } else {
            message.append("        Here are the tasks in your list:\n");
            for (int i = 0; i < taskList.size(); i++) {
                message.append("        ")
                        .append((i + 1))
                        .append(".")
                        .append(taskList.get(i))
                        .append("\n");
            }
        }

        message.append("        ____________________________________________________________\n");
        return message.toString();
    }

    /**
     * Helper function that displays a message when a task is added.
     *
     * @param task Task to be added.
     * @param counter Integer representing how many tasks are in the TaskList.
     * @return String representing the add message.
     */
    public static String addMessage(Task task, int counter) {
        return "       ____________________________________________________________\n" +
                "       Great! I've added this task:\n" +
                "       " + task + "\n" +
                "       Now you have " + counter + " tasks in the list\n" +
                "       ____________________________________________________________\n";
    }

    /**
     * Helper function that displays a message when a task is deleted.
     *
     * @param deletedTask Task to be deleted.
     * @param totalTasks Integer representing how many tasks are left in the TaskList.
     * @return String representing the delete message.
     */
    public static String deleteMessage(Task deletedTask, int totalTasks) {
        return "        ____________________________________________________________\n" +
                "        Alrighty, I've removed this task:\n" +
                "          " + deletedTask + "\n" +
                "        Now you have " + totalTasks + " task(s) in the list.\n" +
                "        ____________________________________________________________\n";
    }

    /**
     * Helper function that displays a message when a task is marked/unmarked.
     *
     * @param markAsDone Boolean representing to mark/unmark.
     * @param task Task to be marked.
     * @return String representing the mark/unmark message.
     */
    public static String markAsDoneMessage(boolean markAsDone, Task task) {
        String action = markAsDone ? " done" : " not done yet";
        return "        ____________________________________________________________\n" +
                "        Nice! I've marked this task as" + action + ":\n" +
                "        " + task + "\n" +
                "        ____________________________________________________________\n";
    }

    /**
     * Helper function that displays an error message.
     *
     * @param errorMessage String representing the error message.
     */
    public static void printError(String errorMessage) {
        System.out.println("        ____________________________________________________________");
        System.out.println("        " + errorMessage);
        System.out.println("        ____________________________________________________________");
    }
}
