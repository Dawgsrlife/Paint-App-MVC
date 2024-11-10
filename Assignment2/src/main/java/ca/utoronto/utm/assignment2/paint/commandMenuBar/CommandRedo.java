package ca.utoronto.utm.assignment2.paint.commandMenuBar;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;

public class CommandRedo extends MenuItem implements Command {
    public CommandRedo() {
        super("Redo");
    }
    @Override
    public void execute(PaintModel model, Scene scene) {
        model.redo();
    }
}
