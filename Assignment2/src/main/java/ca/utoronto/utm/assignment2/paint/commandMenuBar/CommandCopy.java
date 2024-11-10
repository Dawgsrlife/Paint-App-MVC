package ca.utoronto.utm.assignment2.paint.commandMenuBar;

import ca.utoronto.utm.assignment2.paint.Clipboard;
import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.Shape;
import ca.utoronto.utm.assignment2.paint.controlPanels.PropertiesPanel;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;

public class CommandCopy extends MenuItem implements Command {
    PropertiesPanel propertiesPanel;

    public CommandCopy(PropertiesPanel propertiesPanel) {
        super("copy");
        this.propertiesPanel = propertiesPanel;
    }

    @Override
    public void execute(PaintModel model, Scene scene) {
        Shape selectedShape = propertiesPanel.getCurrentlyLoadedShape();
        if (selectedShape != null) {
            Clipboard.add(selectedShape);
        }
    }
}
