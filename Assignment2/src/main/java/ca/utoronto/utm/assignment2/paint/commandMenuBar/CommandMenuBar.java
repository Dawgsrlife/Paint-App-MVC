package ca.utoronto.utm.assignment2.paint.commandMenuBar;

import ca.utoronto.utm.assignment2.paint.PaintController;
import ca.utoronto.utm.assignment2.paint.PaintModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

public class CommandMenuBar extends MenuBar implements EventHandler<ActionEvent> {

    PaintModel model;
    Scene scene;
    PaintController controller;

    public CommandMenuBar(PaintModel model, Scene scene, PaintController controller) {
        this.model = model;
        this.scene = scene;
        this.controller = controller;
        Menu menu;
        MenuItem menuItem;
        getStyleClass().add("menu-bar");


        // A menu for File

        menu = new Menu("File");
        menu.getStyleClass().add("menu");

        menuItem = new CommandNew();
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);
        menuItem.getStyleClass().add("menu-item");

        menuItem = new CommandOpen();
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);
        menuItem.getStyleClass().add("menu-item");

        menuItem = new CommandSave();
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);
        menuItem.getStyleClass().add("menu-item");

        SeparatorMenuItem separator = new SeparatorMenuItem();
        menu.getItems().add(separator);
        separator.getStyleClass().add("separator");

        menuItem = new CommandExit();
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);
        menuItem.getStyleClass().add("menu-item");

        getMenus().add(menu);


        // Another menu for Edit

        menu = new Menu("Edit");
        menu.getStyleClass().add("menu");

        menuItem = new CommandCut();
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);
        menuItem.getStyleClass().add("menu-item");

        menuItem = new CommandCopy();
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);
        menuItem.getStyleClass().add("menu-item");

        menuItem = new CommandPaste();
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);
        menuItem.getStyleClass().add("menu-item");

        separator = new SeparatorMenuItem();
        menu.getItems().add(separator);
        separator.getStyleClass().add("separator");

        menuItem = new CommandUndo();
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);
        menuItem.getStyleClass().add("menu-item");

        menuItem = new CommandRedo();
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);
        menuItem.getStyleClass().add("menu-item");

        getMenus().add(menu);

        // Another menu for Accessibility

        menu = new Menu("Accessibility");
        menu.getStyleClass().add("menu");

        menuItem = new CommandDarkMode();
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);
        menuItem.getStyleClass().add("menu-item");

        menuItem = new CommandLightMode();
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);
        menuItem.getStyleClass().add("menu-item");

        getMenus().add(menu);

        // Another menu for Help

        menu = new Menu("Help");
        menu.getStyleClass().add("menu");

        menuItem = new CommandCopyright();
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);
        menuItem.getStyleClass().add("menu-item");

        getMenus().add(menu);
    }

    @Override
    public void handle(ActionEvent event) {
        Command c = (Command) event.getSource();
        c.execute(model, scene, controller);

    }
}
