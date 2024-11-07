//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ca.utoronto.utm.assignment2.paint;

import ca.utoronto.utm.assignment2.paint.commandMenuBar.CommandMenuBar;
import ca.utoronto.utm.assignment2.paint.controlPanels.EditingPanel;
import ca.utoronto.utm.assignment2.paint.controlPanels.PropertiesPanel;
import ca.utoronto.utm.assignment2.paint.controlPanels.ShapeChooserPanel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Paint extends Application {
    PaintModel model;
    PaintView view;
    PaintController controller;
    CommandMenuBar menuBar;
    ShapeChooserPanel shapeChooserPanel;
    PropertiesPanel propertiesPanel;
    EditingPanel editingPanel;

    public Paint() {
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        this.model = new PaintModel();
        this.menuBar = new CommandMenuBar();
        this.shapeChooserPanel = new ShapeChooserPanel();
        this.propertiesPanel = new PropertiesPanel();
        this.editingPanel = new EditingPanel();
        this.controller = new PaintController(this.model, this.shapeChooserPanel, this.propertiesPanel, this.editingPanel);

        Pane canvasPane = new Pane(); // Create new Pane
        this.controller.setCanvasPane(canvasPane);
        this.view = new PaintView(this.model, this.controller);
        canvasPane.getChildren().add(this.view);

        BorderPane root = new BorderPane();
        root.setTop(this.menuBar.createMenuBar(this.model));
        root.setCenter(canvasPane); //
        BorderPane left = new BorderPane();
        left.setTop(this.shapeChooserPanel);
        left.setCenter(this.propertiesPanel);
        root.setLeft(left);
        root.setRight(this.editingPanel);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Paint");
        stage.show();
        stage.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            this.view.setWidth(this.view.getWidth() + newWidth.doubleValue() - oldWidth.doubleValue());
            this.view.update();
        });
        stage.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            this.view.setHeight(this.view.getHeight() + newHeight.doubleValue() - oldHeight.doubleValue());
            this.view.update();
        });
    }
}
