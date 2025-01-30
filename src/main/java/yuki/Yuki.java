package yuki;

import yuki.command.Command;
import yuki.task.Task;
import yuki.TaskList;
import yuki.YukiException;

public class Yuki {

    private final Storage storage;
    private TaskList<Task> tasks;
    private final Ui ui;

    public Yuki(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList<>(storage.load());
        } catch (YukiException e) {
            ui.showLoadingError();
            tasks = new TaskList<>();
        }

    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (YukiException e) {
                ui.showError(e.getMessage());
            }
        }
        storage.save(tasks);
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new Yuki("src/main/java/data/Yuki.txt").run();
    }
}
