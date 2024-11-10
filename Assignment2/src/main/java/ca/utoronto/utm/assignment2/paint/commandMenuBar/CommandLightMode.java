package ca.utoronto.utm.assignment2.paint.commandMenuBar;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;

import java.util.Objects;

public class CommandLightMode extends MenuItem implements Command {
    public CommandLightMode() {
        super("Light Mode");
    }

    @Override
    public void execute(PaintModel model, Scene scene) {
        System.out.println("in");
        scene.getStylesheets().set(0, Objects.requireNonNull(this.getClass().getResource("paint-style.css")).toExternalForm());
    }
}
