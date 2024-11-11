package ca.utoronto.utm.assignment2.paint.commandMenuBar;

import ca.utoronto.utm.assignment2.paint.*;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;

/**
 * The CommandCut class implements the Command interface to handle cutting the
 * currently selected shape in the PaintModel. When executed, this command removes
 * the selected shape from the model, essentially "cutting" it from the canvas.
 */
public class CommandCut extends MenuItem implements Command {

    /**
     * Initializes a new CommandCut MenuItem with the label "cut".
     */
    public CommandCut() {
        super("cut");
    }

    /**
     * If a shape is currently selected in the model and then users click cut on the
     * Edit menubar, the selected shaped will be cut from the canvas, that is, remove
     * the selected shape from model.
     *
     * @param model      The PaintModel, which manages the application's state.
     * @param scene      The JavaFX Scene associated with the paint application, allowing commands to interact with the UI.
     * @param controller The PaintController, which provides access to control and manipulate the paint application
     *                   with users' mouse events.
     */
    @Override
    public void execute(PaintModel model, Scene scene, PaintController controller) {
        if (model.getCurrentShape() != null) {
            model.setClipBoard(model.getCurrentShape());
            model.removeShape(model.getCurrentShape());
        }
    }
}
