package ca.utoronto.utm.assignment2.paint;

import javafx.event.EventType;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Observable;

public class PaintModel extends Observable {
    private final ArrayList<Shape> shapes = new ArrayList<>();
    private final ArrayList<Shape> tempShapes = new ArrayList<>();

    private Shape shape;

    /**
     * Add a Shape instance into steps list
     * @param shape a shape instance
     */
    public void addShape(Shape shape) {
        tempShapes.clear();
        shapes.add(shape);
        this.setChanged();
        this.notifyObservers();
    }

    /* future undo functionality
    public void removeShape(Shape shape) {
        shapes.remove(shape);
        System.out.println("Shape removed: " + shape + ", Remaining shapes: " + shapes.size());
        this.setChanged();
        this.notifyObservers();
    }
     */

    /**
     * Get all steps queued to be painted
     * @return An array list of all the steps
     */
    public ArrayList<Shape> getShapes() {
        ArrayList<Shape> tempShapes = new ArrayList<>();
        tempShapes.addAll(shapes);
        tempShapes.addAll(this.tempShapes);
        return tempShapes;
    }

    /**
     * Add a Shape instance into tempShapes list
     * for buffer indicating when  mouse is not
     * released yet
     * @param shape a shape instance
     */
    public void addTempShape(Shape shape) {
        tempShapes.add(shape);
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * this method finalizes a sketching process
     * clears template shape
     * adds finalized version into models array
     */
    private void finalizeShape() {
        addShape(this.shape);
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
     * @return a instance of a shape instance
     */
    private Shape getPaintStrategy(String mode, Point point, PaintProperties pp) {
        return switch (mode) {
            case "Circle" -> new Circle(point, point, pp);
            case "Rectangle" -> new Rectangle(point, point, pp);
            case "Square" -> new Square(point, point, pp);
            case "Oval" -> new Oval(point, point, pp);
            case "Squiggle" -> new Squiggle(point, pp);
            case "Polyline" -> new Polyline(point, pp);
            default -> throw new IllegalArgumentException("Unknown mode: " + mode);
        };
    }


    /**
     * this method updates current paint array
     * @param mode what mode is activated currently
     * @param mouseEvent mouseEvent invoked this update
     * @param point starting point
     * @param pp paint properties
     */
    public void update(String mode, MouseEvent mouseEvent, Point point, PaintProperties pp) {
        // determine mouse event type
        EventType<MouseEvent> event = (EventType<MouseEvent>) mouseEvent.getEventType();
        if (event.equals(MouseEvent.MOUSE_PRESSED) & mouseEvent.isPrimaryButtonDown()) {
            if (mode.equals("Polyline") & this.shape != null) {
                this.shape.setEnd(point);
            } else {
                // create shape and initialize starting point on MOUSE_PRESSED
                System.out.println("Started " + mode);
                this.shape = getPaintStrategy(mode, point, pp);
                addTempShape(this.shape);
            }
        } else if (event.equals(MouseEvent.MOUSE_DRAGGED)) {
            // update shape ending point on MOUSE_DRAGGED
            this.shape.setEnd(point);
        } else if (!mode.equals("Polyline") & event.equals(MouseEvent.MOUSE_RELEASED)) {
            // finalize by putting shape into models array
            finalizeShape();
        } else if (mode.equals("Polyline") & event.equals(MouseEvent.MOUSE_PRESSED) & mouseEvent.isSecondaryButtonDown()) {
            // finalize by putting shape into models array (polyline)
            finalizeShape();
        }
    }
}