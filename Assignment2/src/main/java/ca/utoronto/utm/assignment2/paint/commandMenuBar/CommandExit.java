package ca.utoronto.utm.assignment2.paint.commandMenuBar;

import ca.utoronto.utm.assignment2.paint.PaintController;
import ca.utoronto.utm.assignment2.paint.PaintModel;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;

/**
 * The CommandExit class implements the Command interface to handle
 * exiting the application. When executed, this command will close the
 * JavaFX application.
 */
public class CommandExit extends MenuItem implements Command {

    /**
     * Initializes a new CommandDarkMode MenuItem with the label "Exit".
     */
    public CommandExit() {
        super("Exit");
    }

    /**
     * This command executes when the user selects the "Exit" option from the
     * menu, allowing the application to shut down properly.
     * @param model      The PaintModel, which manages the application's state.
     * @param scene      The JavaFX Scene associated with the paint application, allowing commands to interact with the UI.
     * @param controller The PaintController, which provides access to control and manipulate the paint application
     *                   with users' mouse events.
     */
    @Override
    public void execute(PaintModel model, Scene scene, PaintController controller) {
        Platform.exit();
    }
}
