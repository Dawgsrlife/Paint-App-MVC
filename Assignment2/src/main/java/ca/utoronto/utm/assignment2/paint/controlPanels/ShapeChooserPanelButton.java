package ca.utoronto.utm.assignment2.paint.controlPanels;

import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Region;

/**
 * ShapeChooserPanelButton is a custom ToggleButton used in a painting application's shape chooser panel.
 * Each button represents a different drawable shape and displays an icon generated from an SVG path.
 * The button maintains a command string that identifies the associated shape or tool (e.g., "Circle", "Rectangle").
 *
 * This class extends ToggleButton, allowing each button to have an on/off state, which is useful for
 * selecting a single shape at a time in the ShapeChooserPanel.
 *
 * Dependencies:
 * - Region: Used to create a visual icon based on the provided SVG path.
 *
 * @see ToggleButton
 * @see Region
 *
 * Author: tianji61
 */
public class ShapeChooserPanelButton extends ToggleButton {
    // button properties
    private final String command;

    public ShapeChooserPanelButton(String svg, String name) {
        super(name);
        this.command = name;
        this.getStyleClass().add("button");
        setPickOnBounds(true);

        Region icon = new Region();
        icon.getStyleClass().add("icon");
        icon.setStyle("-fx-shape: \"" + svg + "\"");
        setGraphic(icon);
    }

    /**
     * Returns the command associated with this button.
     *
     * @return A string representing the shape or tool command (e.g., "Circle", "Rectangle").
     */
    public String getCommand() {
        return command;
    }
}
