package ca.utoronto.utm.assignment2.paint;

import ca.utoronto.utm.assignment2.paint.controlPanels.PropertiesPanel;

public class PolylineModeStrategy extends DrawModeStrategy implements ModeStrategy {

    public PolylineModeStrategy(PaintModel model, String mode, PropertiesPanel pp) {
        super(model, mode, pp);
    }

    @Override
    public void onMousePressed(Point point, boolean isPrimaryButton, boolean isSecondaryButton) {
        if (true) {  //TODO:temp
            // Disable the ability to do anything by pressing both buttons
            if (isPrimaryButton && isSecondaryButton) return;

            if (isPrimaryButton) {
                Shape shape = model.getCurrentShape();

                shape.setEnd(point);
                model.addTempShape(shape);
            } else if (isSecondaryButton) {
                model.getCurrentShape().finalizeShape();
                finalizeShape();  // from superclass
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
