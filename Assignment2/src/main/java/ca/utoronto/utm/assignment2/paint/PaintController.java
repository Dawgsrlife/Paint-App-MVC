package ca.utoronto.utm.assignment2.paint;

import ca.utoronto.utm.assignment2.paint.controlPanels.EditingPanel;
import ca.utoronto.utm.assignment2.paint.controlPanels.PropertiesPanel;
import ca.utoronto.utm.assignment2.paint.controlPanels.ShapeChooserPanel;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class PaintController implements EventHandler<MouseEvent> {

    private final PaintModel model;
    private final ShapeChooserPanel scp;
    private final PropertiesPanel pp;
    private final EditingPanel ep;
    private Pane canvasPane;
    private Shape shape;
    private TextField activeTextField;
    private Text activeText;

    public PaintController(PaintModel model, ShapeChooserPanel scp, PropertiesPanel pp, EditingPanel ep) {
        this(model, scp, pp, ep, new Pane());
    }

    public PaintController(PaintModel model, ShapeChooserPanel scp, PropertiesPanel pp, EditingPanel ep, Pane canvasPane) {
        this.model = model;
        this.scp = scp;
        this.pp = pp;
        this.ep = ep;
        this.canvasPane = canvasPane; //Store user's text
    }


    @Override
    public void handle(MouseEvent mouseEvent) {
        Point point = new Point(mouseEvent.getX(), mouseEvent.getY());

        // determine mouse event type
        EventType<MouseEvent> event = (EventType<MouseEvent>) mouseEvent.getEventType();
        if (event.equals(MouseEvent.MOUSE_MOVED)) {
            ep.setMouseCoords(mouseEvent);
        } else if (event.equals(MouseEvent.MOUSE_PRESSED) & mouseEvent.isPrimaryButtonDown()) {
            if (scp.getMode().equals("TextBox")) {
                handleTextBoxEvent(mouseEvent, point);}
            if (scp.getMode().equals("Polyline") & this.shape != null) {
                this.shape.setEnd(point);
                model.addTempShape(this.shape);
            }else {
                // If the shape to be created is a Polyline, then finalize it
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

    /**
     * This is a helper method to handle TextBox
     * @param mouseEvent
     * @param point
     */
    private void handleTextBoxEvent(MouseEvent mouseEvent, Point point) {
        if (scp.getMode().equals("TextBox")) {
            // Create TextBox and add to model
            TextBox textBox = new TextBox(point, point, pp.getPaintProperties().isFilled(),pp.getPaintProperties().getFillColor(),
                    pp.getPaintProperties().getBorderColor(), pp.getPaintProperties().getBorderWidth());
            model.addTempShape(textBox);


            textBox.activateTextField(canvasPane, this);
            model.setActiveTextBox(textBox);
        }
    }

    public void setCanvasPane(Pane canvasPane) {
        this.canvasPane = canvasPane;
    }

    public void persistTextBox(TextBox textBox) {
        model.addShape(textBox);
        model.setActiveTextBox(textBox);
    }
}
