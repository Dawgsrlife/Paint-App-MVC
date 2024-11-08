package ca.utoronto.utm.assignment2.paint;

import ca.utoronto.utm.assignment2.paint.controlPanels.PropertiesPanel;
import ca.utoronto.utm.assignment2.paint.controlPanels.ShapeChooserPanel;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class PaintController implements EventHandler<MouseEvent> {

    private final PaintModel model;
    private final ShapeChooserPanel scp;
    private final PropertiesPanel pp;
    private Shape shape;
    private final Pane canvasPane;

    public PaintController(PaintModel model, ShapeChooserPanel scp, PropertiesPanel pp, Pane canvasPane) {
        this.model = model;
        this.scp = scp;
        this.pp = pp;
        this.canvasPane = canvasPane;
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
            if (scp.getMode().equals("Text") & this.shape != null) {
                // Finalize the Text shape and activate the TextField for input
                Text textShape = (Text) this.shape;
                textShape.activateTextField(canvasPane, this);  // Display the TextField after drawing the text box
            } else {
                // Finalize and add the shape to the model for other modes
                finalizeShape();
            }
        } else if (scp.getMode().equals("Polyline") & event.equals(MouseEvent.MOUSE_PRESSED) & mouseEvent.isSecondaryButtonDown() & !scp.getMode().equals("select")) {
            // finalize by putting shape into models array (polyline)
            finalizeShape();
            System.out.println("Finished " + scp.getMode());
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
     * This method is called by Text shape after user input is complete,
     * saving the finalized text into the model.
     */
    public void persistTextBox(Text textShape) {
        model.addShape(textShape);
    }
}
