// Clipboard.java
package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;
import java.util.List;

public class Clipboard {
    private static final List<Shape> clipboard = new ArrayList<>();

    // Add a shape to the clipboard
    public static void add(Shape shape) {
        clipboard.clear();
        clipboard.add(shape);
        System.out.println("Shape added to clipboard.");
    }

    // Retrieve the shape from the clipboard
    public static Shape get() {
        if (!clipboard.isEmpty()) {
            return clipboard.get(0);
        }
        return null;
    }

    public static void clear() {
        clipboard.clear();
    }
}
