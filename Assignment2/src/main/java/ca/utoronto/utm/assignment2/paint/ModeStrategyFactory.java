package ca.utoronto.utm.assignment2.paint;

import ca.utoronto.utm.assignment2.paint.controlPanels.PropertiesPanel;
import javafx.scene.layout.Pane;

/**
 * Factory class for creating instances of different {@link ModeStrategy} implementations.
 * <p>
 * This class provides a static method to obtain the appropriate strategy for a given mode in the painting application.
 * The strategies determine how the application responds to mouse events based on the current mode, such as drawing, selecting, or erasing objects.
 * </p>
 *
 * @see ModeStrategy
 * @see SelectModeStrategy
 * @see ObjectEraserStrategy
 * @see PolylineModeStrategy
 * @see TextModeStrategy
 * @see DrawModeStrategy
 * @author huaethan
 */
public class ModeStrategyFactory {

    /**
     * Returns an instance of the {@link ModeStrategy} based on the given mode.
     * <p>
     * The method selects the correct strategy based on the current mode (e.g., "Select", "Polyline", "ObjectEraser", "text").
     * It initializes the strategy with the necessary parameters, including the current paint model, properties panel, and canvas pane.
     * </p>
     *
     * @param mode The current mode of interaction, determining which strategy to use (e.g., "Select", "Polyline").
     * @param model The paint model that holds the current state of the drawing.
     * @param pp The properties panel that manages paint properties like colors and brush settings.
     * @param canvasPane The canvas pane where shapes are drawn, used for text mode.
     * @return A {@link ModeStrategy} instance that handles the specified mode.
     */
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
                if (model.getCurrentShape() == null) return new PolylineModeStrategy(model, mode, pp);

                return new PolylineModeStrategy(model, mode, pp);
            }
            case "Text" -> {
                return new TextModeStrategy(model, mode, pp, canvasPane);
            }
            default -> {
                return new DrawModeStrategy(model, mode, pp);
            }
        }
    }
}
