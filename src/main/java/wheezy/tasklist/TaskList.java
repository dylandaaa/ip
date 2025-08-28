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
     */
    public void handleMark(String input, boolean markAsDone) {
        try {
            int taskNumber = Parser.extractTaskNumber(input);

            if (this.taskList.isEmpty()) {
                Ui.printError("Your task list is empty! Add some tasks first.");
                return;
            }

            if (taskNumber < 0 || taskNumber >= this.taskList.size()) {
                Ui.printError("Task number " + (taskNumber + 1) + " doesn't exist! You have " + this.taskList.size() + " task(s).");
                return;

            }

            Task task = this.taskList.get(taskNumber);
            if (markAsDone) {
                task.markDone();
                try {
                    Storage.fileMark(taskNumber, true);
                } catch (IOException ioe) {
                    System.out.println("An IO Exception occurred: " + ioe.getMessage());
                }
            } else {
                task.unmarkDone();
                try {
                    Storage.fileMark(taskNumber, false);
                } catch (IOException ioe) {
                    System.out.println("An IO Exception occurred: " + ioe.getMessage());
                }
            }

            System.out.println(Ui.markAsDoneMessage(markAsDone, task));

        } catch (NumberFormatException e) {
            String command = markAsDone ? "mark" : "unmark";
            Ui.printError("Invalid task number! Usage: " + command + " <task_number>");
        }
    }

    /**
     * Deletes from the TaskList, the storage file and prints the appropriate
     * delete message to the user. Handles relevant exceptions.
     *
     * @param input String representing the raw user input.
     */
    public void handleDelete(String input) {
        try {
            int taskNumber = Parser.extractTaskNumber(input);

            if (this.taskList.isEmpty()) {
                Ui.printError("Your task list is empty! Nothing to delete.");
                return;
            }

            if (taskNumber < 0 || taskNumber >= this.taskList.size()) {
                Ui.printError("Task number " + (taskNumber + 1) + " doesn't exist! You have " + this.taskList.size() + " task(s).");
                return;
            }

            Task deletedTask = this.taskList.remove(taskNumber);
            try {
                Storage.fileDelete(taskNumber);
            } catch (IOException ioe) {
                System.out.println("An IO Exception occurred: " + ioe.getMessage());
            }
            System.out.println(Ui.deleteMessage(deletedTask, this.taskList.size()));

        } catch (NumberFormatException e) {
            Ui.printError("Invalid task number! Usage: delete <task_number>");
        }
    }

    /**
     * Adds to the TaskList and to the storage file. Handles relevant exceptions.
     *
     * @param input String representing the raw user input.
     */
    public void handleAdd(String input) {
        if (input.trim().isEmpty()) {
            Ui.printError("Task description cannot be empty!");
            return;
        }

        Task newTask = new Todo(input);
        this.taskList.add(newTask);
        try {
            Storage.fileAdd(newTask);
        } catch (IOException ioe) {
            System.out.println("An IO Exception occurred: " + ioe.getMessage());
        }
        System.out.println(Ui.addMessage(newTask, this.taskList.size()));
    }

    /**
     * Adds Todo tasks to the TaskList and to the storage file. Handles relevant exceptions.
     *
     * @param input String representing the raw user input.
     */
    public void handleTodoWithErrorCheck(String input) {
        try {
            String description = Parser.extractTodoDescription(input);
            if (description.trim().isEmpty()) {
                Ui.printError("Todo description cannot be empty! Usage: todo <description>");
                return;
            }

            Task newTask = new Todo(description);
            this.taskList.add(newTask);
            try {
                Storage.fileAdd(newTask);
            } catch (IOException ioe) {
                System.out.println("An IO Exception occurred: " + ioe.getMessage());
            }
            System.out.println(Ui.addMessage(newTask, this.taskList.size()));
        } catch (StringIndexOutOfBoundsException e) {
            Ui.printError("Todo description is missing! Usage: todo <description>");
        }
    }

    /**
     * Adds Deadline tasks to the TaskList and to the storage file. Handles relevant exceptions.
     *
     * @param input String representing the raw user input.
     */
    public void handleDeadlineWithErrorCheck(String input) {
        try {
            String description = Parser.extractDeadlineDescription(input);
            String deadline = Parser.extractDeadlineDate(input);

            if (description.trim().isEmpty()) {
                Ui.printError("Deadline description cannot be empty! Usage: deadline <description> /by <date>");
                return;
            }
            if (deadline.trim().isEmpty()) {
                Ui.printError("Deadline date cannot be empty! Usage: deadline <description> /by <date>");
                return;
            }

            Task newTask = new Deadline(description, deadline);
            this.taskList.add(newTask);
            try {
                Storage.fileAdd(newTask);
            } catch (IOException ioe) {
                System.out.println("An IO Exception occurred: " + ioe.getMessage());
            }
            System.out.println(Ui.addMessage(newTask, this.taskList.size()));
        } catch (IllegalArgumentException e) {
            Ui.printError("Invalid deadline format! Usage: deadline <description> /by <date>");
        } catch (StringIndexOutOfBoundsException e) {
            Ui.printError("Deadline command is incomplete! Usage: deadline <description> /by <date>");
        }
    }

    /**
     * Adds Event tasks to the TaskList and to the storage file. Handles relevant exceptions.
     *
     * @param input String representing the raw user input.
     */
    public void handleEventWithErrorCheck(String input) {
        try {
            String description = Parser.extractEventDescription(input);
            String from = Parser.extractEventStartTime(input);
            String until = Parser.extractEventEndTime(input);

            if (description.trim().isEmpty()) {
                Ui.printError("Event description cannot be empty! Usage: event <description> /from <start> /to <end>");
                return;
            }
            if (from.trim().isEmpty()) {
                Ui.printError("Event start time cannot be empty! Usage: event <description> /from <start> /to <end>");
                return;
            }
            if (until.trim().isEmpty()) {
                Ui.printError("Event end time cannot be empty! Usage: event <description> /from <start> /to <end>");
                return;
            }

            Task newTask = new Event(description, from, until);
            this.taskList.add(newTask);
            try {
                Storage.fileAdd(newTask);
            } catch (IOException ioe) {
                System.out.println("An IO Exception occurred: " + ioe.getMessage());
            }
            System.out.println(Ui.addMessage(newTask, this.taskList.size()));
        } catch (IllegalArgumentException e) {
            Ui.printError("Invalid event format! Usage: event <description> /from <start> /to <end>");
        } catch (StringIndexOutOfBoundsException e) {
            Ui.printError("Event command is incomplete! Usage: event <description> /from <start> /to <end>");
        }
    }

    /**
     * Finds the tasks containing the string inputted by the user.
     *
     * @param input String representing the user input containing the keyword.
     */
    public void handleFindWithErrorCheck(String input) {
        String description = Parser.extractFindDescription(input);
        ArrayList<Task> tasks = new ArrayList<>();

        for (Task task : taskList) {
            if (task.getDescription().contains(description)) {
                tasks.add(task);
            }
        }

        System.out.println(Ui.findMessage(tasks));
    }
}
