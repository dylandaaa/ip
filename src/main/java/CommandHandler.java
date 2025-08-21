public class CommandHandler {
    public static void handleMark(String input, Task[] list, int counter, boolean markAsDone) {
        try {
            int taskNumber = CommandParser.extractTaskNumber(input);

            if (counter == 0) {
                ErrorHandler.printError("Your task list is empty! Add some tasks first.");
                return;
            }

            if (taskNumber < 0 || taskNumber >= counter) {
                ErrorHandler.printError("Task number " + (taskNumber + 1) + " doesn't exist! You have " + counter + " task(s).");
                return;
            }

            if (markAsDone) {
                list[taskNumber].markDone();
            } else {
                list[taskNumber].unmarkDone();
            }

            String action = markAsDone ? " done" : " not done yet";
            System.out.println("        ____________________________________________________________");
            System.out.println("        Okay, I've marked this task as" + action + ":");
            System.out.println("          " + list[taskNumber]);
            System.out.println("        ____________________________________________________________");

        } catch (NumberFormatException e) {
            String command = markAsDone ? "mark" : "unmark";
            ErrorHandler.printError("Invalid task number! Usage: " + command + " <task_number>");
        } catch (StringIndexOutOfBoundsException e) {
            String command = markAsDone ? "mark" : "unmark";
            ErrorHandler.printError("Please provide a task number! Usage: " + command + " <task_number>");
        }
    }

    public static void handleTodoWithErrorCheck(String input, Task[] list, int counter) {
        try {
            String description = CommandParser.extractTodoDescription(input);
            if (description.trim().isEmpty()) {
                ErrorHandler.printError("Todo description cannot be empty! Usage: todo <description>");
                return;
            }
            CommandHandler.handleTodo(description, list, counter);
        } catch (StringIndexOutOfBoundsException e) {
            ErrorHandler.printError("Todo description is missing! Usage: todo <description>");
        }
    }

    public static void handleDeadlineWithErrorCheck(String input, Task[] list, int counter) {
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

            CommandHandler.handleDeadline(description, list, counter, deadline);
        } catch (IllegalArgumentException e) {
            ErrorHandler.printError("Invalid deadline format! Usage: deadline <description> /by <date>");
        } catch (StringIndexOutOfBoundsException e) {
            ErrorHandler.printError("Deadline command is incomplete! Usage: deadline <description> /by <date>");
        }
    }

    public static void handleEventWithErrorCheck(String input, Task[] list, int counter) {
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

            CommandHandler.handleEvent(description, list, counter, from, until);
        } catch (IllegalArgumentException e) {
            ErrorHandler.printError("Invalid event format! Usage: event <description> /from <start> /to <end>");
        } catch (StringIndexOutOfBoundsException e) {
            ErrorHandler.printError("Event command is incomplete! Usage: event <description> /from <start> /to <end>");
        }
    }

    public static void handleAdd(String input, Task[] list, int counter) {
        if (input.trim().isEmpty()) {
            ErrorHandler.printError("Task description cannot be empty!");
            return;
        }

        Task newTask = new Task(input);
        list[counter - 1] = newTask;
        System.out.println(Message.addMessage(input, newTask, counter));
    }

    public static void handleTodo(String input, Task[] list, int counter) {
        Task newTask = new Todo(input);
        list[counter - 1] = newTask;
        System.out.println(Message.addMessage(input, newTask, counter));
    }

    public static void handleDeadline(String input, Task[] list, int counter, String deadline) {
        Task newTask = new Deadline(input, deadline);
        list[counter - 1] = newTask;
        System.out.println(Message.addMessage(input, newTask, counter));
    }

    public static void handleEvent(String input, Task[] list, int counter, String from, String until) {
        Task newTask = new Event(input, from, until);
        list[counter - 1] = newTask;
        System.out.println(Message.addMessage(input, newTask, counter));
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
}
