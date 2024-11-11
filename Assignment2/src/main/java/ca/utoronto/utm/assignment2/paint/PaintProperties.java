package ca.utoronto.utm.assignment2.paint;

import javafx.scene.paint.Color;

/**
 * This class represents the properties of a shape, providing the model for attributes
 * such as fill color, border color, border width, and stroke thickness.
 * <p>
 * Instances of this class are used to define the visual appearance of shapes in the paint application,
 * including whether the shape is filled, its fill color, stroke color, stroke thickness, and the number of vertices
 * (used for shapes like polygons).
 * </p>
 * @author tianji61
 */
public class PaintProperties {
    private boolean filled;
    private Color fillColor;
    private Color strokeColor;
    private double strokeThickness;
    private int vertices;

    /**
     * Constructor to initialize the paint properties for a shape.
     *
     * @param filled          Indicates whether the shape is filled.
     * @param fillColor      The color used to fill the shape.
     * @param strokeColor    The color of the shape's border.
     * @param strokeThickness The thickness of the shape's border.
     * @param vertices       The number of vertices for polygon shapes.
     */
    public PaintProperties(Boolean filled, Color fillColor, Color strokeColor, double strokeThickness, int vertices) {
        this.filled = filled;
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeThickness = strokeThickness;
        this.vertices = vertices;
    }

    /**
     * Returns whether the shape is filled.
     *
     * @return true if the shape is filled, false otherwise.
     */
    public boolean isFilled() {
        return filled;
    }

    /**
     * Sets whether the shape is filled.
     *
     * @param filled true to fill the shape, false to leave it unfilled.
     */
    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    /**
     * Returns the fill color of the shape.
     *
     * @return The fill color.
     */
    public Color getFillColor() {
        return fillColor;
    }

    /**
     * Sets the fill color of the shape.
     *
     * @param fillColor The fill color to apply to the shape.
     */
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    /**
     * Returns the stroke color (border color) of the shape.
     *
     * @return The stroke color.
     */
    public Color getStrokeColor() {
        return strokeColor;
    }

    /**
     * Sets the stroke color (border color) of the shape.
     *
     * @param strokeColor The stroke color to apply to the shape.
     */
    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    /**
     * Returns the stroke thickness (border width) of the shape.
     *
     * @return The stroke thickness.
     */
    public double getStrokeThickness() {
        return strokeThickness;
    }

    /**
     * Sets the stroke thickness (border width) of the shape.
     *
     * @param strokeThickness The thickness of the shape's border.
     */
    public void setStrokeThickness(double strokeThickness) {
        this.strokeThickness = strokeThickness;
    }

    /**
     * Returns the number of vertices for polygon shapes.
     *
     * @return The number of vertices.
     */
    public int getVertices() {
        return vertices;
    }

    /**
     * Sets the number of vertices for polygon shapes.
     *
     * @param vertices The number of vertices to set.
     */
    public void setVertices(int vertices) {
        this.vertices = vertices;
    }

    /**
     * Returns a string representation of the paint properties, including whether the shape is filled,
     * the fill color, stroke color, stroke thickness, and number of vertices.
     *
     * @return A string describing the paint properties of the shape.
     */
    @Override
    public String toString() {
        return filled + "," + fillColor + "," + strokeColor + "," + strokeThickness + "," + vertices;
    }
}
