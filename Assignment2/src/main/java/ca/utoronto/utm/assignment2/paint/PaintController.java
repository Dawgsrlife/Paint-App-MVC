package ca.utoronto.utm.assignment2.paint;

import ca.utoronto.utm.assignment2.paint.controlPanels.PropertiesPanel;
import ca.utoronto.utm.assignment2.paint.controlPanels.ShapeChooserPanel;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;

public class PaintController implements EventHandler<MouseEvent> {

    private final PaintModel model;
    private final ShapeChooserPanel scp;
    private final PropertiesPanel pp;
    private Shape shape;

    public PaintController(PaintModel model, ShapeChooserPanel scp, PropertiesPanel pp) {
        this.model = model;
        this.scp = scp;
        this.pp = pp;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        Point point = new Point(mouseEvent.getX(), mouseEvent.getY());
        pp.setMouseCoords(point);

        // determine mouse event type
        EventType<MouseEvent> event = (EventType<MouseEvent>) mouseEvent.getEventType();
        if (event.equals(MouseEvent.MOUSE_PRESSED) & mouseEvent.isPrimaryButtonDown()) {
            if (scp.getMode().equals("select")) {
                this.shape = model.getSelected(point);
                if (this.shape != null) {
                    pp.loadPaintProperties(shape);
                }
            } else if (scp.getMode().equals("Polyline") & this.shape != null) {
                this.shape.setEnd(point);
                model.addTempShape(this.shape);
            } else {
                // If the shape to be created is a Polyline, then finalize it
                if (model.getTempShape() instanceof Polyline) {
                    finalizeShape();
                }
                // create shape and initialize starting point on MOUSE_PRESSED
                System.out.println("Started " + scp.getMode());
                this.shape = PaintStrategy.getPaintStrategy(scp.getMode(), point, point, pp.getPaintProperties(), null);
            }
        } else if (!scp.getMode().equals("Polyline") & event.equals(MouseEvent.MOUSE_DRAGGED) & mouseEvent.isPrimaryButtonDown()) {
            if (scp.getMode().equals("select") & this.shape != null) {
                System.out.println("drag");
            } else if (this.shape != null){
                // update shape ending point on MOUSE_DRAGGED
                this.shape.setEnd(point);
                model.addTempShape(this.shape);
            }
        } else if (!scp.getMode().equals("Polyline") & event.equals(MouseEvent.MOUSE_RELEASED) & !scp.getMode().equals("select")) {
            // finalize by putting shape into models array
            finalizeShape();
        } else if (scp.getMode().equals("Polyline") & event.equals(MouseEvent.MOUSE_PRESSED) & mouseEvent.isSecondaryButtonDown() & !scp.getMode().equals("select")) {
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
