package ca.utoronto.utm.assignment2.paint.commandMenuBar;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;

public class CommandNew extends MenuItem implements Command {
    public CommandNew() {
        super("New");
    }
    @Override
    public void execute(PaintModel model, Scene scene) {
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
