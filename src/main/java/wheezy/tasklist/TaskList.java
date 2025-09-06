package wheezy.tasklist;

import java.io.IOException;
import java.util.ArrayList;

import wheezy.task.Task;
import wheezy.parser.Parser;
import wheezy.storage.Storage;
import wheezy.ui.Ui;
import wheezy.task.Event;
import wheezy.task.Todo;
import wheezy.task.Deadline;

/*
 * Represents a list of tasks as an ArrayList<Task>.
 * Contains methods that are relevant to manipulating the
 * tasks inside the task list itself.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor to construct the Task List.
     *
     * @param taskList An empty ArrayList<Task>.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Helper function that shadows the isEmpty() function
     * of regular ArrayLists.
     *
     * @return Boolean representing whether it is empty or not.
     */
    public boolean isEmpty() {
        return this.taskList.isEmpty();
    }

    /**
     * Helper function that shadows the size() function of
     * regular ArrayLists.
     *
     * @return Integer representing the size of the TaskList.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Helper function that shadows the get() function of
     * regular ArrayLists.
     *
     * @param i Integer index of the task to be retrieved.
     * @return The Task at the specified index.
     */
    public Task get(int i) {
        return this.taskList.get(i);
    }

    /**
     * Helper function that shadows the add() function of
     * regular ArrayLists.
     *
     * @param task Task to be added to the TaskList.
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Marks, saves into storage file, and prints a message when the user inputs
     * a mark/unmark command. Handles relevant exceptions.
     *
     * @param input String representing the raw user input.
     * @param markAsDone Boolean representing whether the user wants to mark/unmark.
     * @return The string output from Wheezy.
     */
    public String handleMark(String input, boolean markAsDone) {
        try {
            int taskNumber = Parser.extractTaskNumber(input);

            if (this.taskList.isEmpty()) {
                return Ui.printError("Your task list is empty! Add some tasks first.");
            }

            if (taskNumber < 0 || taskNumber >= this.taskList.size()) {
                return Ui.printError("Task number " + (taskNumber + 1) +
                        " doesn't exist! You have " + this.taskList.size() + " task(s).");
            }

            Task task = this.taskList.get(taskNumber);
            if (markAsDone) {
                task.markDone();
                try {
                    Storage.fileMark(taskNumber, true);
                } catch (IOException ioe) {
                    return "An IO Exception occurred: " + ioe.getMessage();
                }
            } else {
                task.unmarkDone();
                try {
                    Storage.fileMark(taskNumber, false);
                } catch (IOException ioe) {
                    return "An IO Exception occurred: " + ioe.getMessage();
                }
            }

            return Ui.markAsDoneMessage(markAsDone, task);

        } catch (NumberFormatException e) {
            String command = markAsDone ? "mark" : "unmark";
            return Ui.printError("Invalid task number! Usage: " + command + " <task_number>");
        }
    }

    /**
     * Deletes from the TaskList, the storage file and prints the appropriate
     * delete message to the user. Handles relevant exceptions.
     *
     * @param input String representing the raw user input.
     * @return The string output from Wheezy.
     */
    public String handleDelete(String input) {
        try {
            int taskNumber = Parser.extractTaskNumber(input);

            if (this.taskList.isEmpty()) {
                return Ui.printError("Your task list is empty! Nothing to delete.");
            }

            if (taskNumber < 0 || taskNumber >= this.taskList.size()) {
                return Ui.printError("Task number " + (taskNumber + 1) +
                        " doesn't exist! You have " + this.taskList.size() + " task(s).");
            }

            Task deletedTask = this.taskList.remove(taskNumber);
            try {
                Storage.fileDelete(taskNumber);
            } catch (IOException ioe) {
                return "An IO Exception occurred: " + ioe.getMessage();
            }

            return Ui.deleteMessage(deletedTask, this.taskList.size());

        } catch (NumberFormatException e) {
            return Ui.printError("Invalid task number! Usage: delete <task_number>");
        }
    }

    /**
     * Adds to the TaskList and to the storage file. Handles relevant exceptions.
     *
     * @param input String representing the raw user input.
     * @return The string output from Wheezy
     */
    public String handleAdd(String input) {
        if (input.trim().isEmpty()) {
            return Ui.printError("Task description cannot be empty!");
        }

        Task newTask = new Todo(input);
        this.taskList.add(newTask);
        try {
            Storage.fileAdd(newTask);
        } catch (IOException ioe) {
            return "An IO Exception occurred: " + ioe.getMessage();
        }
        return Ui.addMessage(newTask, this.taskList.size());
    }

    /**
     * Adds Todo tasks to the TaskList and to the storage file. Handles relevant exceptions.
     *
     * @param input String representing the raw user input.
     * @return The string output from Wheezy
     */
    public String handleTodoWithErrorCheck(String input) {
        try {
            String description = Parser.extractTodoDescription(input);
            if (description.trim().isEmpty()) {
                return Ui.printError("Todo description cannot be empty! Usage: todo <description>");
            }

            Task newTask = new Todo(description);
            this.taskList.add(newTask);
            try {
                Storage.fileAdd(newTask);
            } catch (IOException ioe) {
                return "An IO Exception occurred: " + ioe.getMessage();
            }
            return Ui.addMessage(newTask, this.taskList.size());
        } catch (StringIndexOutOfBoundsException e) {
            return Ui.printError("Todo description is missing! Usage: todo <description>");
        }
    }

    /**
     * Adds Deadline tasks to the TaskList and to the storage file. Handles relevant exceptions.
     *
     * @param input String representing the raw user input.
     * @return The string output from Wheezy
     */
    public String handleDeadlineWithErrorCheck(String input) {
        try {
            String description = Parser.extractDeadlineDescription(input);
            String deadline = Parser.extractDeadlineDate(input);

            if (description.trim().isEmpty()) {
                return Ui.printError("Deadline description cannot be empty! " +
                        "Usage: deadline <description> /by <date>");
            }
            if (deadline.trim().isEmpty()) {
                return Ui.printError("Deadline date cannot be empty! " +
                        "Usage: deadline <description> /by <date>");
            }

            Task newTask = new Deadline(description, deadline);
            this.taskList.add(newTask);
            try {
                Storage.fileAdd(newTask);
            } catch (IOException ioe) {
                System.out.println("An IO Exception occurred: " + ioe.getMessage());
            }

            return Ui.addMessage(newTask, this.taskList.size());

        } catch (IllegalArgumentException e) {
            return Ui.printError("Invalid deadline format! " +
                    "Usage: deadline <description> /by <date>");
        } catch (StringIndexOutOfBoundsException e) {
            return Ui.printError("Deadline command is incomplete! " +
                    "Usage: deadline <description> /by <date>");
        }
    }

    /**
     * Adds Event tasks to the TaskList and to the storage file. Handles relevant exceptions.
     *
     * @param input String representing the raw user input.
     * @return The string output from Wheezy
     */
    public String handleEventWithErrorCheck(String input) {
        try {
            String description = Parser.extractEventDescription(input);
            String from = Parser.extractEventStartTime(input);
            String until = Parser.extractEventEndTime(input);

            if (description.trim().isEmpty()) {
                return Ui.printError("Event description cannot be empty! " +
                        "Usage: event <description> /from <start> /to <end>");
            }
            if (from.trim().isEmpty()) {
                return Ui.printError("Event start time cannot be empty! " +
                        "Usage: event <description> /from <start> /to <end>");
            }
            if (until.trim().isEmpty()) {
                return Ui.printError("Event end time cannot be empty! " +
                        "Usage: event <description> /from <start> /to <end>");
            }

            Task newTask = new Event(description, from, until);
            this.taskList.add(newTask);
            try {
                Storage.fileAdd(newTask);
            } catch (IOException ioe) {
                return "An IO Exception occurred: " + ioe.getMessage();
            }
            return Ui.addMessage(newTask, this.taskList.size());
        } catch (IllegalArgumentException e) {
            return Ui.printError("Invalid event format! " +
                    "Usage: event <description> /from <start> /to <end>");
        } catch (StringIndexOutOfBoundsException e) {
            return Ui.printError("Event command is incomplete! " +
                    "Usage: event <description> /from <start> /to <end>");
        }
    }

    /**
     * Finds the tasks containing the string inputted by the user.
     *
     * @param input String representing the user input containing the keyword.
     * @return The string ouput from Wheezy
     */
    public String handleFindWithErrorCheck(String input) {
        String description = Parser.extractFindDescription(input);
        ArrayList<Task> tasks = new ArrayList<>();

        for (Task task : taskList) {
            if (task.getDescription().contains(description)) {
                tasks.add(task);
            }
        }

        return Ui.findMessage(tasks);
    }
}
