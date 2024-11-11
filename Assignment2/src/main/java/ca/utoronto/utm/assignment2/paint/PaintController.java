package ca.utoronto.utm.assignment2.paint;

import ca.utoronto.utm.assignment2.paint.controlPanels.PropertiesPanel;
import ca.utoronto.utm.assignment2.paint.controlPanels.ShapeChooserPanel;
import ca.utoronto.utm.assignment2.paint.shapes.Point;
import ca.utoronto.utm.assignment2.paint.shapes.Text;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class PaintController implements EventHandler<MouseEvent> {
    private final PaintModel model;
    private final ShapeChooserPanel scp;
    private final PropertiesPanel pp;
    private final Pane canvasPane;

    private ModeStrategy modeStrategy;

    public PaintController(PaintModel model, ShapeChooserPanel scp, PropertiesPanel pp, Pane canvasPane) {
        this.model = model;
        this.scp = scp;
        this.pp = pp;
        this.canvasPane = canvasPane;

        // Default drawing mode
        setModeStrategy(scp.getMode());
    }

    public void setModeStrategy(String mode) {
        switch (mode) {
            case "Select" -> modeStrategy = new SelectModeStrategy(model, pp);
            case "ObjectEraser" -> modeStrategy = new ObjectEraserStrategy(model);
            case "Polyline" -> {
                // Make a new polyline shape first if it doesn't yet exist.
                if (model.getCurrentShape() == null) modeStrategy = new DrawModeStrategy(model, mode, pp);

                modeStrategy = new PolylineModeStrategy(model, mode, pp);
            }
            case "text" -> modeStrategy = new TextModeStrategy(model, mode, pp, canvasPane);
            default -> modeStrategy = new DrawModeStrategy(model, mode, pp);
        }
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        // Grab current point and set coords
        Point point = new Point(mouseEvent.getX(), mouseEvent.getY());
        if (! mouseEvent.getSource().equals(pp)) pp.setMouseCoords(point);

        switch (mouseEvent.getEventType().toString()) {
            case "MOUSE_PRESSED" -> {
                // Update the mode strategy only on mouse press
                setModeStrategy(scp.getMode());
                modeStrategy.onMousePressed(point, mouseEvent.isPrimaryButtonDown(), mouseEvent.isSecondaryButtonDown());
            }
            case "MOUSE_MOVED" -> modeStrategy.onMouseMoved(point);
            case "MOUSE_DRAGGED" -> modeStrategy.onMouseDragged(point);
            case "MOUSE_RELEASED" -> modeStrategy.onMouseReleased(point);
            default -> {
                // If there's a new event type, you can add more cases or handle it
            }
        }
        model.setLastPoint(point);
        model.update();
    }

    /**
     * This method is called by Text shape after user input is complete,
     * saving the finalized text into the model.
     */
    public void persistTextBox(Text textShape) {
        model.addShape(textShape);
    }
}
