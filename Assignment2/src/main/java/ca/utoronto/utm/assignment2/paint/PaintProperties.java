package ca.utoronto.utm.assignment2.paint;

import javafx.scene.paint.Color;

/**
 * This class represents the properties of a shape, it is the model to characterize fill
 * color, border color and border width.
 */
public class PaintProperties {
    private final Color fillColor;
    private final Color borderColor;
    private final double borderWidth;
    public PaintProperties(Color fillColor, Color borderColor, double borderLength) {
        this.fillColor = fillColor;
        this.borderColor = borderColor;
        this.borderWidth = borderLength;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public double getBorderWidth() {
        return borderWidth;
    }
}
