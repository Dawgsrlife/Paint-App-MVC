package ca.utoronto.utm.assignment2.paint.commandMenuBar;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

public class CommandMenuBar implements EventHandler<ActionEvent> {

    PaintModel model;

    public MenuBar createMenuBar(PaintModel model) {

        this.model = model;

        MenuBar menuBar = new MenuBar();
        Menu menu;
        MenuItem menuItem;

        // A menu for File

        menu = new Menu("File");

        menuItem = new CommandNew();
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menuItem = new MenuItem("Open");
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menuItem = new MenuItem("Save");
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menu.getItems().add(new SeparatorMenuItem());

        menuItem = new CommandExit();
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
        menuItem = new CommandUndo();
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menuItem = new CommandRedo();
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menuBar.getMenus().add(menu);

        return menuBar;
    }

    @Override
    public void handle(ActionEvent event) {
        Command c = (Command) event.getSource();
        c.execute(model);
    }
}
