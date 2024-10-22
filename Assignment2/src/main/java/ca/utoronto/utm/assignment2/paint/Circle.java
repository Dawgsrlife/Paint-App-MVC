package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Objects;

/**
 * A class representing a shape on the canvas
 *
 * @author tianji61
 */
public class Circle extends Shape {
    private double radius;

    public Circle(Point centre, int radius) {
        super(centre, null, "Circle", false,
                Color.LIGHTGREEN, null, 5.0);
        this.radius = radius;
    }

    @Override
    public void paint(GraphicsContext g2d) {
        g2d.setFill(getColor());
        double[] info = getPaintInfo();
        g2d.fillOval(info[0], info[1], info[2], info[2]);
        if (!isFilled()) {
            removeFilled(g2d);
        }
    }

    @Override
    public void removeFilled(GraphicsContext g2d) {
        g2d.setFill(PaintPanel.backgroundColor);
        double[] info = getPaintInfo();
        double width = getBorderWidth();
        g2d.fillOval(info[0] + width, info[1] + width,
                info[2] - width * 2, info[2] - width * 2);
    }

    @Override
    public double[] getPaintInfo() {
        double radius = getRadius();
        double x = getStart().x - radius / 2;
        double y = getStart().y - radius / 2;
        return new double[]{x, y, radius};
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
