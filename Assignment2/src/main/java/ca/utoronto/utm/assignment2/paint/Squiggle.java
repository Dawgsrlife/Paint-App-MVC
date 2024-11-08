package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polyline;

import java.util.ArrayList;

/**
 * This class represents a squiggle on the canvas, which is
 * an ordered set of points connected to each by a line.
 * The points are defined on every left click drag of the mouse
 * or release of the left mouse button
 */
public class Squiggle extends Shape {
    private final ArrayList<Point> points = new ArrayList<>();
    private final double strokeSize;

    public Squiggle(Point point, PaintProperties pp, ArrayList<Point> path) {
        super(point, point, "Squiggle", pp);
        points.add(point);
        if (path != null) this.points.addAll(path);
        strokeSize = pp.getStrokeThickness();
    }

    @Override
    public void paint(GraphicsContext g2d) {
        g2d.setStroke(getProperties().getStrokeColor());
        g2d.setLineWidth(strokeSize);
        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);
            g2d.strokeLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

    @Override
    public void fill(GraphicsContext g2d) {

    }

    @Override
    public double[] getPaintInfo() {
        return new double[0];
    }

    @Override
    boolean includeCursor(Point p) {
        for (Point point : points) {
            Ellipse strokePoint = new Ellipse(point.x, point.y, getProperties().getStrokeThickness(), getProperties().getStrokeThickness());
            if (strokePoint.contains(p.x, p.y)) {
                System.out.println("in");
                return true;
            }
        }
        return false;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    @Override
    public void setEnd(Point end) {
        points.add(end);
        super.setEnd(end);
    }

    @Override
    public String toString() {
        StringBuilder path = new StringBuilder();
        for (Point p : getPoints()) {
            if (!p.equals(getStart())) path.append(",").append(p);
        }
        return super.toString() + path;
    }
}
