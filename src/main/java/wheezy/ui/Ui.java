package wheezy.ui;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import wheezy.tasklist.TaskList;
import wheezy.command.Command;
import wheezy.parser.Parser;
import wheezy.task.Task;

/**
 * Represents the UI that the user will interact with. Everything that
 * displayed to the user is represented as a method in this class.
 */
public class Ui {
    private static Scanner scanner = new Scanner(System.in);
    private static final String dashedLines = "________________________________________________________\n";
    private static final String indentation = "";

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
                Command command = Parser.parseCommand(input);

                String output = command.execute(taskList);
                System.out.println(output);

                if (command.isExit()) {
                    break;
                }
            } catch (DateTimeParseException dtpe) {
                System.out.println(Ui.printError("Date is in the incorrect format!: \n" +
                        "        It should be in <yyyy>-<mm>-<dd>."));
            } catch (Exception e) {
                System.out.println(Ui.printError("Something went wrong! Please try again."));
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
                        \\   \\//\\/   /  |  \\_/ __ \\_/ __ \\\\____ |   |  |
                         \\        /|   Y  \\  ___/\\  ___/ /    /\\___  |
                          \\__/\\__/ |___|__/\\____/ \\____/ |____| /____|
                """;
        System.out.println(dashedLines);
        System.out.println("        Hello I'm\n" + logo);
        System.out.println("        What can I do for you?");
        System.out.println(dashedLines);
    }

    /**
     * Helper function that displays the bye message.
     *
     * @return String representing the bye message.
     */
    public static String byeMessage() {
        return dashedLines + indentation + "See you around!\n" + dashedLines;
    }

    /**
     * Helper function that lists the tasks in the TaskList.
     *
     * @param taskList TaskList that stores all the tasks.
     * @return String representing the list of tasks.
     */
    public static String listMessage(TaskList taskList) {

        StringBuilder message = new StringBuilder();
        message.append(dashedLines);

        if (taskList.isEmpty()) {
            message.append(indentation);
            message.append("Your task list is empty! Add some tasks to get started.\n");
        }

        message.append(indentation);
        message.append("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            message.append(indentation)
                    .append((i + 1))
                    .append(".")
                    .append(taskList.get(i))
                    .append("\n");
        }

        message.append(dashedLines);
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
        return dashedLines +
                indentation + "Great! I've added this task:\n" +
                indentation + task + "\n" +
                indentation + "Now you have " + counter + " tasks in the list\n" +
                dashedLines;
    }

    /**
     * Helper function that displays a message when a task is deleted.
     *
     * @param deletedTask Task to be deleted.
     * @param totalTasks Integer representing how many tasks are left in the TaskList.
     * @return String representing the delete message.
     */
    public static String deleteMessage(Task deletedTask, int totalTasks) {
        return dashedLines +
                indentation + "Alrighty, I've removed this task:\n" +
                indentation + deletedTask + "\n" +
                indentation + "Now you have " + totalTasks + " task(s) in the list.\n" +
                dashedLines;
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
        return dashedLines +
                indentation + "Nice! I've marked this task as" + action + ":\n" +
                indentation + task + "\n" +
                dashedLines;
    }

    /**
     * Helper function that displays the results of a find command.
     *
     * @param tasks ArrayList of tasks that match the search criteria.
     * @return String representing the formatted search results.
     */
    public static String findMessage(ArrayList<Task> tasks) {
        StringBuilder message = new StringBuilder();
        message.append(dashedLines);
        int counter = 1;

        if (tasks.isEmpty()) {
            message.append("No tasks found!");
        }
        for (Task task : tasks) {
            message.append(indentation);
            message.append(counter).append(".");
            message.append(task);
            message.append("\n");
            counter++;
        }

        message.append(dashedLines);
        return message.toString();
    }

    /**
     * Helper function that displays an error message.
     *
     * @param errorMessage String representing the error message.
     * @return String representing the formatted error message.
     */
    public static String printError(String errorMessage) {
        return dashedLines +
                indentation + errorMessage + "\n" +
                dashedLines;
    }
}
