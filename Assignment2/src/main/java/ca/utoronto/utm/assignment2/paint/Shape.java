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
    // Default fields
    private Point start;
    private Point end;
    private String type;
    private boolean filled;
    private Color color;
    private Color borderColor;
    private double borderWidth;

    // State flags
    private boolean canFinalize;
    private boolean displayShape;

    public Shape(Point start, Point end, String type, boolean filled, Color color, Color borderColor, double borderWidth) {
        this.start = start;
        this.end = end;
        this.type = type;
        this.filled = filled;
        this.color = color;
        this.borderColor = borderColor;
        this.borderWidth = borderWidth;

        this.canFinalize = false;
        this.displayShape = false;
    }

    abstract void paint(GraphicsContext g2d);

    protected abstract void fill(GraphicsContext g2d);

    protected abstract double[] getPaintInfo();

    /**
     * Handles the behaviour of the Shape when the mouse is moved.
     * <p>
     * There is no operation for the generic shape.
     *
     * @param point
     */
    public void onMouseMoved(Point point) {
        // No-op for default behaviour
    }

    /**
     * Handles the behaviour of the Shape when the mouse is pressed.
     * <p>
     * The shape only adds its initial point and does nothing else.
     *
     * @param point
     * @param isPrimaryButtonDown
     * @param isSecondaryButtonDown
     */
    public void onMousePressed(Point point, boolean isPrimaryButtonDown, boolean isSecondaryButtonDown) {
        if (isPrimaryButtonDown && isSecondaryButtonDown) {
            // Do nothing
        } else if (isPrimaryButtonDown) {
            // Add the initial point of the Shape
            setStart(point);  // see if you keep this later or not (corresponds with docstring)
        } else if (isSecondaryButtonDown) {
            // Consider adding functionality here
        }
    }

    /**
     * Handles the behaviour of the Shape when the mouse is dragged.
     * <p>
     * The shape obtains a new ending point, to wherever the mouse cursor is dragged.
     *
     * @param point
     * @param isPrimaryButtonDown
     * @param isSecondaryButtonDown
     */
    public void onMouseDragged(Point point, boolean isPrimaryButtonDown, boolean isSecondaryButtonDown) {
        if (isPrimaryButtonDown && isSecondaryButtonDown) {
            // Do nothing
        } else if (isPrimaryButtonDown) {
            // Set the end point
            setEnd(point);
        } else if (isSecondaryButtonDown) {
            // Consider adding functionality here (like with secondary colours)
        }
    }

    /**
     * Handles the behaviour of the Shape when the mouse is released.
     * <p>
     * Flags that this Shape can be finalized.
     *
     * @param point
     */
    public void onMouseReleased(Point point) {
        this.canFinalize = true;  // Finalize shape flag
    }

    public boolean canFinalize() {
        return canFinalize;
    }

    public boolean canDisplayShape() {
        return displayShape;
    }

    public void setCanFinalize(boolean canFinalize) {
        this.canFinalize = canFinalize;
    }

    public void setDisplayShape(boolean displayShape) {
        this.displayShape = displayShape;
    }

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

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public double getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(double borderWidth) {
        this.borderWidth = borderWidth;
    }

    @Override
    public String toString() {
        return start + "," + end + "," + type + "," + filled + "," + color + "," + borderColor + "," + borderWidth;
    }
}
