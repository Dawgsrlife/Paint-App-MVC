package ca.utoronto.utm.assignment2.paint.commandMenuBar;

import ca.utoronto.utm.assignment2.paint.PaintController;
import ca.utoronto.utm.assignment2.paint.PaintModel;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;

import java.util.Objects;

/**
 * The CommandLightMode class implements the Command interface to handle
 * switching the application to a light mode theme. When executed, this command
 * applies a light mode stylesheet to the JavaFX Scene, changing the visual
 * appearance of the application.
 */
public class CommandLightMode extends MenuItem implements Command {

    /**
     * Initializes a new CommandDarkMode MenuItem with the label "Dark Mode".
     */
    public CommandLightMode() {
        super("Light Mode");
    }

    /**
     * Executes the light mode command, applying a light mode stylesheet to the
     * application Scene. This method replaces the current stylesheet with
     * "paint-style.css".
     */
    @Override
    public void execute(PaintModel model, Scene scene, PaintController controller) {
        scene.getStylesheets().set(0, Objects.requireNonNull(this.getClass().getResource("paint-style.css")).toExternalForm());
    }
}
