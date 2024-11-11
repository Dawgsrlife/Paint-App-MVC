package ca.utoronto.utm.assignment2.paint.commandMenuBar;

import ca.utoronto.utm.assignment2.paint.PaintController;
import ca.utoronto.utm.assignment2.paint.PaintModel;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;

/**
 * The CommandNew class implements the Command interface to handle creating a new canvas.
 * When executed, this command clears the current canvas in the PaintModel so that
 * create a new blank canvas.
 * This command is typically triggered when the user selects the "New" option from
 * the menu, allowing them to start a new project.
 */
public class CommandNew extends MenuItem implements Command {

    /**
     * Initializes a new CommandNew MenuItem with the label "New".
     */
    public CommandNew() {
        super("New");
    }

    /**
     * Executes the new canvas command. This method displays a confirmation dialog
     * to the user to verify if they want to create a new canvas.
     * If the user confirms, the current canvas is cleared, which removes all shapes
     * and starts fresh. Otherwise, the program exits the dialog and canvas remains the same.
     *
     * @param model      The PaintModel that manages the application's state, including shapes.
     * @param scene      The JavaFX Scene.
     * @param controller The PaintController.
     */
    @Override
    public void execute(PaintModel model, Scene scene, PaintController controller) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to create a new canvas? \n" +
                        "Unsaved changes cannot be restored.",
                ButtonType.NO, ButtonType.YES);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            model.clear();
        }
    }
}
