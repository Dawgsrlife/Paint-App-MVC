package ca.utoronto.utm.assignment2.paint;


import ca.utoronto.utm.assignment2.paint.controlPanels.PropertiesPanel;
import ca.utoronto.utm.assignment2.paint.controlPanels.ShapeChooserPanel;
import ca.utoronto.utm.assignment2.paint.commandMenuBar.CommandMenuBar;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Main application class for the Paint application.
 * <p>
 * This class sets up the main application window, initializing the model, view, and controller components.
 * It also configures the UI layout with a command menu bar, shape chooser, properties panel, and canvas pane.
 * Additionally, it listens for window size changes to dynamically adjust the canvas size.
 * </p>
 *
 * @see PaintModel
 * @see PaintView
 * @see PaintController
 * @see CommandMenuBar
 * @see ShapeChooserPanel
 * @see PropertiesPanel
 * @author tianji61 / mengale1 / huaethan / chen2046
 */
public class Paint extends Application {
    PaintModel model; // Model
    PaintView view; // View
    PaintController controller; // Controller
    CommandMenuBar menuBar; // Control
    ShapeChooserPanel shapeChooserPanel; // Control
    PropertiesPanel propertiesPanel; // Control
    // commandManager;

    /**
     * The main entry point for launching the Paint application.
     * <p>
     * Calls the launch() method to start the JavaFX application.
     * </p>
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes the application window, UI components, and layout.
     * <p>
     * This method sets up the main window with a border pane layout. It initializes the model, view, and controller,
     * and adds the UI elements (such as the menu bar, shape chooser panel, properties panel, and canvas pane) to the window.
     * Additionally, it adds listeners to resize the canvas when the window is resized.
     * </p>
     *
     * @param stage The primary stage for this application.
     * @throws Exception If an error occurs during the initialization.
     */
    @Override
    public void start(Stage stage) throws Exception {

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root);
        Pane canvasPane = new Pane();

        this.model = new PaintModel();

        shapeChooserPanel = new ShapeChooserPanel();
        propertiesPanel = new PropertiesPanel();
        this.controller = new PaintController(model, shapeChooserPanel, propertiesPanel, canvasPane);
        this.view = new PaintView(model, controller, canvasPane);
        menuBar = new CommandMenuBar(model, scene, controller);

        root.setTop(menuBar);
        root.setCenter(canvasPane);
        root.setLeft(shapeChooserPanel);
        root.setRight(propertiesPanel);
        scene.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("commandMenuBar/paint-style.css")).toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Paint");
        stage.show();

        //this.commandManager = new CommandManager(view);

        // listen to window size change events and change canvas size correspondingly
        // width change handler
        stage.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            view.setWidth(view.getWidth() + newWidth.doubleValue() - oldWidth.doubleValue());
            model.update();
        });
        // height change handler
        stage.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            view.setHeight(view.getHeight() + newHeight.doubleValue() - oldHeight.doubleValue());
            model.update();
        });
    }
}
