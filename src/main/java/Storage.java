import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    private final TaskList<Task> storageTasks = new TaskList<>();
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(TaskList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) {
                writer.write(tasks.get(i).toFileString() + "\n");
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("An error occurred while saving tasks to file.");
        }
    }

    public TaskList<Task> load() throws YukiException {
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].trim();
                }
                switch (parts[0]) {
                    case "T":
                        storageTasks.add(new Todo(parts[1], parts[2]));
                        break;
                    case "D":
                        storageTasks.add(new Deadline(parts[1], parts[2], parts[3]));
                        break;
                    case "E":
                        storageTasks.add(new Event(parts[1], parts[2], parts[3], parts[4]));
                        break;
                    default:
                        throw new YukiException("Invalid task type");
                }
            }
        } catch (FileNotFoundException e) {
            throw new YukiException("File not found");
        }
        return storageTasks;
    }
}
