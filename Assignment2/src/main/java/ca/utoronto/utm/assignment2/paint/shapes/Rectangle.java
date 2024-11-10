package ca.utoronto.utm.assignment2.paint.shapes;

import ca.utoronto.utm.assignment2.paint.PaintProperties;
import javafx.scene.canvas.GraphicsContext;

/**
 * A class representing a rectangle on the canvas
 *
 * @author tianji61
 */
public class Rectangle extends Shape {

    /**
     * Initialize a Rectangle with two given coordinates
     *
     * @param start starting coordinate
     * @param end   ending coordinate
     */
    public Rectangle(Point start, Point end, PaintProperties pp) {
        super(start, end, "Rectangle", pp);
    }

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

    @Override
    protected void fill(GraphicsContext g2d) {
        g2d.setFill(getProperties().getFillColor());
        double[] info = getPaintInfo();
        double width = getProperties().getStrokeThickness() / 2;
        g2d.fillRect(info[0] + width, info[1] + width,
                info[2] - width * 2, info[3] - width * 2);
    }

    @Override
    protected double[] getPaintInfo() {
        double startX = Math.min(getStart().getX(), getEnd().getX());
        double startY = Math.min(getStart().getY(), getEnd().getY());
        double width = Math.abs(getEnd().getX() - getStart().getX());
        double height = Math.abs(getEnd().getY() - getStart().getY());
        return new double[]{startX, startY, width, height};
    }

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

    @Override
    public void move(double dx, double dy) {
        getStart().setX(getStart().getX() + dx);
        getStart().setY(getStart().getY() + dy);
        getEnd().setX(getEnd().getX() + dx);
        getEnd().setY(getEnd().getY() + dy);
    }
}
