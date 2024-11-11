package ca.utoronto.utm.assignment2.paint.commandMenuBar;

import ca.utoronto.utm.assignment2.paint.PaintController;
import ca.utoronto.utm.assignment2.paint.PaintModel;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;

/**
 * The CommandPaste class implements the Command interface to handle pasting
 * a copied or cut shape from the clipboard back into the canvas. When executed,
 * this command retrieves the shape from the model's clipboard and adds it to
 * the PaintModel.
 * This command is typically triggered when the user selects "Paste" from the menu.
 * It allows users to paste a previously copied or cut shape back into the canvas.
 */
public class CommandPaste extends MenuItem implements Command {

    /**
     * Initializes a new CommandPaste MenuItem with the label "Paste".
     */
    public CommandPaste() {
        super("paste");
    }

    /**
     * Executes the paste command, adding the shape currently stored in the model's
     * clipboard to the canvas. If a shape is available in the clipboard, it is added
     * to the PaintModel which help draws the copied shape.
     * Finally, the clipboard is cleared for the next time copy and paste implementation.
     *
     * @param model      The PaintModel that manages the application's state, including the clipboard.
     * @param scene      The JavaFX Scene (not used in this command).
     * @param controller The PaintController (not used in this command).
     */
    @Override
    public void execute(PaintModel model, Scene scene, PaintController controller) {
        if (model.getClipBoard() != null) {
            model.addShape(model.getClipBoard());
            model.setClipBoard(null);
        }
    }
}