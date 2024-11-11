package ca.utoronto.utm.assignment2.paint;

import ca.utoronto.utm.assignment2.paint.controlPanels.PropertiesPanel;
import ca.utoronto.utm.assignment2.paint.controlPanels.ShapeChooserPanel;
import ca.utoronto.utm.assignment2.paint.shapes.Point;
import ca.utoronto.utm.assignment2.paint.shapes.Text;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * Controller class that handles user input and interaction with the paint canvas.
 * <p>
 * The controller listens for mouse events and updates the model, view, and mode strategy accordingly.
 * It serves as the intermediary between the view (the canvas) and the underlying data model,
 * allowing the user to interact with the shapes and manage their creation, modification, or deletion.
 * </p>
 *
 * @see PaintModel
 * @see ShapeChooserPanel
 * @see PropertiesPanel
 * @see ModeStrategy
 * @author tianji61 / mengale1
 */
public class PaintController implements EventHandler<MouseEvent> {
    private final PaintModel model;
    private final ShapeChooserPanel scp;
    private final PropertiesPanel pp;
    private final Pane canvasPane;

    private ModeStrategy modeStrategy;

    /**
     * Constructs a new PaintController.
     * <p>
     * This constructor initializes the controller with the given model, shape chooser panel,
     * properties panel, and canvas pane. It also sets the default mode strategy based on the
     * selected mode in the shape chooser panel.
     * </p>
     *
     * @param model The paint model that stores the shapes and data.
     * @param scp The panel for selecting shapes.
     * @param pp The panel for adjusting properties of shapes.
     * @param canvasPane The canvas where the shapes are drawn.
     */
    public PaintController(PaintModel model, ShapeChooserPanel scp, PropertiesPanel pp, Pane canvasPane) {
        this.model = model;
        this.scp = scp;
        this.pp = pp;
        this.canvasPane = canvasPane;

        // Default drawing mode
        setModeStrategy(scp.getMode());
    }

    /**
     * Sets the current mode strategy based on the selected drawing mode.
     * <p>
     * This method updates the strategy used for handling mouse events based on the selected mode
     * (e.g., drawing, erasing, selecting, etc.).
     * </p>
     *
     * @param mode The mode selected by the user, such as "Select" or "Polyline".
     */
    public void setModeStrategy(String mode) {
        modeStrategy = ModeStrategyFactory.getModeStrategy(mode, model, pp, canvasPane);
    }

    /**
     * Handles mouse events that occur on the canvas.
     * <p>
     * This method processes different types of mouse events (e.g., mouse press, move, drag, release)
     * and delegates the appropriate actions to the current mode strategy.
     * </p>
     *
     * @param mouseEvent The mouse event to handle.
     */
    @Override
    public void handle(MouseEvent mouseEvent) {
        // Grab current point and set coords
        Point point = new Point(mouseEvent.getX(), mouseEvent.getY());
        if (! mouseEvent.getSource().equals(pp)) pp.setMouseCoords(point);

        switch (mouseEvent.getEventType().toString()) {
            case "MOUSE_PRESSED" -> {
                // Update the mode strategy only on mouse press
                setModeStrategy(scp.getMode());
                modeStrategy.onMousePressed(point, mouseEvent.isPrimaryButtonDown(), mouseEvent.isSecondaryButtonDown());
            }
            case "MOUSE_MOVED" -> modeStrategy.onMouseMoved(point);
            case "MOUSE_DRAGGED" -> modeStrategy.onMouseDragged(point);
            case "MOUSE_RELEASED" -> modeStrategy.onMouseReleased(point);
            default -> {
                // If there's a new event type, you can add more cases or handle it
            }
        }
        model.setLastPoint(point);
        model.update();
    }
}
