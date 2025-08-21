import java.util.ArrayList;

public class CommandHandler {
    public static void handleMark(String input, ArrayList<Task> taskList, boolean markAsDone) {
        try {
            int taskNumber = CommandParser.extractTaskNumber(input);

            if (taskList.isEmpty()) {
                ErrorHandler.printError("Your task list is empty! Add some tasks first.");
                return;
            }

            if (taskNumber < 0 || taskNumber >= taskList.size()) {
                ErrorHandler.printError("Task number " + (taskNumber + 1) + " doesn't exist! You have " + taskList.size() + " task(s).");
                return;
            }

            Task task = taskList.get(taskNumber);
            if (markAsDone) {
                task.markDone();
            } else {
                task.unmarkDone();
            }

            String action = markAsDone ? " done" : " not done yet";
            System.out.println("        ____________________________________________________________");
            System.out.println("        Nice! I've marked this task as" + action + ":");
            System.out.println("          " + task);
            System.out.println("        ____________________________________________________________");

        } catch (NumberFormatException e) {
            String command = markAsDone ? "mark" : "unmark";
            ErrorHandler.printError("Invalid task number! Usage: " + command + " <task_number>");
        }
    }

    public static void handleDelete(String input, ArrayList<Task> taskList) {
        try {
            int taskNumber = CommandParser.extractTaskNumber(input);

            if (taskList.isEmpty()) {
                ErrorHandler.printError("Your task list is empty! Nothing to delete.");
                return;
            }

            if (taskNumber < 0 || taskNumber >= taskList.size()) {
                ErrorHandler.printError("Task number " + (taskNumber + 1) + " doesn't exist! You have " + taskList.size() + " task(s).");
                return;
            }

            Task deletedTask = taskList.remove(taskNumber);
            System.out.println(Message.deleteMessage(deletedTask, taskList.size()));

        } catch (NumberFormatException e) {
            ErrorHandler.printError("Invalid task number! Usage: delete <task_number>");
        }
    }

    public static void handleAdd(String input, ArrayList<Task> taskList) {
        if (input.trim().isEmpty()) {
            ErrorHandler.printError("Task description cannot be empty!");
            return;
        }

        Task newTask = new Task(input);
        taskList.add(newTask);
        System.out.println(Message.addMessage(newTask, taskList.size()));
    }

    public static void handleTodoWithErrorCheck(String input, ArrayList<Task> taskList) {
        try {
            String description = CommandParser.extractTodoDescription(input);
            if (description.trim().isEmpty()) {
                ErrorHandler.printError("Todo description cannot be empty! Usage: todo <description>");
                return;
            }

            Task newTask = new Todo(description);
            taskList.add(newTask);
            System.out.println(Message.addMessage(newTask, taskList.size()));
        } catch (StringIndexOutOfBoundsException e) {
            ErrorHandler.printError("Todo description is missing! Usage: todo <description>");
        }
    }

    public static void handleDeadlineWithErrorCheck(String input, ArrayList<Task> taskList) {
        try {
            String description = CommandParser.extractDeadlineDescription(input);
            String deadline = CommandParser.extractDeadlineDate(input);

            if (description.trim().isEmpty()) {
                ErrorHandler.printError("Deadline description cannot be empty! Usage: deadline <description> /by <date>");
                return;
            }
            if (deadline.trim().isEmpty()) {
                ErrorHandler.printError("Deadline date cannot be empty! Usage: deadline <description> /by <date>");
                return;
            }

            Task newTask = new Deadline(description, deadline);
            taskList.add(newTask);
            System.out.println(Message.addMessage(newTask, taskList.size()));
        } catch (IllegalArgumentException e) {
            ErrorHandler.printError("Invalid deadline format! Usage: deadline <description> /by <date>");
        } catch (StringIndexOutOfBoundsException e) {
            ErrorHandler.printError("Deadline command is incomplete! Usage: deadline <description> /by <date>");
        }
    }

    public static void handleEventWithErrorCheck(String input, ArrayList<Task> taskList) {
        try {
            String description = CommandParser.extractEventDescription(input);
            String from = CommandParser.extractEventStartTime(input);
            String until = CommandParser.extractEventEndTime(input);

            if (description.trim().isEmpty()) {
                ErrorHandler.printError("Event description cannot be empty! Usage: event <description> /from <start> /to <end>");
                return;
            }
            if (from.trim().isEmpty()) {
                ErrorHandler.printError("Event start time cannot be empty! Usage: event <description> /from <start> /to <end>");
                return;
            }
            if (until.trim().isEmpty()) {
                ErrorHandler.printError("Event end time cannot be empty! Usage: event <description> /from <start> /to <end>");
                return;
            }

            Task newTask = new Event(description, from, until);
            taskList.add(newTask);
            System.out.println(Message.addMessage(newTask, taskList.size()));
        } catch (IllegalArgumentException e) {
            ErrorHandler.printError("Invalid event format! Usage: event <description> /from <start> /to <end>");
        } catch (StringIndexOutOfBoundsException e) {
            ErrorHandler.printError("Event command is incomplete! Usage: event <description> /from <start> /to <end>");
        }
    }
}
