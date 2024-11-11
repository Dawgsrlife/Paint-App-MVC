package ca.utoronto.utm.assignment2.paint;

import ca.utoronto.utm.assignment2.paint.controlPanels.PropertiesPanel;
import ca.utoronto.utm.assignment2.paint.shapes.Point;
import ca.utoronto.utm.assignment2.paint.shapes.Text;
import javafx.scene.layout.Pane;

public class TextModeStrategy implements ModeStrategy {
    private PaintModel model;
    private PropertiesPanel propertiesPanel;
    private Text currentText;
    private Pane canvasPane;
    private PaintController controller;

    public TextModeStrategy(PaintModel model, Pane canvasPane, PropertiesPanel pp, PaintController controller) {
        this.model = model;
        this.canvasPane = canvasPane;
        this.propertiesPanel = pp;
        this.controller = controller;
    }

    @Override
    public void onMouseMoved(Point point) {
    }

    @Override
    public void onMousePressed(Point point, boolean isPrimaryButton, boolean isSecondaryButton) {
        if (!isPrimaryButton) return;

        // Create a new text object
        currentText = new Text(point, point, propertiesPanel.getPaintProperties());
        model.addShape(currentText);
        System.out.println("Started text box at: " + point);
    }

    @Override
    public void onMouseDragged(Point point) {
        if (currentText == null) return;
        currentText.setEnd(point);
        model.update();
    }

    @Override
    public void onMouseReleased(Point point) {
        if (currentText == null) return;

        // Finalize the text box
        currentText.activateTextField(canvasPane, this.controller);
        System.out.println("Completed text box creation at: " + point);
    }
}
