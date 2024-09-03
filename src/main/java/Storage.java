import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String path;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public Storage(String path) {
        this.path = path;
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File stored = new File(this.path);

        if (!stored.exists()) {
            stored.getParentFile().mkdirs();
            stored.createNewFile();
        } else {
            Scanner scanner = new Scanner(stored);
            while (scanner.hasNextLine()) {
                String taskInfo = scanner.nextLine();
                String[] separated = taskInfo.split(" \\| ");
                String taskType = separated[0];
                boolean status = separated[1].equals("1");
                String name = separated[2];

                switch (taskType) {
                    case "T":
                        tasks.add(new Todo(name, status));
                        break;

                    case "D":
                        LocalDate due = LocalDate.parse(separated[3]);
                        tasks.add(new Deadline(name, status, due));
                        break;

                    case "E":
                        String[] startAndEnd = separated[3].split("-");
                        LocalDateTime start = LocalDateTime.parse(startAndEnd[0].trim(), FORMATTER);
                        LocalDateTime end = LocalDateTime.parse(startAndEnd[1].trim(), FORMATTER);
                        tasks.add(new Event(name, status, start, end));
                        break;

                    default:
                        throw new IOException("Invalid task type in file: " + taskType);
                }
            }
            scanner.close();
        }

        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(path);
        for (Task task : tasks) {
            writer.write(task.storeInFile() + "\n");
        }
        writer.close();
    }
}