package ca.utoronto.utm.assignment2.paint.commandMenuBar;

import ca.utoronto.utm.assignment2.paint.*;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;


public class CommandCut extends MenuItem implements Command {
    public CommandCut() {
        super("Cut");
    }

    @Override
    public void execute(PaintModel model, Scene scene, PaintController controller) {
        if (controller.getShape() != null) {
            model.removeShape(controller.getShape());
        }
    }
}
