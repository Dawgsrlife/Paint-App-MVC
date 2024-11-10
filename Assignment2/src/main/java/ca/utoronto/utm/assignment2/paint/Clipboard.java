package ca.utoronto.utm.assignment2.paint;

public class Clipboard {
    private Shape shape;

    public void add(Shape copiedShape) {
        this.shape = copiedShape;
    }

    public Shape getShape() {
        return shape;
    }
}