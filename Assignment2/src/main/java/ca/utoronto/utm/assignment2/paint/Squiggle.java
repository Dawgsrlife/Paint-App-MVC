package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Squiggle extends Shape {
    private final ArrayList<Point> points = new ArrayList<>();

    public Squiggle(Point point) {
        super(point, null, "Squiggle", true,
                Color.BLACK, null, 0.0);
    }

    public void addPoint(Point point) {
        points.add(point);
        setEnd(point);
    }

    @Override
    void paint(GraphicsContext g2d) {
        g2d.setStroke(getColor());
        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);
            g2d.strokeLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

    @Override
    void removeFilled(GraphicsContext g2d) {

    }

    @Override
    double[] getPaintInfo() {
        return new double[0];
    }
}
