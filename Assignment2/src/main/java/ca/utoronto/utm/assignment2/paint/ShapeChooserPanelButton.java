package ca.utoronto.utm.assignment2.paint;

import javafx.scene.control.Button;

public class ShapeChooserPanelButton extends Button {
    // button properties
    private final String command;

    // css properties for button highlighting
    private static final String SELECTED = "-fx-background-color: rgba(255, 255, 0, 0.2);" +
            "-fx-border-color: rgba(0, 0, 0, 0.2);" +
            "-fx-border-radius: 3px;";

    private static final String UNSELECTED = "-fx-background-color: rgba(225, 225, 225, 1);" +
            "-fx-border-color: rgba(0, 0, 0, 0.2);" +
            "-fx-border-radius: 3px;";

    public ShapeChooserPanelButton(String label, String name) {
        super(label);
        this.command = name;
        this.setMinWidth(150);
        this.setStyle(UNSELECTED);
    }

    public void setSelected(boolean selected) {
        setStyle(selected ? SELECTED : UNSELECTED);
    }

    public String getCommand() {
        return command;
    }
}
