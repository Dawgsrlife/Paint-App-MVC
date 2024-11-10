package ca.utoronto.utm.assignment2.paint.commandMenuBar;

import ca.utoronto.utm.assignment2.paint.*;
import ca.utoronto.utm.assignment2.paint.controlPanels.PropertiesPanel;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;


public class CommandCut extends MenuItem implements Command {
    private PropertiesPanel propertiesPanel; // Reference to PropertiesPanel to get the selected shape
    private PaintModel model;                // Reference to PaintModel for removing shapes
    private Scene scene;
    private PaintController controller;

    public CommandCut(PropertiesPanel propertiesPanel, PaintModel model, Scene scene, PaintController controller) {
        super("cut");
        this.propertiesPanel = propertiesPanel;
        this.model = model;
        this.scene = scene;
        this.controller = controller;
    }


    @Override
    public void execute(PaintModel model, Scene scene) {
        // Ensure all dependencies are available
        if (propertiesPanel == null || model == null || controller == null) {
            System.out.println("Error: Missing necessary component for cut operation.");
            return;
        }

        // Get the currently selected shape
        Shape selectedShape = propertiesPanel.getCurrentlyLoadedShape("cut");

        if (selectedShape != null) {
            controller.removeShape(selectedShape);
            propertiesPanel.clearCLS();
            scene.getRoot().requestLayout();
            System.out.println("Cut operation completed.");

        } else {
            System.out.println("No shape selected to cut.");
        }
    }
}
