package ca.utoronto.utm.assignment2.paint;

import ca.utoronto.utm.assignment2.paint.controlPanels.PropertiesPanel;
import javafx.scene.layout.Pane;

import java.awt.*;

public class ModeStrategyFactory {
    public static ModeStrategy getModeStrategy(String mode, PaintModel model, PropertiesPanel pp, Pane canvasPane) {
        switch (mode) {
            case "Select" -> {
                return new SelectModeStrategy(model, pp);
            }
            case "ObjectEraser" -> {
                return new ObjectEraserStrategy(model);
            }
            case "Polyline" -> {
                // Make a new polyline shape first if it doesn't yet exist.
                if (model.getCurrentShape() == null) return new DrawModeStrategy(model, mode, pp);

                return new PolylineModeStrategy(model, mode, pp);
            }
            case "text" -> {
                return new TextModeStrategy(model, mode, pp, canvasPane);
            }
            default -> {
                return new DrawModeStrategy(model, mode, pp);
            }
        }
    }
}
