package wheezy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import wheezy.gui.CommandHandler;
import wheezy.tasklist.TaskList;
import wheezy.task.Task;
import wheezy.storage.Storage;
import wheezy.ui.Ui;

/**
 * Entry point class for the Wheezy application. Initializes state, loads storage,
 * and provides methods to run the CLI and generate responses for the GUI.
 */
public class Wheezy {
    private TaskList taskList;
    private ArrayList<Task> arrayList = new ArrayList<>();

    /**
     * Constructs a Wheezy instance and loads previously saved tasks if available.
     */
    public Wheezy() {
        this.taskList = new TaskList(arrayList);
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
    }

    /**
     * Runs the CLI version of Wheezy.
     */
    public void run() {
        Ui.printWelcome();
        Ui.startUi(this.taskList);
    }

    /**
     * Overloaded method for getResponse
     * @param input
     * @return String output from Wheezy
     */
    public String getResponse(String input) {
        return getResponse(input, taskList);
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @param input Raw user input string.
     * @param taskList TaskList that stores all the tasks.
     * @return String response to be displayed in the GUI.
     */
    public String getResponse(String input, TaskList taskList) {
        return CommandHandler.handleResponse(input, taskList);
    }

    /**
     * Program entry point to launch the CLI application.
     *
     * @param args Command-line arguments (unused).
     */
    public static void main(String[] args) {
        new Wheezy().run();
    }
}
