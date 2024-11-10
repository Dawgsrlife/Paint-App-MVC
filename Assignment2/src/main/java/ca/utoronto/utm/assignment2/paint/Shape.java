package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class represents a shape instance on canvas,
 * which is defined to be some shape has:
 * a start point
 * an end point
 * and printable when g2d is passed into getPrint()
 *
 * @author tianji61
 */
public abstract class Shape {
    // Default Fields
    private Point start;
    private Point end;
    private String type;
    private final PaintProperties properties;

    // Tracking Fields
    private boolean finalized;

    public Shape(Point start, Point end, String type, PaintProperties properties) {
        this.start = start;
        this.end = end;
        this.type = type;
        this.properties = properties;
        this.finalized = true;
    }

    abstract void paint(GraphicsContext g2d);

    protected abstract void fill(GraphicsContext g2d);

    protected abstract double[] getPaintInfo();

    abstract boolean includeCursor(Point p);


    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PaintProperties getProperties() {
        return properties;
    }

    @Override
    public String toString() {
        return start + "," + end+ "," + type + "," + properties;
    }

    public void finalizeShape() {}

    public boolean isFinalized() {
        return finalized;
    }

    public void setFinalized(boolean finalized) {
        this.finalized = finalized;
    }
}
