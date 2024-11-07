package ca.utoronto.utm.assignment2.paint;

import ca.utoronto.utm.assignment2.paint.controlPanels.EditingPanel;
import ca.utoronto.utm.assignment2.paint.controlPanels.PropertiesPanel;
import ca.utoronto.utm.assignment2.paint.controlPanels.ShapeChooserPanel;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;

public class PaintController implements EventHandler<MouseEvent> {

    private final PaintModel model;
    private final ShapeChooserPanel scp;
    private final PropertiesPanel pp;
    private final EditingPanel ep;
    private Shape shape;

    public PaintController(PaintModel model, ShapeChooserPanel scp, PropertiesPanel pp, EditingPanel ep) {
        this.model = model;
        this.scp = scp;
        this.pp = pp;
        this.ep = ep;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        // Grab Point and Mouse Event Type
        Point point = new Point(mouseEvent.getX(), mouseEvent.getY());
        EventType<MouseEvent> eventType = (EventType<MouseEvent>) mouseEvent.getEventType();

        // Corresponding Mouse Behaviour
        switch (eventType.getName()) {
            case "MOUSE_MOVED" -> {
                ep.setMouseCoords(mouseEvent);
                shape.onMouseMoved(point);
            }
            case "MOUSE_PRESSED" -> {
                shape.onMousePressed(point, mouseEvent.isPrimaryButtonDown(), mouseEvent.isSecondaryButtonDown());

                if (shape.canDisplayShape()) model.addTempShape(shape);
                if (shape.canFinalize()) finalizeShape();

                System.out.println("Started " + scp.getMode());
                this.shape = PaintStrategy.getPaintStrategy(scp.getMode(), point, point, pp.getPaintProperties(), null);
            }
            case "MOUSE_DRAGGED" -> {
                shape.onMouseDragged(point, mouseEvent.isPrimaryButtonDown(), mouseEvent.isSecondaryButtonDown());

                if (shape.canDisplayShape()) model.addTempShape(shape);
            }
            case "MOUSE_RELEASED" -> {
                shape.onMouseReleased(point);

                if (shape.canDisplayShape()) finalizeShape();
            }
        }

        if (event.equals(MouseEvent.MOUSE_MOVED)) {
            ep.setMouseCoords(mouseEvent);
        } else if (event.equals(MouseEvent.MOUSE_PRESSED) & mouseEvent.isPrimaryButtonDown()) {
            if (scp.getMode().equals("Polyline") & this.shape != null) {
                this.shape.setEnd(point);
                model.addTempShape(this.shape);
            } else {
                // If the shape to be created is a Polyline, then finalize it

                // Instead, do !model.getTempShape().canFinalize() to check if you need to finalize
                if (model.getTempShape() instanceof Polyline) {
                    finalizeShape();
                }
                // create shape and initialize starting point on MOUSE_PRESSED
                System.out.println("Started " + scp.getMode());
                this.shape = PaintStrategy.getPaintStrategy(scp.getMode(), point, point, pp.getPaintProperties(), null);

            }
        } else if (!scp.getMode().equals("Polyline") & event.equals(MouseEvent.MOUSE_DRAGGED) & mouseEvent.isPrimaryButtonDown()) {
            // update shape ending point on MOUSE_DRAGGED
            this.shape.setEnd(point);
            model.addTempShape(this.shape);
        } else if (!scp.getMode().equals("Polyline") & event.equals(MouseEvent.MOUSE_RELEASED)) {
            // finalize by putting shape into models array
            finalizeShape();
        } else if (scp.getMode().equals("Polyline") & event.equals(MouseEvent.MOUSE_PRESSED) & mouseEvent.isSecondaryButtonDown()) {
            // finalize by putting shape into models array (polyline)
            finalizeShape();
        }
    }

    /**
     * this method finalizes a sketching process
     * clears template shape
     * adds finalized version into models array
     */
    public void finalizeShape() {
        model.addShape(this.shape);
        // clean cache on MOUSE_RELEASED
        if (this.shape != null) {
            System.out.println("    ^ Added");
            this.shape = null;
        }
    }
}
