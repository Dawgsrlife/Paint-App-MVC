package ca.utoronto.utm.assignment2.paint.commandMenuBar;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.Shape;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CommandSave extends MenuItem implements Command {
    public CommandSave() {
        super("Save");
    }
    @Override
    public void execute(PaintModel model) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Save file");
        fc.setInitialFileName("New-Canvas.a2s");
        File file = fc.showSaveDialog(new Stage());
        try {
            if (! file.createNewFile()) {
                Alert alert = new Alert(Alert.AlertType.WARNING,
                        "Are you sure you want to override the existing file?",
                        ButtonType.NO, ButtonType.YES);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.NO) return;
            }
            FileOutputStream fos = new FileOutputStream(file);
            for (Shape shape : model.getShapes()) {
                if (shape != null) fos.write(shape.toString().getBytes());
            }
            fos.flush();
            fos.close();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Something went wrong while saving canvas!",
                    ButtonType.OK);
            alert.showAndWait();
        }
    }
}
