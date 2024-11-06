package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class Squiggle extends Shape {
    private final ArrayList<Point> points = new ArrayList<>();
    private final double strokeSize;

    public Squiggle(Point point, PaintProperties pp, ArrayList<Point> path) {
        super(point, point, "Squiggle", true,
                pp.getFillColor(), pp.getBorderColor(), 0.0);
        points.add(point);
        if (path != null) this.points.addAll(path);
        strokeSize = pp.getStrokeThickness();
    }

    @Override
    public void paint(GraphicsContext g2d) {
        g2d.setStroke(getColor());
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
