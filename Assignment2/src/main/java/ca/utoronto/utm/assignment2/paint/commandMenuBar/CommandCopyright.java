package ca.utoronto.utm.assignment2.paint.commandMenuBar;

import ca.utoronto.utm.assignment2.paint.PaintController;
import ca.utoronto.utm.assignment2.paint.PaintModel;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;

/**
 * The CommandCopyright class implements the Command interface to handle
 * displaying a humorous copyright message.
 * This command is intended for user engagement.
 *
 */
public class CommandCopyright extends MenuItem implements Command {

    /**
     * Initializes a new CommandCopyright MenuItem with the label "Copyright".
     */
    public CommandCopyright() {
        super("Copyright");
    }

    /**
     * Executes the command to display an alert dialog with a humorous copyright
     * message.
     * @param model      The PaintModel, which manages the application's state.
     * @param scene      The JavaFX Scene associated with the paint application, allowing commands to interact with the UI.
     * @param controller The PaintController, which provides access to control and manipulate the paint application
     *                   with users' mouse events.
     */
    @Override
    public void execute(PaintModel model, Scene scene, PaintController controller) {
        Alert alert = new Alert(Alert.AlertType.NONE,
                "Haha, jokes on you, we don't respect copyrights",
                ButtonType.OK);
        alert.setTitle("User Guide");
        alert.showAndWait();
    }
}
