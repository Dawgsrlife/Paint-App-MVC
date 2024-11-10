package ca.utoronto.utm.assignment2.paint.commandMenuBar;

import ca.utoronto.utm.assignment2.paint.PaintController;
import ca.utoronto.utm.assignment2.paint.PaintModel;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;

public class CommandUndo extends MenuItem implements Command {
    public CommandUndo() {
        super("Undo");
    }
    @Override
    public void execute(PaintModel model, Scene scene, PaintController controller) {
        model.undo();
    }
}
