package ca.utoronto.utm.assignment2.paint.commandMenuBar;

import ca.utoronto.utm.assignment2.paint.PaintController;
import ca.utoronto.utm.assignment2.paint.PaintModel;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;

/**
 * The CommandRedo class implements the Command interface to handle redoing
 * the last undone action in the PaintModel. When executed, this command
 * attempts to reapply an action that was previously undone using the undo functionality.
 * This command is typically triggered when the user selects "Redo" from the menu,
 * allowing them to reapply the last action that was undone.
 */
public class CommandRedo extends MenuItem implements Command {

    /**
     * Initializes a new CommandDarkMode MenuItem with the label "Dark Mode".
     */
    public CommandRedo() {
        super("Redo");
    }

    /**
     * Executes the redo command, invoking the redo method on the PaintModel.
     * If there are actions in the undo stack that can be redone, they are reapplied
     * to restore the state before the undo action.
     *
     * @param model      The PaintModel that manages the application's state, including undo and redo actions.
     * @param scene      The JavaFX Scene (not used in this command).
     * @param controller The PaintController (not used in this command).
     */
    @Override
    public void execute(PaintModel model, Scene scene, PaintController controller) {
        model.redo();
    }
}
