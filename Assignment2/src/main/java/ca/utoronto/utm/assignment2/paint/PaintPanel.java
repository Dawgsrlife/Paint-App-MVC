package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.Canvas;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.Observable;
import java.util.Observer;

public class PaintPanel extends Canvas implements EventHandler<MouseEvent>, Observer {
    private String mode = "Circle";
    private PaintModel model;
    private PropertiesPanel propertiesPanel;
    private String cursorCoordinate;

    public PaintPanel(PaintModel model, PropertiesPanel propertiesPanel) {
        super(500, 500);
        this.model = model;
        this.model.addObserver(this);
        this.propertiesPanel = propertiesPanel;

        this.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        this.addEventHandler(MouseEvent.MOUSE_RELEASED, this);
        this.addEventHandler(MouseEvent.MOUSE_MOVED, this);
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
        this.addEventHandler(MouseEvent.MOUSE_DRAGGED, this);
    }

    /**
     * Controller aspect of this
     */
    public void setMode(String mode) {
        this.mode = mode;
        System.out.println(this.mode);
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        // Later when we learn about inner classes...
        // https://docs.oracle.com/javafx/2/events/DraggablePanelsExample.java.html

        model.update(this.mode, mouseEvent,
                new Point(mouseEvent.getX(), mouseEvent.getY()),
                propertiesPanel.getPaintProperties());

        this.cursorCoordinate = "X = " + mouseEvent.getX() + ", Y = " + mouseEvent.getY();
        update();
    }

    @Override
    public void update(Observable o, Object arg) {
        // get painter
        GraphicsContext g2d = this.getGraphicsContext2D();
        // draw background
        g2d.setFill(Color.WHITE);
        g2d.fillRect(0,0,this.getWidth(),this.getHeight());
        // draw steps
        for (Shape s : this.model.getShapes()) {
            s.paint(g2d);
        }
        // draw cursorCoordinate
        g2d.setFill(Color.BLACK);
        g2d.fillText(cursorCoordinate, 3, 13);
    }

    /**
     * Refresh canvas, repaint everything existed
     */
    public void update() {
        update(model, new Object());
    }
}
