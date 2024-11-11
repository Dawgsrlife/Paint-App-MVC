package ca.utoronto.utm.assignment2.paint.commandMenuBar;

import ca.utoronto.utm.assignment2.paint.PaintController;
import ca.utoronto.utm.assignment2.paint.PaintModel;
import javafx.scene.Scene;

/**
 *  The Command interface defines a contract for executable actions within the paint application.
 *  This interface follows the Command design pattern, allowing actions to be defined as separate
 *  command objects.
*/
public interface Command {
    /**
     * Executes a command action.
     *
     * @param model      The PaintModel, which manages the application's state.
     * @param scene      The JavaFX Scene associated with the paint application, allowing commands to interact with the UI.
     * @param controller The PaintController, which provides access to control and manipulate the paint application
     *                   with users' mouse events.
     */
    void execute(PaintModel model, Scene scene, PaintController controller);
}
