public class ExitCommand extends Command{

    public ExitCommand(String[] command, String description, boolean isExit) {
        super(command, description, isExit);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws YukiException{

    }

    @Override
    public boolean isExit() {
        return true;
    }
}
