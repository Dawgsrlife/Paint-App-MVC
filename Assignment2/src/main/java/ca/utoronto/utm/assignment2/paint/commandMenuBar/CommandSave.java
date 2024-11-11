package ca.utoronto.utm.assignment2.paint.commandMenuBar;

import ca.utoronto.utm.assignment2.paint.PaintController;
import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.shapes.Shape;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

/**
 * The CommandSave class implements the Command interface to handle saving the
 * current canvas to a file. When executed, this command allows the user to
 * specify a file location and then saves all shapes in the current PaintModel
 * to the specified file.
 * This command is typically triggered when the user selects "Save" from the menu,
 * allowing them to save their work for later use.
 */
public class CommandSave extends MenuItem implements Command {

    /**
     * Initializes a new CommandSave MenuItem with the label "Save".
     */
    public CommandSave() {
        super("Save");
    }

    /**
     * Executes the save command, prompting the user to select a location to save
     * the current canvas. This method opens a file dialog, allows the user to choose
     * a file, and saves the state of the PaintModel to that file.
     * If an error occurs during the saving process, an alert is shown to the user.
     *
     * @param model      The PaintModel that manages the application's state, including all shapes.
     * @param scene      The JavaFX Scene (used for file dialog interaction).
     * @param controller The PaintController (not used in this command).
     */
    @Override
    public void execute(PaintModel model, Scene scene, PaintController controller) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Save canvas");
        fc.setInitialFileName("New-Canvas.a2s");
        File file = fc.showSaveDialog(new Stage());
        if (file == null) return;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
            for (Shape shape : model.getShapes()) {
                if (shape != null) bw.write(shape + "\n");
            }
            bw.flush();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Something went wrong while saving canvas!",
                    ButtonType.OK);
            alert.showAndWait();
        }
    }
}
