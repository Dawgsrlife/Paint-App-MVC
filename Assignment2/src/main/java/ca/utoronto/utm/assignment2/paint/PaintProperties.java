package ca.utoronto.utm.assignment2.paint;

import javafx.scene.paint.Color;

/**
 * This class represents the properties of a shape, it is the model to characterize fill
 * color, border color, border width, and stroke size.
 */
public class PaintProperties {
    private boolean filled;
    private Color fillColor;
    private Color strokeColor;
    private double strokeThickness;

    public PaintProperties(Boolean filled, Color fillColor, Color strokeColor, double strokeThickness) {
        this.filled = filled;
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeThickness = strokeThickness;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public double getStrokeThickness() {
        return strokeThickness;
    }

    public void setStrokeThickness(double strokeThickness) {
        this.strokeThickness = strokeThickness;
    }

    @Override
    public String toString() {
        return filled + "," + fillColor + "," + strokeColor + "," + strokeThickness;
    }
}
