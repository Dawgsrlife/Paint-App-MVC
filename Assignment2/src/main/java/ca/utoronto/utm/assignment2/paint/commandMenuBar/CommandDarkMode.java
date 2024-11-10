package ca.utoronto.utm.assignment2.paint.commandMenuBar;

import ca.utoronto.utm.assignment2.paint.PaintController;
import ca.utoronto.utm.assignment2.paint.PaintModel;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;

import java.util.Objects;

public class CommandDarkMode extends MenuItem implements Command {
    public CommandDarkMode() {
        super("Dark Mode");
    }

    @Override
    public void execute(PaintModel model, Scene scene, PaintController controller) {
        scene.getStylesheets().set(0, Objects.requireNonNull(this.getClass().getResource("paint-style-dark.css")).toExternalForm());

    }
}
