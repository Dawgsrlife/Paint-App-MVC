package ca.utoronto.utm.assignment2.paint;

import ca.utoronto.utm.assignment2.paint.shapes.Point;

public interface ModeStrategy {
    void onMouseMoved(Point point);
    void onMousePressed(Point point, boolean isPrimaryButton, boolean isSecondaryButton);
    void onMouseDragged(Point point);
    void onMouseReleased(Point point);
}
