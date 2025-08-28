package wheezy.ui;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import wheezy.tasklist.TaskList;
import wheezy.commandtype.CommandType;
import wheezy.parser.Parser;
import wheezy.task.Task;

public class Ui {
    private static Scanner scanner = new Scanner(System.in);
    private static final String dashedLines = "        __________________________________________________________\n";

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
                case FIND -> {
                    taskList.handleFindWithErrorCheck(input);
                }
                case INVALID -> {
                    ErrorHandler.printError("I don't understand that command. Try 'list', 'todo <description>', 'delete <number>', or 'bye'.");
                }
                }


                if (commandType == CommandType.BYE) {
                    break;
                }
            } catch (DateTimeParseException dtpe) {
                ErrorHandler.printError("Date is in the incorrect format!: \n" +
                        "        It should be in <yyyy>-<mm>-<dd>.");
            } catch (Exception e) {
                ErrorHandler.printError("Something went wrong! Please try again.");
            }
        }
    }

    public static void printWelcome() {
        String logo = """
                         __      __..__                                 \s
                        /  \\    /  \\  |__   ____   ____ __________.__.
                        \\   \\/\\/   /  |  \\_/ __ \\_/ __ \\\\____ |   |  |
                         \\        /|   Y  \\  ___/\\  ___/ /    /\\___  |
                          \\__/\\__/ |___|__/\\____/ \\____/ |____| /____|
                """;
        System.out.println(dashedLines);
        System.out.println("        Hello I'm\n" + logo);
        System.out.println("        What can I do for you?");
        System.out.println(dashedLines);
    }

    public static String byeMessage() {
        return dashedLines + "        Bye, see you around!\n" + dashedLines;
    }

    public static String listMessage(TaskList taskList) {

        StringBuilder message = new StringBuilder();
        message.append(dashedLines);

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

        message.append(dashedLines);
        return message.toString();
    }

    public static String addMessage(Task task, int counter) {
        return dashedLines +
                "       Great! I've added this task:\n" +
                "       " + task + "\n" +
                "       Now you have " + counter + " tasks in the list\n" +
                dashedLines;
    }

    public static String deleteMessage(Task deletedTask, int totalTasks) {
        return dashedLines +
                "        Alrighty, I've removed this task:\n" +
                "          " + deletedTask + "\n" +
                "        Now you have " + totalTasks + " task(s) in the list.\n" +
                dashedLines;
    }

    public static String markAsDoneMessage(boolean markAsDone, Task task) {
        String action = markAsDone ? " done" : " not done yet";
        return dashedLines +
                "        Nice! I've marked this task as" + action + ":\n" +
                "        " + task + "\n" +
                dashedLines;
    }

    public static String findMessage(ArrayList<Task> tasks) {
        StringBuilder message = new StringBuilder();
        message.append(dashedLines);
        int counter = 1;

        if (tasks.isEmpty()) {
            message.append("No tasks found!");
        } else {
            for (Task task : tasks) {
                message.append("        ");
                message.append(counter).append(".");
                message.append(task);
                message.append("\n");
                counter++;
            }
        }

        message.append(dashedLines);
        return message.toString();
    }
}
