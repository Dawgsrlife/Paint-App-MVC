package ca.utoronto.utm.assignment2.paint.shapes;

import ca.utoronto.utm.assignment2.paint.PaintProperties;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

/**
 * The Polygon class represents a polygon shape with a specified number of vertices,
 * a stroke thickness, and fill properties. It provides methods for painting the polygon
 * on a graphics context, checking if the cursor is inside the polygon, and moving the
 * polygon by a specified distance.
 * <p>
 * This class extends the {@link Shape} class and supports both filled and stroked polygons.
 * </p>
 * @author tianji61
 */
public class Polygon extends Shape {

    /**
     * Constructs a Polygon object with the given start and end points and paint properties.
     *
     * @param start The starting point of the polygon.
     * @param end The ending point of the polygon.
     * @param pp The paint properties associated with the polygon.
     */
    public Polygon(Point start, Point end, PaintProperties pp) {
        super(start, end, "Polygon", pp);
    }

    /**
     * Paints the polygon on the provided graphics context.
     * <p>
     * This method draws the polygon with the specified stroke and fill properties using the
     * given {@link GraphicsContext}.
     * </p>
     *
     * @param g2d The graphics context used to paint the polygon.
     */
    @Override
    public void paint(GraphicsContext g2d) {
        if (getProperties().getStrokeThickness() != 0.0) {
            g2d.setStroke(getProperties().getStrokeColor());
            g2d.setLineWidth(getProperties().getStrokeThickness());
            ArrayList<double[]> info = getPaintInfo(0);
            g2d.strokePolygon(info.get(0), info.get(1), getProperties().getVertices());
        }
        if (getProperties().isFilled()) {
            fill(g2d);
        }
    }

    /**
     * Fills the polygon with the specified fill color.
     * <p>
     * This method fills the polygon with the color specified in the paint properties.
     * </p>
     *
     * @param g2d The graphics context used to fill the polygon.
     */
    @Override
    protected void fill(GraphicsContext g2d) {
        g2d.setFill(getProperties().getFillColor());
        ArrayList<double[]> info = getPaintInfo(getProperties().getStrokeThickness() / 2);
        g2d.fillPolygon(info.get(0), info.get(1), getProperties().getVertices());
    }

    /**
     * Calculates the basic variables required to draw the polygon (radius and angle).
     *
     * @return An array containing the starting point, radius, and angle of the polygon.
     */
    @Override
    public double[] getPaintInfo() {
        // calculate basic variables for calculation vertices
        double radius = Math.sqrt(Math.pow(getStart().getX() - getEnd().getX(), 2) +
                Math.pow(getStart().getY() - getEnd().getY(), 2));
        double theta = Math.atan((getStart().getY() - getEnd().getY()) / (getStart().getX() - getEnd().getX()));
        return new double[]{getStart().getX(), getStart().getY(), radius, theta};
    }

    /**
     * Calculates the vertices of the polygon, considering the border width.
     *
     * @param border The width of the border.
     * @return An {@link ArrayList} containing the x and y coordinates of the polygon vertices.
     */
    public ArrayList<double[]> getPaintInfo(double border) {
        // get actual vertices
        ArrayList<double[]> result = new ArrayList<>();
        result.add(new double[getProperties().getVertices()]);
        result.add(new double[getProperties().getVertices()]);
        double[] info = getPaintInfo();
        for (int i = 0; i < getProperties().getVertices(); i++) {
            result.get(0)[i] = info[0]+ (info[2] - border) * Math.cos(info[3] + (Math.PI * 2) * i / getProperties().getVertices());
            result.get(1)[i] = info[1] + (info[2] - border) * Math.sin(info[3] + (Math.PI * 2) * i / getProperties().getVertices());
        }
        return result;
    }

    /**
     * Checks if the given point is inside the polygon's boundaries.
     * <p>
     * The method checks if the point is inside the polygon based on the fill and stroke properties.
     * </p>
     *
     * @param p The point to check.
     * @return true if the point is inside the polygon, false otherwise.
     */
    @Override
    public boolean includeCursor(Point p) {
        javafx.scene.shape.Polygon outer = getFxPolygon(- getProperties().getStrokeThickness() / 2);
        if (getProperties().isFilled()) {
            return outer.contains(p.getX(), p.getY());
        }
        javafx.scene.shape.Polygon inner = getFxPolygon(getProperties().getStrokeThickness() / 2);
        return outer.contains(p.getX(), p.getY()) & !inner.contains(p.getX(), p.getY());
    }

    /**
     * Returns an instance of a JavaFX {@link javafx.scene.shape.Polygon} that represents
     * the polygon for selection or manipulation purposes.
     *
     * @param border The width of the border.
     * @return A JavaFX polygon instance representing the shape.
     */
    private javafx.scene.shape.Polygon getFxPolygon(double border) {
        ArrayList<double[]> info = getPaintInfo(border);
        double[] vertices = new double[info.get(0).length * 2];
        int count = 0;
        for (int i = 0; i < vertices.length; i += 2) {
            vertices[i] = info.get(0)[count];
            vertices[i + 1] = info.get(1)[count];
            count++;
        }
        return new javafx.scene.shape.Polygon(vertices);
    }

    /**
     * Moves the polygon by the specified distance in the x and y directions.
     *
     * @param dx The distance to move in the x direction.
     * @param dy The distance to move in the y direction.
     */
    @Override
    public void move(double dx, double dy) {
        getStart().setX(getStart().getX() + dx);
        getStart().setY(getStart().getY() + dy);
        getEnd().setX(getEnd().getX() + dx);
        getEnd().setY(getEnd().getY() + dy);
    }
}
