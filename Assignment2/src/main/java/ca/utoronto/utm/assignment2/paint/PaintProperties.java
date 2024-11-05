package ca.utoronto.utm.assignment2.paint;

import javafx.scene.paint.Color;

/**
 * This class represents the properties of a shape, it is the model to characterize fill
 * color, border color, border width, and stroke size.
 */
public class PaintProperties {
    private final boolean filled;
    private final Color fillColor;
    private final Color borderColor;
    private final double strokeThickness;

    public PaintProperties(Boolean filled, Color fillColor, Color borderColor, double strokeThickness) {
        this.filled = filled;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
        this.strokeThickness = strokeThickness;
    }

    public boolean isFilled() {
        return filled;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public double getStrokeThickness() {
        return strokeThickness;
    }
}
