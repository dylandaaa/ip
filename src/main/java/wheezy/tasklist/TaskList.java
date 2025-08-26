package wheezy.tasklist;

import java.io.IOException;
import java.util.ArrayList;
import wheezy.task.Task;
import wheezy.parser.Parser;
import wheezy.ui.ErrorHandler;
import wheezy.storage.Storage;
import wheezy.ui.Ui;
import wheezy.task.*;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public boolean isEmpty() {
        return this.taskList.isEmpty();
    }

    public int size() {
        return this.taskList.size();
    }

    public Task get(int i) {
        return this.taskList.get(i);
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public void handleMark(String input, boolean markAsDone) {
        try {
            int taskNumber = Parser.extractTaskNumber(input);

            if (this.taskList.isEmpty()) {
                ErrorHandler.printError("Your task list is empty! Add some tasks first.");
                return;
            }

            if (taskNumber < 0 || taskNumber >= this.taskList.size()) {
                ErrorHandler.printError("Task number " + (taskNumber + 1) + " doesn't exist! You have " + this.taskList.size() + " task(s).");
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
            ErrorHandler.printError("Invalid task number! Usage: " + command + " <task_number>");
        }
    }

    public void handleDelete(String input) {
        try {
            int taskNumber = Parser.extractTaskNumber(input);

            if (this.taskList.isEmpty()) {
                ErrorHandler.printError("Your task list is empty! Nothing to delete.");
                return;
            }

            if (taskNumber < 0 || taskNumber >= this.taskList.size()) {
                ErrorHandler.printError("Task number " + (taskNumber + 1) + " doesn't exist! You have " + this.taskList.size() + " task(s).");
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
            ErrorHandler.printError("Invalid task number! Usage: delete <task_number>");
        }
    }

    public void handleAdd(String input) {
        if (input.trim().isEmpty()) {
            ErrorHandler.printError("Task description cannot be empty!");
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

    public void handleTodoWithErrorCheck(String input) {
        try {
            String description = Parser.extractTodoDescription(input);
            if (description.trim().isEmpty()) {
                ErrorHandler.printError("Todo description cannot be empty! Usage: todo <description>");
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
            ErrorHandler.printError("Todo description is missing! Usage: todo <description>");
        }
    }

    public void handleDeadlineWithErrorCheck(String input) {
        try {
            String description = Parser.extractDeadlineDescription(input);
            String deadline = Parser.extractDeadlineDate(input);

            if (description.trim().isEmpty()) {
                ErrorHandler.printError("Deadline description cannot be empty! Usage: deadline <description> /by <date>");
                return;
            }
            if (deadline.trim().isEmpty()) {
                ErrorHandler.printError("Deadline date cannot be empty! Usage: deadline <description> /by <date>");
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
            ErrorHandler.printError("Invalid deadline format! Usage: deadline <description> /by <date>");
        } catch (StringIndexOutOfBoundsException e) {
            ErrorHandler.printError("Deadline command is incomplete! Usage: deadline <description> /by <date>");
        }
    }

    public void handleEventWithErrorCheck(String input) {
        try {
            String description = Parser.extractEventDescription(input);
            String from = Parser.extractEventStartTime(input);
            String until = Parser.extractEventEndTime(input);

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
            this.taskList.add(newTask);
            try {
                Storage.fileAdd(newTask);
            } catch (IOException ioe) {
                System.out.println("An IO Exception occurred: " + ioe.getMessage());
            }
            System.out.println(Ui.addMessage(newTask, this.taskList.size()));
        } catch (IllegalArgumentException e) {
            ErrorHandler.printError("Invalid event format! Usage: event <description> /from <start> /to <end>");
        } catch (StringIndexOutOfBoundsException e) {
            ErrorHandler.printError("Event command is incomplete! Usage: event <description> /from <start> /to <end>");
        }
    }
}
