package ca.utoronto.utm.assignment2.paint;

import ca.utoronto.utm.assignment2.paint.controlPanels.PropertiesPanel;

public class PolylineModeStrategy extends DrawModeStrategy implements ModeStrategy {

    public PolylineModeStrategy(PaintModel model, String mode, PropertiesPanel pp) {
        super(model, mode, pp);
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

        Shape shape = model.getCurrentShape();

        // TODO: handle polyline creation
        if (shape == null) {
            System.out.println("Make polyline work! Not yet working with this Strategy.");
            return;
        }

        if (isPrimaryButton) {
            shape.setEnd(point);
            model.addTempShape(shape);
        } else if (isSecondaryButton) {
            shape.finalizeShape();
            finalizeShape();  // from superclass
            shape.setFinalized(true);  // After ending a Polyline, mark it finalized
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
