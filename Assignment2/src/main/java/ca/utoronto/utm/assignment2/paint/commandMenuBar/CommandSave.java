package ca.utoronto.utm.assignment2.paint.commandMenuBar;

import ca.utoronto.utm.assignment2.paint.PaintController;
import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.Shape;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class CommandSave extends MenuItem implements Command {
    public CommandSave() {
        super("Save");
    }
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
