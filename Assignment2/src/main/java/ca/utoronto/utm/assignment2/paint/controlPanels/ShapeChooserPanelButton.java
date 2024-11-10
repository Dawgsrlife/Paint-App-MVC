package ca.utoronto.utm.assignment2.paint.controlPanels;

import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Region;

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

    public String getCommand() {
        return command;
    }
}
