package ca.utoronto.utm.assignment2.paint.shapes;

import ca.utoronto.utm.assignment2.paint.PaintProperties;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Ellipse;

/**
 * The Oval class represents an oval shape that can be drawn, filled, and interacted with on the canvas.
 * <p>
 * This class extends the Shape class and provides methods for rendering an oval shape on the canvas,
 * as well as checking whether a cursor is inside the oval's bounds. It also supports moving the oval
 * and applying various paint properties such as stroke and fill.
 * </p>
 * @author huaethan
 */
public class Oval extends Shape {

    /**
     * Constructs an Oval shape with the specified start and end points and paint properties.
     *
     * @param start The starting point of the oval.
     * @param end The ending point of the oval, determining its size.
     * @param pp The paint properties for the oval (e.g., stroke color, fill color).
     */
    public Oval(Point start, Point end, PaintProperties pp) {
        super(start, end, "Oval", pp);
    }

    /**
     * Paints the oval on the given graphics context, applying stroke and fill properties.
     * <p>
     * If the stroke thickness is greater than zero, the oval's outline is drawn with the specified
     * stroke color and thickness. If the oval is filled, it is filled with the specified fill color.
     * </p>
     *
     * @param g2d The graphics context to draw on.
     */
    @Override
    public void paint(GraphicsContext g2d) {
        if (getProperties().getStrokeThickness() != 0.0) {
            g2d.setStroke(getProperties().getStrokeColor());
            g2d.setLineWidth(getProperties().getStrokeThickness());
            double[] info = getPaintInfo();
            g2d.strokeOval(info[0], info[1], info[2], info[3]);
        }
        if (getProperties().isFilled()) {
            fill(g2d);
        }
    }

    /**
     * Fills the oval shape on the graphics context.
     * <p>
     * The filling is applied inside the stroke area, adjusting for the stroke thickness.
     * </p>
     *
     * @param g2d The graphics context to fill the oval on.
     */
    @Override
    protected void fill(GraphicsContext g2d) {
        g2d.setFill(getProperties().getFillColor());
        double[] info = getPaintInfo();
        double width = getProperties().getStrokeThickness() / 2;
        g2d.fillOval(info[0] + width, info[1] + width,
                info[2] - width * 2, info[3] - width * 2);
    }

    /**
     * Retrieves the bounding box information for painting the oval.
     * <p>
     * This includes the starting coordinates (top-left corner), width, and height of the oval.
     * </p>
     *
     * @return An array containing the x and y coordinates for the top-left corner,
     *         and the width and height of the oval.
     */
    @Override
    protected double[] getPaintInfo() {
        // calculate basic info of a oval
        double startX = Math.min(getStart().getX(), getEnd().getX());
        double startY = Math.min(getStart().getY(), getEnd().getY());
        double width = Math.abs(getEnd().getX() - getStart().getX());
        double height = Math.abs(getEnd().getY() - getStart().getY());
        return new double[]{startX, startY, width, height};
    }

    /**
     * Checks if the given point is within the bounds of the oval.
     * <p>
     * This method checks if the cursor is inside the oval's border, considering whether
     * the oval is filled or just stroked. It uses the Ellipse class to determine if the point
     * lies within the area defined by the oval's stroke and fill.
     * </p>
     *
     * @param p The point to check.
     * @return True if the point is inside the oval's boundaries, false otherwise.
     */
    @Override
    public boolean includeCursor(Point p) {
        // check if cursor is inside the border (for holographes)
        double[] info = getPaintInfo();
        Ellipse outer = new Ellipse(info[0] + (info[2] / 2), info[1] + (info[3] / 2),
                info[2] / 2 + getProperties().getStrokeThickness() / 2,
                info[3] / 2 + getProperties().getStrokeThickness() / 2);
        if (getProperties().isFilled()) {
            return outer.contains(p.getX(), p.getY());
        }
        // check if cursor is inside the fill (for filled graphs)
        Ellipse inner = new Ellipse(info[0] + (info[2] / 2), info[1] + (info[3] / 2),
                info[2] / 2 - getProperties().getStrokeThickness() / 2,
                info[3] / 2 - getProperties().getStrokeThickness() / 2);
        return outer.contains(p.getX(), p.getY()) & !inner.contains(p.getX(), p.getY());
    }


    /**
     * Moves the oval by a specified amount along the x and y axes.
     * <p>
     * This method adjusts the starting and ending points of the oval by the given deltas.
     * </p>
     *
     * @param dx The change in the x-coordinate.
     * @param dy The change in the y-coordinate.
     */
    @Override
    public void move(double dx, double dy) {
        getStart().setX(getStart().getX() + dx);
        getStart().setY(getStart().getY() + dy);
        getEnd().setX(getEnd().getX() + dx);
        getEnd().setY(getEnd().getY() + dy);
    }
}
