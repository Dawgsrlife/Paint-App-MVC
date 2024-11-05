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
    private EditingPanel ep;
    private Shape shape;

    public PaintController(PaintModel model, ShapeChooserPanel scp, PropertiesPanel pp, EditingPanel ep) {
        this.model = model;
        this.scp = scp;
        this.pp = pp;
        this.ep = ep;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        Point point = new Point(mouseEvent.getX(), mouseEvent.getY());

        // determine mouse event type
        EventType<MouseEvent> event = (EventType<MouseEvent>) mouseEvent.getEventType();
        if (event.equals(MouseEvent.MOUSE_MOVED)) {
            ep.setMouseCoords(mouseEvent);
        } else if (event.equals(MouseEvent.MOUSE_PRESSED) & mouseEvent.isPrimaryButtonDown()) {
            if (scp.getMode().equals("Polyline") & this.shape != null) {
                this.shape.setEnd(point);
                model.addTempShape(this.shape);
            } else {
                // If the shape to be created is a Polyline, then finalize it
                if (model.getTempShape() instanceof Polyline) {
                    finalizeShape();
                }
                // create shape and initialize starting point on MOUSE_PRESSED
                System.out.println("Started " + scp.getMode());
                this.shape = getPaintStrategy(scp.getMode(), point, pp.getPaintProperties());

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

    /**
     * this method returns a corresponding shape instance to mode
     * @param mode what mode is activated currently
     * @param point starting point
     * @param pp paint properties
     * @return an instance of a shape instance
     */
    private Shape getPaintStrategy(String mode, Point point, PaintProperties pp) {
        return switch (mode) {
            case "Circle" -> new Circle(point, point, pp);
            case "Rectangle" -> new Rectangle(point, point, pp);
            case "Square" -> new Square(point, point, pp);
            case "Oval" -> new Oval(point, point, pp);
            case "Squiggle" -> new Squiggle(point, pp);
            case "Polyline" -> new Polyline(point, pp);
            case "Triangle" -> new Triangle(point, point, pp);
            case "PrecisionEraser" -> new PrecisionEraser(point, pp);
            default -> throw new IllegalArgumentException("Unknown mode: " + mode);
        };
    }
}
