package ca.utoronto.utm.assignment2.paint.commandMenuBar;

import ca.utoronto.utm.assignment2.paint.Clipboard;
import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.Shape;
import ca.utoronto.utm.assignment2.paint.controlPanels.PropertiesPanel;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import static ca.utoronto.utm.assignment2.paint.PaintStrategy.getPaintStrategy;

public class CommandPaste extends MenuItem implements Command {
    PropertiesPanel propertiesPanel;

    public CommandPaste(PropertiesPanel propertiesPanel) {
        super("paste");
        this.propertiesPanel = propertiesPanel;
    }

    @Override
    public void execute(PaintModel model, Scene scene) {
        Shape copiedShape = Clipboard.get();
        if (copiedShape != null) {
            Shape newS = getPaintStrategy(
                    copiedShape.getType(),
                    copiedShape.getStart(),
                    copiedShape.getEnd(),
                    copiedShape.getProperties(),
                    null
            );
            model.addShape(newS);
        }else{System.out.println("Clipboard is empty, nothing to paste.");}
    }
}