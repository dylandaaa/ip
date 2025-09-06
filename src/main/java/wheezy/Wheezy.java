package wheezy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import wheezy.gui.CommandHandler;
import wheezy.tasklist.TaskList;
import wheezy.task.Task;
import wheezy.storage.Storage;
import wheezy.ui.Ui;

public class Wheezy {
    private TaskList taskList;
    private ArrayList<Task> arrayList = new ArrayList<>();

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
     */
    public String getResponse(String input, TaskList taskList) {
        return CommandHandler.handleResponse(input, taskList);
    }

    public static void main(String[] args) {
        new Wheezy().run();
    }
}
