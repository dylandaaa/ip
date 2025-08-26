import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

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

    public static void main(String[] args) {
        new Wheezy().run();
    }
}
