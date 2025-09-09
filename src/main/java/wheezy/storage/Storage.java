package wheezy.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import wheezy.tasklist.TaskList;
import wheezy.task.Task;
import wheezy.task.Deadline;
import wheezy.task.Event;
import wheezy.task.Todo;

/*
 * Used to handle things related to file I/O. Used to parse string input
 * in the storage format to tasks.
 */
public class Storage {
    /**
     * Creates the necessary file and directory to save task data.
     *
     * @throws IOException If file corrupts while creating.
     */
    public static void createDirectory() throws IOException {
        new File("data").mkdirs();
        new File("data/wheezy.txt").createNewFile();
    }

    /**
     * Reads the contents of the data file and parses it via fileContentParser.
     * Adds the tasks to a TaskList.
     *
     * @param taskList Empty TaskList.
     * @return TaskList containing the tasks read from the file.
     * @throws FileNotFoundException If storage file is not found.
     */
    public static TaskList loadContent(TaskList taskList) throws FileNotFoundException {
        File f = new File("data/wheezy.txt");
        Scanner scanner = new Scanner(f);

        while (scanner.hasNext()) {
            Task newTask = fileContentParser(scanner.nextLine());
            taskList.add(newTask);
        }

        return taskList;
    }

    /**
     * Marks/unmarks the specified task in the correct file format.
     * Creates a new data file in the process.
     *
     * @param taskNumber Integer representing the task number (1 indexing).
     * @param isDone Boolean representing to mark or unmark the task.
     * @throws IOException If storage file is not found.
     */
    public static void fileMark(int taskNumber, boolean isDone) throws IOException {
        File f = new File("data/wheezy.txt");
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }

        String oldLine = lines.get(taskNumber);
        String[] parts = oldLine.split("\\|");
        if (isDone) {
            parts[1] = "1";
        } else {
            parts[1] = "0";
        }
        lines.set(taskNumber, String.join("|", parts));

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        }
    }

    /**
     * Adds a line in the storage file when a new task is added.
     *
     * @param task New task to add to the file.
     * @throws IOException If storage file is not found.
     */
    public static void fileAdd(Task task) throws IOException {
        FileWriter fw = new FileWriter("data/wheezy.txt", true);
        fw.write(task.toFileString() + "\n");
        fw.close();
    }

    /**
     * Deletes the specified task in the storage file.
     *
     * @param taskNumber Integer to represent the task number to delete (1 indexing).
     * @throws IOException If storage file is not found.
     */
    public static void fileDelete(int taskNumber) throws IOException {
        File f = new File("data/wheezy.txt");
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }

        lines.remove(taskNumber);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        }
    }

    /**
     * Parses the line from the storage format:
     * <tasktype>|<isdone>|<description>|<optional-dates>
     * The appropriate task type is then created from the parsed line.
     *
     * @param input String containing a line in the above file format.
     * @return Task of the appropriate type.
     */
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
            assert(parts.length == 4);
            String date = parts[3];
            Task deadline = new Deadline(description, date);
            if (isDone) {
                deadline.markDone();
            }
            return deadline;
        case "E":
            assert(parts.length == 5);
            String from = parts[3];
            String until = parts[4];
            Task event = new Event(description, from, until);
            if (isDone) {
                event.markDone();
            }
            return event;
        default:
            return new Todo(description);
        }
    }
}
