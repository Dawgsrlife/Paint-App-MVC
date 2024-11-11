package ca.utoronto.utm.assignment2.paint.shapes;

import ca.utoronto.utm.assignment2.paint.PaintProperties;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

/**
 * SmartShape extends the Squiggle class to provide functionality
 * for creating shapes that can be finalized into closed polygons
 * based on certain conditions, such as proximity of points and
 * angular tolerance.
 *
 * @author huaethan
 */
public class SmartShape extends Squiggle {
    // Constants defining shape behavior
    final private double CLOSE_CONDITION = 15; // distance between first and last point must be within this value
    final private double SLOPE_LENIENCE = 2 * Math.PI / 3; // 120 degrees in radians
    final private double VERTEX_DISTANCE = 30; // defines the minimum distance between two vertices

    private boolean isSquiggle; // Indicates if the shape is still a squiggle
    public ArrayList<Point> tempPoints; // Temporary list of points defining the squiggle

    /**
     * Constructs a SmartShape with a starting point, paint properties,
     * and an initial path of points.
     *
     * @param point The starting point of the shape
     * @param pp Paint properties for the shape (e.g., fill color, stroke color)
     * @param path A list of points defining the squiggle path
     */
    public SmartShape(Point point, PaintProperties pp, ArrayList<Point> path) {
        super(point, pp, path);
        getProperties().setFilled(pp.isFilled());
        isSquiggle = true;
        tempPoints = new ArrayList<>();
    }


    /**
     * Gets the vector from point b to point a (a - b).
     *
     * @param a First point
     * @param b Second point
     * @return The vector representing the difference between points a and b
     */
    private Point getVector(Point a, Point b) {
        return new Point(a.getX() - b.getX(), a.getY() - b.getY());
    }

    /**
     * Calculates the Euclidean distance between two points.
     *
     * @param a First point
     * @param b Second point
     * @return The distance between points a and b
     */
    private double dist(Point a, Point b) {
        Point v = getVector(a,b);
        return Math.sqrt(v.getX() * v.getX() + v.getY() * v.getY());
    }

    /**
     * Calculates the Euclidean distance of a point from the origin (0,0).
     *
     * @param a The point to measure
     * @return The distance from the origin to point a
     */
    private double dist(Point a) {
        return dist(a, new Point(0,0));
    }


    /**
     * Calculates the relative angle between two vectors formed from a given origin point.
     *
     * @param a The first point defining the first vector
     * @param b The second point defining the second vector
     * @param origin The origin point to calculate the vectors from
     * @return The angle between the two vectors in radians
     */
    private double getRelativeAngle(Point a, Point b, Point origin) {
        Point aVec = getVector(a,origin);
        Point bVec = getVector(b,origin);
        double normedDotProduct = (aVec.getX() * bVec.getX() + aVec.getY() * bVec.getY())/(dist(aVec) * dist(bVec));
        return Math.acos(normedDotProduct);
    }

    /**
     * Calculates the slope of the line between two points.
     *
     * @param a First point
     * @param b Second point
     * @return The slope of the line defined by points a and b
     */
    private double getSlope(Point a, Point b) {
        return (a.getY() - b.getY()) / (a.getX() - b.getX());
    }

    /**
     * Sets the end point of the shape and adds it to the temporary points list.
     *
     * @param point The point to set as the end of the shape
     */
    @Override
    public void setEnd(Point point) {
        tempPoints.add(point);
        super.setEnd(point);
    }

    /**
     * Paints the shape on the given graphics context.
     * If the shape is still a squiggle, it delegates painting to the parent class.
     * Otherwise, it draws the finalized shape as a polygon.
     *
     * @param g2d The graphics context to draw the shape on
     */
    @Override
    public void paint(GraphicsContext g2d) {
        if(isSquiggle) {
            super.paint(g2d);
            return;
        }

        ArrayList<Point> points = this.getPoints();
        int numPoints = points.size();
        double[] xArr = new double[numPoints];
        double[] yArr = new double[numPoints];
        for(int i = 0; i < numPoints; i++) {
            xArr[i] = points.get(i).getX();
            yArr[i] = points.get(i).getY();
        }

        PaintProperties pp = getProperties();
        g2d.setStroke(pp.getStrokeColor());
        g2d.setLineWidth(pp.getStrokeThickness());

        g2d.strokePolygon(xArr, yArr, numPoints);

        if(pp.isFilled()) {
            g2d.setFill(pp.getFillColor());
            g2d.fillPolygon(xArr, yArr, numPoints);
        }
    }

