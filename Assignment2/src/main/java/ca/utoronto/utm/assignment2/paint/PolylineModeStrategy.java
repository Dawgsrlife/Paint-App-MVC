package ca.utoronto.utm.assignment2.paint;

import ca.utoronto.utm.assignment2.paint.controlPanels.PropertiesPanel;
import ca.utoronto.utm.assignment2.paint.shapes.Point;
import ca.utoronto.utm.assignment2.paint.shapes.Shape;
import ca.utoronto.utm.assignment2.paint.shapes.Polyline;

public class PolylineModeStrategy extends DrawModeStrategy implements ModeStrategy {
    Polyline polyline;

    public PolylineModeStrategy(PaintModel model, String mode, PropertiesPanel pp) {
        super(model, mode, pp);
        polyline = (Polyline)model.getCurrentShape();
    }

    @Override
    public void onMouseMoved(Point point) {
        // No-op
        // Maybe add a Polyline trail to here later!
    }

    @Override
    public void onMousePressed(Point point, boolean isPrimaryButton, boolean isSecondaryButton) {
        // Disable the ability to do anything by pressing both buttons
        if (isPrimaryButton && isSecondaryButton) return;

        if (polyline == null || !polyline.hasStarted()) {
            super.onMousePressed(point, isPrimaryButton, isSecondaryButton);
            polyline = (Polyline)model.getCurrentShape();
            polyline.setStarted(true);
        } else if (polyline.hasStarted()) {
            if (isPrimaryButton) {
                polyline.setEnd(point);
                model.addTempShape(polyline);
            } else if (isSecondaryButton) {
                polyline.finalizeShape();
                finalizeShape();  // from superclass
                polyline.setFinalized(true);  // After ending a Polyline, mark it finalized
            }
        }
    }

    @Override
    public void onMouseDragged(Point point) {
        // No-op
    }

    @Override
    public void onMouseReleased(Point point) {
        // No-op
    }
}
