package ca.utoronto.utm.assignment2.paint;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class View implements EventHandler<ActionEvent> {

    private PaintModel paintModel;
    private PaintPanel paintPanel;
    private ShapeChooserPanel shapeChooserPanel;
    private PropertiesPanel propertiesPanel;

    public View(PaintModel model, Stage stage) {
        this.paintModel = model;

        this.propertiesPanel = new PropertiesPanel(this);
        this.paintPanel = new PaintPanel(this.paintModel, this.propertiesPanel);
        this.shapeChooserPanel = new ShapeChooserPanel(this);

        //sub panels
        BorderPane root = new BorderPane();
        BorderPane left = new BorderPane();
        left.setTop(this.shapeChooserPanel);
        left.setCenter(this.propertiesPanel);
        BorderPane right = new BorderPane();
        right.setTop(new StepsPanel(this));
        right.setCenter(new EditingPanel(this));
        root.setTop(createMenuBar());
        root.setCenter(this.paintPanel);
        root.setLeft(left);
        root.setRight(right);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Paint");
        stage.show();
    }

    public PaintModel getPaintModel() {
        return this.paintModel;
    }

    // ugly way to do this?
    public void setMode(String mode) {
        this.paintPanel.setMode(mode);
    }

    private MenuBar createMenuBar() {

        MenuBar menuBar = new MenuBar();
        Menu menu;
        MenuItem menuItem;

        // A menu for File

        menu = new Menu("File");

        menuItem = new MenuItem("New");
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menuItem = new MenuItem("Open");
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menuItem = new MenuItem("Save");
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menu.getItems().add(new SeparatorMenuItem());

        menuItem = new MenuItem("Exit");
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menuBar.getMenus().add(menu);

        // Another menu for Edit

        menu = new Menu("Edit");

        menuItem = new MenuItem("Cut");
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menuItem = new MenuItem("Copy");
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menuItem = new MenuItem("Paste");
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menu.getItems().add(new SeparatorMenuItem());
        menuItem = new MenuItem("Undo");
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menuItem = new MenuItem("Redo");
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menuBar.getMenus().add(menu);

        return menuBar;
    }


    @Override
    public void handle(ActionEvent event) {
        System.out.println(((MenuItem) event.getSource()).getText());
        String command = ((MenuItem) event.getSource()).getText();
        System.out.println(command);
        if (command.equals("Exit")) {
            Platform.exit();
        }
    }

    public PaintPanel getPaintPanel() {
        return this.paintPanel;
    }

}
