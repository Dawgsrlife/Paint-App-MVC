package ca.utoronto.utm.assignment2.paint.commandMenuBar;

import ca.utoronto.utm.assignment2.paint.PaintController;
import ca.utoronto.utm.assignment2.paint.PaintModel;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;

public class CommandCopyright extends MenuItem implements Command {
    public CommandCopyright() {
        super("Copyright");
    }

    @Override
    public void execute(PaintModel model, Scene scene, PaintController controller) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Haha, jokes on you, we don't respect copyrights",
                ButtonType.OK);
        alert.showAndWait();
    }
}
