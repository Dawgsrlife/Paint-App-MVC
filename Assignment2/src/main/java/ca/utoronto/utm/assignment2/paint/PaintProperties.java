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
    private final double strokeSize;

    public PaintProperties(Color fillColor, Color borderColor, double borderWidth, double strokeSize) {
        this.fillColor = fillColor;
        this.borderColor = borderColor;
        this.borderWidth = borderWidth;
        this.strokeSize = strokeSize;
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

    public double getStrokeSize() {
        return strokeSize;
    }
}
