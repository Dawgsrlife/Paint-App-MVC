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

/**
 * CommandMenuBar is responsible for creating the "File", "Edit", "Accessibility", and
 * "Help" menus, each containing different commands that the user can execute.(Constructor)
 * This class implements the EventHandler interface to handle menu item actions,
 * executing the appropriate command when a user selects a menu item.(handle method)
 */
public class CommandMenuBar extends MenuBar implements EventHandler<ActionEvent> {

    PaintModel model;
    Scene scene;
    PaintController controller;

    /**
     * Initializes a new CommandMenuBar instance, adding all menus on the top of the canvas
     * and their respective command items to provide the user interface for controlling
     * the application.
     *
     * @param model      The PaintModel that manages shapes and shapes' interactions.
     * @param scene      The JavaFX Scene to which this menu bar is added.
     * @param controller The PaintController.
     */
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

    /**
     * Handles the execution of commands when a menu item is selected.
     * This method casts the event source to a Command and executes
     * it to specific command class.
     *
     * @param event The ActionEvent triggered by selecting a menu item.
     */
    @Override
    public void handle(ActionEvent event) {
        Command c = (Command) event.getSource();
        c.execute(model, scene, controller);

    }
}
