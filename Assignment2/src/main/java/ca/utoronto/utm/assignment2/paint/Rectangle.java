package ca.utoronto.utm.assignment2.paint;

import java.util.Objects;

/**
 * A class representing a rectangle on the canvas
 * @author tianji61
 */
public class Rectangle {

    // two corner coordinates for Rectangle indexing and definition
    private Point start;
    private Point end;

    /**
     * Initialize a Rectangle with two given coordinates
     * @param start starting coordinate
     * @param end ending coordinate
     */
    public Rectangle(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Objects.equals(start, rectangle.start) && Objects.equals(end, rectangle.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
