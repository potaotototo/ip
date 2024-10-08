package noosy.commands;

import noosy.storage.Storage;
import noosy.task.TaskList;
import noosy.ui.Ui;

/**
 * Represents the exit command in the Noosy task management chatbot.
 * This command is responsible for terminating the program execution.
 */
public class ExitCommand extends Command {

    /**
     * Checks if this command is an exit command.
     * Always returns true for ExitCommand.
     *
     * @return true, indicating that this is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the exit command.
     * Displays a goodbye message to the user.
     *
     * @param tasks   The current list of tasks (not used in this command).
     * @param ui      The user interface for displaying output.
     * @param storage The storage for persisting tasks (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        assert tasks != null : "Tasklist cannot be null.";
        assert ui != null : "Ui cannot be left as null.";
        assert storage != null : "Storage cannot be null.";

        ui.showGoodbye();
        System.exit(0);
    }

    /**
     * Represents a method that gives the Noosy reponse after something has been executed by Noosy.
     *
     * @return the String of a completed execution by Noosy
     */
    @Override
    public String getString() {
        return "Exited!";
    }
}
