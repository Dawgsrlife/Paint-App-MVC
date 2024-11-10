package ca.utoronto.utm.assignment2.paint.commandMenuBar;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;

public class CommandExit extends MenuItem implements Command {

    public CommandExit() {
        super("Exit");
    }

    @Override
    public void execute(PaintModel model, Scene scene) {
        Platform.exit();
    }
}
