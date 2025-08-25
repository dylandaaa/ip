import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class FileHandler {
    public static void createDirectory() throws IOException {
        new File("data").mkdirs();
        new File("data/wheezy.txt").createNewFile();
    }

    public static ArrayList<Task> loadContent(ArrayList<Task> taskList) throws FileNotFoundException {
        // reads and returns the taskList from the ./data/wheezy.txt file
            File f = new File("data/wheezy.txt");
            Scanner scanner = new Scanner(f);

            while (scanner.hasNext()) {
                Task newTask = fileContentParser(scanner.nextLine());
                taskList.add(newTask);
            }

            return taskList;
    }

    public static Task fileContentParser(String input) {
        String[] parts = input.split("\\|");
        String description = parts[2];
        String type = parts[0];
        boolean isDone = Objects.equals(parts[1], "1");

        switch (type) {
        case "T":
            Task todo = new Todo(description);
            if (isDone) {
                todo.markDone();
            }
            return todo;
        case "D":
            String date = parts[3];
            Task deadline = new Deadline(description, date);
            if (isDone) {
                deadline.markDone();
            }
            return deadline;
        case "E":
            String from = parts[3];
            String until = parts[4];
            Task event = new Event(description, from, until);
            if (isDone) {
                event.markDone();
            }
            return event;
        default:
            return new Task(description);
        }
    }
}
