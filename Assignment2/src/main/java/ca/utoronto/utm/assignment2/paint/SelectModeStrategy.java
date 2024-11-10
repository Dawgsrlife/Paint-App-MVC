package ca.utoronto.utm.assignment2.paint;

import ca.utoronto.utm.assignment2.paint.controlPanels.PropertiesPanel;

public class SelectModeStrategy implements ModeStrategy {
    private final PaintModel model;
    private final PropertiesPanel properties;

    public SelectModeStrategy(PaintModel model, PropertiesPanel properties) {
        this.model = model;
        this.properties = properties;
    }

    @Override
    public void onMousePressed(Point point, boolean isPrimaryButton, boolean isSecondaryButton) {
        // Only handle for left-clicks (or primary button usage)
        // Thus, exit if the primary button isn't used
        if (!isPrimaryButton) return;

        model.setCurrentShape(model.getSelected(point));
        if (model.getCurrentShape() != null) properties.loadPaintProperties(model.getCurrentShape());
    }

    @Override
    public void onMouseDragged(Point point) {
        Shape shape = model.getCurrentShape();
        Point lastPoint = model.getLastPoint();

        shape.getStart().setX(shape.getStart().getX() + point.x - lastPoint.x);
        shape.getStart().setY(shape.getStart().getY() + point.y - lastPoint.y);
        shape.getEnd().setX(shape.getEnd().getX() + point.x - lastPoint.x);
        shape.getEnd().setY(shape.getEnd().getY() + point.y - lastPoint.y);

        model.update();
    }

    @Override
    public void onMouseReleased(Point point) {
        // No-op
    }
}
