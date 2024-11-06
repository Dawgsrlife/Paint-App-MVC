package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class represents a shape instance on canvas,
 * which is defined to be some shape has:
 * a start point
 * an end point
 * and printable when g2d is passed into getPrint()
 *
 * @author tianji61
 */
public abstract class Shape {

    // default fields
    private Point start;
    private Point end;
    private String type;
    private boolean filled;
    private Color color;
    private Color borderColor;
    private double borderWidth;

    public Shape(Point start, Point end, String type, boolean filled, Color color, Color borderColor, double borderWidth) {
        this.start = start;
        this.end = end;
        this.type = type;
        this.filled = filled;
        this.color = color;
        this.borderColor = borderColor;
        this.borderWidth = borderWidth;
    }

    abstract void paint(GraphicsContext g2d);

    protected abstract void fill(GraphicsContext g2d);

    protected abstract double[] getPaintInfo();

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public double getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(double borderWidth) {
        this.borderWidth = borderWidth;
    }

    @Override
    public String toString() {
        return start + "," + end + "," + type + "," + filled + "," + color + "," + borderColor + "," + borderWidth;
    }
}
