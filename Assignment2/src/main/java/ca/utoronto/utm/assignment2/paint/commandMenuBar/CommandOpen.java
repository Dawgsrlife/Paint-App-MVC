package ca.utoronto.utm.assignment2.paint.commandMenuBar;

import ca.utoronto.utm.assignment2.paint.*;
import ca.utoronto.utm.assignment2.paint.shapes.Point;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * The CommandOpen class implements the Command interface to handle opening
 * a previously saved canvas file. When executed, this command allows the user
 * to select a file and loads the contents into the PaintModel.
 * This command is typically triggered when the user selects "Open" from the menu.
 */
public class CommandOpen extends MenuItem implements Command {

    /**
     * Initializes a new CommandOpen MenuItem with the label "Open".
     */
    public CommandOpen() {
        super("Open");
    }

    /**
     * Executes the open command to allow the user to load a previously saved
     * canvas.
     * Steps: Firstly opening a file dialog to the selected file to read the selected
     * file. And then restore the canvas to the saved state by adding shapes to the model.
     * The method prompts the user with a confirmation before proceeding, warning
     * that any unsaved changes will be lost.
     * If confirmed, the canvas is cleared, and the shapes are reconstructed from the
     * file data. Otherwise, nothing changes to canvas.
     * Precondition: the file has to be "*.a2s" format.
     *
     * @param model      The PaintModel that manages the application's state, including shapes.
     * @param scene      The JavaFX Scene (used for file dialog interaction).
     * @param controller The PaintController (not used in this command).
     */
    @Override
    public void execute(PaintModel model, Scene scene, PaintController controller) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Open canvas");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("A2S", "*.a2s"));
        File file = fc.showOpenDialog(new Stage());
        if (file == null) {
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Are you sure you want to open this canvas? \n" +
                            "Any unsaved changes will be permanently lost.",
                    ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.NO) {
                return;
            }
            model.clear();
            while (br.ready()) {
                String[] details = br.readLine().split(",");
                Point start = new Point(Double.parseDouble(details[0]), Double.parseDouble(details[1]));
                Point end = new Point(Double.parseDouble(details[2]), Double.parseDouble(details[3]));
                String type = details[4];
                PaintProperties pp = new PaintProperties(
                        Boolean.parseBoolean(details[5]), Color.web(details[6]),
                        Color.web(details[7]), Double.parseDouble(details[8]), Integer.parseInt(details[9]));
                ArrayList<Point> path = new ArrayList<>();
                if (details.length >= 11) {
                    for (int i = 10; i < details.length - 1; i += 2) {
                        path.add(new Point(Double.parseDouble(details[i]), Double.parseDouble(details[i + 1])));
                    }
                }
                model.addShape(PaintStrategy.getPaintStrategy(type, start, end, pp, path));
            }
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Something went wrong while opening canvas!",
                    ButtonType.OK);
            alert.showAndWait();
        }
    }
}
