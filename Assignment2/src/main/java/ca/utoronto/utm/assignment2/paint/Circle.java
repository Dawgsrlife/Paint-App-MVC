package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Objects;

/**
 * A class representing a shape on the canvas
 *
 * @author tianji61 / Starter Code
 */
public class Circle extends Oval {
    private double radius;

    public Circle(Point centre, int radius, PaintProperties pp) {
        super(centre, null, pp);
        this.setType("Circle");
        this.radius = radius;
    }

    @Override
    public double[] getPaintInfo() {
        double radius = getRadius();
        double x = getStart().x - radius;
        double y = getStart().y - radius;
        return new double[]{x, y, 2 * radius, 2 * radius};
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
