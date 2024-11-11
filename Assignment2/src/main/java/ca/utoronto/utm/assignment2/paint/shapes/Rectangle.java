package ca.utoronto.utm.assignment2.paint.shapes;

import ca.utoronto.utm.assignment2.paint.PaintProperties;
import javafx.scene.canvas.GraphicsContext;

/**
 * A class representing a rectangle on the canvas.
 * <p>
 * This class is responsible for handling the drawing and manipulation of a rectangle
 * on the canvas, including its stroke, fill, and movement. It extends the {@link Shape}
 * class, and it supports both filled and outlined rectangles.
 * </p>
 *
 * @author tianji61
 */
public class Rectangle extends Shape {

    /**
     * Constructs a Rectangle object with the specified start and end points and paint properties.
     * The rectangle is drawn between the given points, with the paint properties defining its
     * stroke color, thickness, and fill behavior.
     *
     * @param start The starting point (top-left corner) of the rectangle.
     * @param end The ending point (bottom-right corner) of the rectangle.
     * @param pp The paint properties (stroke color, thickness, fill color).
     */
    public Rectangle(Point start, Point end, PaintProperties pp) {
        super(start, end, "Rectangle", pp);
    }

    /**
     * Paints the rectangle on the canvas using the provided GraphicsContext.
     * This method considers stroke thickness, color, and fill properties while
     * drawing the rectangle.
     *
     * @param g2d The GraphicsContext used for drawing the rectangle on the canvas.
     */
    @Override
    public void paint(GraphicsContext g2d) {
        if (getProperties().getStrokeThickness() != 0.0) {
            g2d.setStroke(getProperties().getStrokeColor());
            g2d.setLineWidth(getProperties().getStrokeThickness());
            double[] info = getPaintInfo();
            g2d.strokeRect(info[0], info[1], info[2], info[3]);
        }
        if (getProperties().isFilled()) {
            fill(g2d);
        }
    }

    /**
     * Fills the rectangle with the fill color using the provided GraphicsContext.
     * The fill is applied inside the stroke area, adjusting for stroke thickness.
     *
     * @param g2d The GraphicsContext used to fill the rectangle on the canvas.
     */
    @Override
    protected void fill(GraphicsContext g2d) {
        g2d.setFill(getProperties().getFillColor());
        double[] info = getPaintInfo();
        double width = getProperties().getStrokeThickness() / 2;
        g2d.fillRect(info[0] + width, info[1] + width,
                info[2] - width * 2, info[3] - width * 2);
    }

    /**
     * Retrieves the basic painting information (x, y, width, height) for the rectangle.
     * The information is calculated based on the start and end points.
     *
     * @return An array of doubles containing the x, y, width, and height of the rectangle.
     */
    @Override
    protected double[] getPaintInfo() {
        double startX = Math.min(getStart().getX(), getEnd().getX());
        double startY = Math.min(getStart().getY(), getEnd().getY());
        double width = Math.abs(getEnd().getX() - getStart().getX());
        double height = Math.abs(getEnd().getY() - getStart().getY());
        return new double[]{startX, startY, width, height};
    }

    /**
     * Determines whether a point is within the bounds of the rectangle.
     * The check considers whether the cursor is inside the rectangle's border or fill area.
     *
     * @param p The point to check for inclusion inside the rectangle.
     * @return true if the point is inside the rectangle's boundaries, false otherwise.
     */
    @Override
    public boolean includeCursor(Point p) {
        double[] info = getPaintInfo();
        javafx.scene.shape.Rectangle outer = new javafx.scene.shape.Rectangle(
                info[0] - getProperties().getStrokeThickness() / 2,
                info[1] - getProperties().getStrokeThickness() / 2,
                info[2] + getProperties().getStrokeThickness(),
                info[3] + getProperties().getStrokeThickness());
        if (getProperties().isFilled()) {
            System.out.println(outer.contains(p.getX(), p.getY()));
            return outer.contains(p.getX(), p.getY());
        }
        javafx.scene.shape.Rectangle inner = new javafx.scene.shape.Rectangle(
                info[0] + getProperties().getStrokeThickness() / 2,
                info[1] + getProperties().getStrokeThickness() / 2,
                info[2] - getProperties().getStrokeThickness(),
                info[3] - getProperties().getStrokeThickness()
        );
        return outer.contains(p.getX(), p.getY()) & !inner.contains(p.getX(), p.getY());
    }

    /**
     * Moves the rectangle by the specified amounts in the x and y directions.
     *
     * @param dx The distance to move the rectangle along the x-axis.
     * @param dy The distance to move the rectangle along the y-axis.
     */
    @Override
    public void move(double dx, double dy) {
        getStart().setX(getStart().getX() + dx);
        getStart().setY(getStart().getY() + dy);
        getEnd().setX(getEnd().getX() + dx);
        getEnd().setY(getEnd().getY() + dy);
    }
}
