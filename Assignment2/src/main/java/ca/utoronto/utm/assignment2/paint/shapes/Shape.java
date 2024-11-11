package ca.utoronto.utm.assignment2.paint.shapes;

import ca.utoronto.utm.assignment2.paint.PaintProperties;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

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
    private PaintProperties properties;
    private final ArrayList<Point> points = new ArrayList<>();

    // Tracking Fields
    private boolean finalized;

    public Shape(Point start, Point end, String type, PaintProperties properties) {
        this.start = start;
        this.end = end;
        this.type = type;
        this.properties = properties;

        this.finalized = true;
    }

    /**
     * This method paints the border of a shape instance (holographs)
     * @param g2d g2d painter
     */
    abstract public void paint(GraphicsContext g2d);

    /**
     * This method fills a shape instance if it's being marked to be filled (filled shapes)
     * @param g2d g2d painter
     */
    abstract protected void fill(GraphicsContext g2d);

    /**
     * This method returns basic paint info of a certain shape, but content could be different
     * for different shape instances
     * @return an array of paint info that could be pass into g2d painter
     */
    abstract protected double[] getPaintInfo();

    /**
     * This method checks if a certain point is included in this shape
     * @param p a point instance, normally a mouse event coords
     * @return true - in the shape / false - not in the shape
     */
    abstract public boolean includeCursor(Point p);

    /**
     * This method transforms this shape instance with a provided displacement vector
     * @param dx displacement on x axis
     * @param dy displacement on y axis
     */
    abstract public void move(double dx, double dy);


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

    public void setProperties(PaintProperties properties) {
        this.properties = properties;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return start + "," + end+ "," + type + "," + properties;
    }

    /**
     * This is a marker for if the shape is finalized from creation
     */
    public void finalizeShape() {}

    public boolean isFinalized() {
        return finalized;
    }

    public void setFinalized(boolean finalized) {
        this.finalized = finalized;
    }
}
