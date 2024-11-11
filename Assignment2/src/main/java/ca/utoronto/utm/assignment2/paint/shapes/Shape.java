package ca.utoronto.utm.assignment2.paint.shapes;

import ca.utoronto.utm.assignment2.paint.PaintProperties;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

/**
 * Abstract base class representing a shape instance on the canvas.
 * A shape is defined by a start point, an end point, and a set of paint properties.
 * It can be painted on the canvas and may be moved or checked for cursor inclusion.
 * <p>
 * Concrete subclasses of this class should implement the details of painting,
 * filling, and cursor inclusion based on their specific shape.
 * </p>
 *
 * <p>Each shape has a start point, an end point, and an associated PaintProperties object. The
 * shape can be painted (with or without filling), moved around on the canvas, and checked if
 * a point lies within its bounds. Shapes can be finalized to indicate they are ready for use.</p>
 *
 * @author tianji61 / mengale1
 */
public abstract class Shape {

    // Default Fields
    private Point start; // The starting point of the shape.
    private Point end;   // The ending point of the shape.
    private String type; // The type of the shape (e.g., "Rectangle", "Circle").
    private PaintProperties properties; // Paint properties, such as stroke color, thickness, and fill color.
    private final ArrayList<Point> points = new ArrayList<>(); // A list of points that define the shape.

    // Tracking Fields
    private boolean finalized; // A flag indicating whether the shape has been finalized.

    /**
     * Constructs a new Shape with the specified start and end points, type, and paint properties.
     *
     * @param start The starting point of the shape.
     * @param end The ending point of the shape.
     * @param type The type of the shape (e.g., "Rectangle", "Circle").
     * @param properties The paint properties, such as stroke color and thickness, and fill color.
     */
    public Shape(Point start, Point end, String type, PaintProperties properties) {
        this.start = start; // Initialize the start point.
        this.end = end;     // Initialize the end point.
        this.type = type;   // Initialize the shape type.
        this.properties = properties; // Initialize the paint properties.
        this.finalized = true; // Set shape as finalized by default.
    }

    /**
     * Paints the border of the shape on the canvas. The exact implementation is defined in subclasses.
     * This method is called by the paint function to render the shape's outline.
     *
     * @param g2d The GraphicsContext used to paint the shape.
     */
    abstract public void paint(GraphicsContext g2d);

    /**
     * Fills the shape with the appropriate color if the shape is marked as filled.
     * The exact implementation is defined in subclasses.
     * This method is used when the shape needs to be filled in addition to being outlined.
     *
     * @param g2d The GraphicsContext used to fill the shape.
     */
    abstract protected void fill(GraphicsContext g2d);

    /**
     * Returns basic paint information for the shape, such as its coordinates and dimensions.
     * This information is used by the GraphicsContext to render the shape correctly on the canvas.
     *
     * @return An array of doubles representing the position and size (e.g., start X, start Y, width, height).
     */
    abstract protected double[] getPaintInfo();

    /**
     * Checks whether the given point is inside the shape. This method is typically used to detect
     * if a mouse cursor is within the bounds of the shape.
     *
     * @param p The point to check, typically derived from a mouse event's coordinates.
     * @return true if the point is inside the shape, false otherwise.
     */
    abstract public boolean includeCursor(Point p);

    /**
     * Moves the shape by a specified displacement vector. This method updates the start and end points
     * of the shape based on the displacement values.
     *
     * @param dx The displacement along the x-axis.
     * @param dy The displacement along the y-axis.
     */
    abstract public void move(double dx, double dy);

    // Getters and Setters for Shape Fields

    /**
     * Gets the starting point of the shape.
     *
     * @return The start point of the shape.
     */
    public Point getStart() {
        return start;
    }

    /**
     * Sets the starting point of the shape.
     *
     * @param start The new starting point of the shape.
     */
    public void setStart(Point start) {
        this.start = start;
    }

    /**
     * Gets the ending point of the shape.
     *
     * @return The end point of the shape.
     */
    public Point getEnd() {
        return end;
    }

    /**
     * Sets the ending point of the shape.
     *
     * @param end The new ending point of the shape.
     */
    public void setEnd(Point end) {
        this.end = end;
    }

    /**
     * Gets the type of the shape (e.g., "Rectangle", "Circle").
     *
     * @return The type of the shape.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the shape.
     *
     * @param type The new type of the shape.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the paint properties associated with the shape.
     *
     * @return The paint properties of the shape.
     */
    public PaintProperties getProperties() {
        return properties;
    }

    /**
     * Sets the paint properties for the shape.
     *
     * @param properties The new paint properties of the shape.
     */
    public void setProperties(PaintProperties properties) {
        this.properties = properties;
    }

    /**
     * Gets the list of points that define the shape.
     *
     * @return A list of points representing the shape.
     */
    public ArrayList<Point> getPoints() {
        return points;
    }

    /**
     * Returns a string representation of the shape, including its start and end points, type, and properties.
     * This is useful for debugging and logging purposes.
     *
     * @return A string representation of the shape.
     */
    @Override
    public String toString() {
        return start + "," + end + "," + type + "," + properties;
    }

    /**
     * Marks the shape as finalized. This method can be overridden in subclasses if additional finalization
     * logic is required (e.g., completing the creation of the shape).
     */
    public void finalizeShape() {}

    /**
     * Checks whether the shape has been finalized. A finalized shape is ready for use.
     *
     * @return true if the shape is finalized, false otherwise.
     */
    public boolean isFinalized() {
        return finalized;
    }

    /**
     * Sets whether the shape is finalized.
     *
     * @param finalized true if the shape should be marked as finalized, false otherwise.
     */
    public void setFinalized(boolean finalized) {
        this.finalized = finalized;
    }
}
