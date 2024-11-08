package ca.utoronto.utm.assignment2.paint;


import ca.utoronto.utm.assignment2.paint.controlPanels.PropertiesPanel;
import ca.utoronto.utm.assignment2.paint.controlPanels.ShapeChooserPanel;
import ca.utoronto.utm.assignment2.paint.commandMenuBar.CommandMenuBar;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Objects;

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

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root);

        this.model = new PaintModel();
        menuBar = new CommandMenuBar(model, scene);
        shapeChooserPanel = new ShapeChooserPanel();
        propertiesPanel = new PropertiesPanel();
        this.controller = new PaintController(model, shapeChooserPanel, propertiesPanel);
        this.view = new PaintView(model, controller);

        root.setTop(menuBar);
        root.setCenter(view);
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
