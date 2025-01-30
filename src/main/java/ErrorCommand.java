public class ErrorCommand extends Command{
    public ErrorCommand(String[] command, String description, boolean isExit) {
        super(command, description, isExit);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws YukiException{
        ui.showError(this.getDescription());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
