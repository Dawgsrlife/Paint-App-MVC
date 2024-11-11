package ca.utoronto.utm.assignment2.paint.commandMenuBar;

import ca.utoronto.utm.assignment2.paint.PaintController;
import ca.utoronto.utm.assignment2.paint.PaintModel;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;

/**
 * The CommandUndo class implements the Command interface to handle undoing
 * the last action in the PaintModel. When executed, this command reverts the
 * most recent change made to the canvas, allowing users to step back through
 * their changes.
 * This command is typically triggered when the user selects "Undo" from the menu,
 * providing them with the ability to undo previous actions.
 */
public class CommandUndo extends MenuItem implements Command {

    /**
     * Initializes a new CommandUndo MenuItem with the label "Undo".
     */
    public CommandUndo() {
        super("Undo");
    }

    /**
     * Executes the undo command, invoking the undo method on the PaintModel.
     * This reverts the most recent action, allowing the user to undo the last
     * shape added or change made to the canvas.
     *
     * @param model      The PaintModel that manages the application's state, including undo and redo actions.
     * @param scene      The JavaFX Scene (not used in this command).
     * @param controller The PaintController (not used in this command).
     */
    @Override
    public void execute(PaintModel model, Scene scene, PaintController controller) {
        model.undo();
    }
}
