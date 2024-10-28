package ca.utoronto.utm.assignment2.paint.controlPanels;

import javafx.scene.control.Button;

public class ShapeChooserPanelButton extends Button {
    // button properties
    private final String command;
    private boolean selected;

    // css properties for button highlighting
    private static final String SELECTED = "-fx-background-color: rgba(255, 255, 0, 0.2);" +
            "-fx-border-color: rgba(0, 0, 0, 0.2);" +
            "-fx-border-radius: 3px;";

    private static final String UNSELECTED = "-fx-background-color: rgba(225, 225, 225, 1);" +
            "-fx-border-color: rgba(0, 0, 0, 0.2);" +
            "-fx-border-radius: 3px;";

    public ShapeChooserPanelButton(String label, String name, boolean selected) {
        super(label);
        this.command = name;
        this.selected = selected;
        this.setMinWidth(150);
        this.setStyle(UNSELECTED);
    }

    public void setSelected(boolean selected) {
        setStyle(selected ? SELECTED : UNSELECTED);
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public String getCommand() {
        return command;
    }
}
