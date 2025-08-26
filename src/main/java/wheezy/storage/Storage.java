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
import wheezy.task.*;

public class Storage {
    public static void createDirectory() throws IOException {
        new File("data").mkdirs();
        new File("data/wheezy.txt").createNewFile();
    }

    public static TaskList loadContent(TaskList taskList) throws FileNotFoundException {
        File f = new File("data/wheezy.txt");
        Scanner scanner = new Scanner(f);

        while (scanner.hasNext()) {
            Task newTask = fileContentParser(scanner.nextLine());
            taskList.add(newTask);
        }

        return taskList;
    }

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

    public static void fileAdd(Task task) throws IOException {
        FileWriter fw = new FileWriter("data/wheezy.txt", true);
        fw.write(task.toFileString() + "\n");
        fw.close();
    }

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
            return new Todo(description);
        }
    }
}
