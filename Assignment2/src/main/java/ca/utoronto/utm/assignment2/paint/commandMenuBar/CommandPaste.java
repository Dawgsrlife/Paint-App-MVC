package ca.utoronto.utm.assignment2.paint.commandMenuBar;

import ca.utoronto.utm.assignment2.paint.PaintController;
import ca.utoronto.utm.assignment2.paint.PaintModel;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;

public class CommandPaste extends MenuItem implements Command {

    public CommandPaste() {
        super("paste");
    }

    @Override
    public void execute(PaintModel model, Scene scene, PaintController controller) {
        if (controller.getShape() != null) {
            model.addShape(controller.getShape());
            controller.setShape(null);
        }
    }
}