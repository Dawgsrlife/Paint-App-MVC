package ca.utoronto.utm.assignment2.paint;


import ca.utoronto.utm.assignment2.paint.controlPanels.PropertiesPanel;
import ca.utoronto.utm.assignment2.paint.controlPanels.ShapeChooserPanel;
import ca.utoronto.utm.assignment2.paint.commandMenuBar.CommandMenuBar;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Paint extends Application {
    PaintModel model; // Model
    PaintView view; // View
    PaintController controller; // Controller
    CommandMenuBar menuBar; // Control
    ShapeChooserPanel shapeChooserPanel; // Control
    PropertiesPanel propertiesPanel; // Control
    // commandManager;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.model = new PaintModel();
        this.menuBar = new CommandMenuBar();
        this.shapeChooserPanel = new ShapeChooserPanel();
        this.propertiesPanel = new PropertiesPanel();

        // Create a Pane to be used as the canvas
        Pane canvasPane = new Pane();
        this.controller = new PaintController(model, shapeChooserPanel, propertiesPanel, canvasPane);
        this.view = new PaintView(model, controller, canvasPane); // Pass canvasPane to view


        BorderPane root = new BorderPane();
        root.setTop(menuBar.createMenuBar(model));
        root.setCenter(canvasPane); // Set canvasPane in the center for drawing
        root.setLeft(shapeChooserPanel);
        root.setRight(propertiesPanel);
        Scene scene = new Scene(root);
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