    /**
     * Checks if a given point is inside the shape.
     * If the shape is a squiggle, the check is delegated to the parent class.
     * If the shape is finalized as a polygon, the method checks if the point is inside the polygon.
     *
     * @param p The point to check
     * @return True if the point is inside the shape, false otherwise
     */
    @Override
    public boolean includeCursor(Point p) {
        ArrayList<Point> points = this.getPoints();
        if(points.isEmpty()) return false;
        if(isSquiggle) return super.includeCursor(p);
        if(getProperties().isFilled()) {
            javafx.scene.shape.Polygon polygon = getFxPolygon();
            return polygon.contains(p.getX(), p.getY());
        }

        for(int i = 0; i < points.size(); i++) {
            Point a, b;
            if(i == 0) {
                a = points.getFirst();
                b = points.getLast();
            }
            else {
                a = points.get(i);
                b = points.get(i-1);
            }
            Point c = getVector(a,b);
            Point zero = new Point(0, 0);

            // normalize this vector
            c.setX(c.getX() / dist(c, zero));
            c.setY(c.getY() / dist(c, zero));

            Point normalVector = new Point(c.getY(), - c.getX());
            double border = getProperties().getStrokeThickness() / 2;
            double[] temp = {
                b.getX() + border * normalVector.getX() - border * c.getX(),
                b.getY() + border * normalVector.getY() - border * c.getY(),
                b.getX() - border * normalVector.getX() - border * c.getX(),
                b.getY() - border * normalVector.getY() - border * c.getY(),
                a.getX() - border * normalVector.getX() + border * c.getX(),
                a.getY() - border * normalVector.getY() + border * c.getY(),
                a.getX() + border * normalVector.getX() + border * c.getX(),
                a.getY() + border * normalVector.getY() + border * c.getY()
            };
            javafx.scene.shape.Polygon line = new javafx.scene.shape.Polygon(temp);
            if(line.contains(p.getX(), p.getY())) return true;
        }
        return false;
    }

    /**
     * Creates a JavaFX Polygon from the shape's points to use in contain checks.
     *
     * @return A JavaFX Polygon representing the shape
     */
    private javafx.scene.shape.Polygon getFxPolygon() {
        double[] vertices = new double[this.getPoints().size() * 2];
        for(int i = 0; i < this.getPoints().size(); i++) {
            vertices[2*i] = this.getPoints().get(i).getX();
            vertices[2*i+1] = this.getPoints().get(i).getY();
        }
        return new javafx.scene.shape.Polygon(vertices);
    }

    /**
     * Finalizes the squiggle into a closed shape by interpolating between points
     * and forming a polygon if the first and last points are close enough.
     * <p>
     * Iterates through the points and selects vertices based on angle and distance
     * to ensure the shape is smooth and forms a closed loop.
     * </p>
     * The algorithm works as follows:
     * - Iterate through the points, keeping track of the vertices that should
     *   form the final shape.
     * - For each point in the squiggle, attempt to find
     *   the best fitting vertex that minimizes the angle between the current
     *   vertex, the current point, and any other point further down the path.
     * - A vertex is considered valid if it is far enough from its neighbours,
     *   and the angle between the points is small enough to be considered part
     *   of the shape's smooth curve.
     * - Once the best vertex is determined, it is added to the final shape's
     *   list of vertices, and the search continues from this new vertex.
     * - After processing all points, check if the shape can be closed by
     *   comparing the angle between the first and last vertex.
     *   If they form
     *   a small enough angle, they are connected, completing the polygon.
     * - If the shape is successfully closed, the squiggle is transformed into
     *   a polygon; otherwise, it remains a squiggle.
     */
    @Override
    public void finalizeShape() {
        if(tempPoints.isEmpty()) return;
        if(dist(tempPoints.getFirst(), tempPoints.getLast()) > CLOSE_CONDITION) return;
        ArrayList<Point> vertices = new ArrayList<>();
        int currVertexIndex = 0;
        Point currVertex = tempPoints.get(currVertexIndex);
        for (int i = 0; i < tempPoints.size(); i++) {
            if(i == 0) continue;

            int extremePointIndex = currVertexIndex;
            Point extremePoint = tempPoints.get(currVertexIndex);
            double minAngle = Integer.MAX_VALUE;
            for(int j = currVertexIndex + 1; j < i; j++) {
                double currAngle = getRelativeAngle(currVertex, tempPoints.get(i), tempPoints.get(j));
                if(currAngle < minAngle &&
                   dist(tempPoints.get(i), tempPoints.get(j)) > VERTEX_DISTANCE &&
                   dist(tempPoints.get(j), currVertex) > VERTEX_DISTANCE) {
                    extremePointIndex = j;
                    extremePoint = tempPoints.get(j);
                    minAngle = currAngle;
                }
            }

            if(minAngle < SLOPE_LENIENCE) {
                System.out.println(i);
                vertices.add(extremePoint);
                currVertexIndex = extremePointIndex;
                currVertex = extremePoint;
            }
        }

        if(getRelativeAngle(vertices.getFirst(), vertices.getLast(), tempPoints.getFirst()) < SLOPE_LENIENCE)
            vertices.add(tempPoints.getFirst());

        this.getPoints().clear();
        for(Point p : vertices) {
            super.setEnd(p);
        }

        isSquiggle = false;
    }
}
