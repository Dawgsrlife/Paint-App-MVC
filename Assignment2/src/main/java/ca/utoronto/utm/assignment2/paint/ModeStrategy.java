package ca.utoronto.utm.assignment2.paint;

public interface ModeStrategy {
    void onMousePressed(Point point, boolean isPrimaryButton, boolean isSecondaryButton);
    void onMouseDragged(Point point);
    void onMouseReleased(Point point);
}
