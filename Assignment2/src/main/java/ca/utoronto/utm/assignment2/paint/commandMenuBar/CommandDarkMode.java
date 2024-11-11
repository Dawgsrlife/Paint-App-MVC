package ca.utoronto.utm.assignment2.paint.commandMenuBar;

import ca.utoronto.utm.assignment2.paint.PaintController;
import ca.utoronto.utm.assignment2.paint.PaintModel;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;

import java.util.Objects;

/**
 * The CommandDarkMode class implements the Command interface to handle
 * switching the application to a dark mode theme. When executed, this command
 * applies a dark mode stylesheet to the JavaFX Scene, changing the visual
 * appearance of the application.
 * This command allows them to toggle between light and dark themes.
 */
public class CommandDarkMode extends MenuItem implements Command {

    /**
     * Initializes a new CommandDarkMode MenuItem with the label "Dark Mode".
     */
    public CommandDarkMode() {
        super("Dark Mode");
    }

    /**
     * Executes the dark mode command, applying a dark mode stylesheet to the
     * application Scene. This method replaces the current stylesheet with
     * "paint-style-dark.css".
     * @param model      The PaintModel, which manages the application's state.
     * @param scene      The JavaFX Scene associated with the paint application, allowing commands to interact with the UI.
     * @param controller The PaintController, which provides access to control and manipulate the paint application
     *                   with users' mouse events.
     */
    @Override
    public void execute(PaintModel model, Scene scene, PaintController controller) {
        scene.getStylesheets().set(0, Objects.requireNonNull(this.getClass().getResource("paint-style-dark.css")).toExternalForm());

    }
}
