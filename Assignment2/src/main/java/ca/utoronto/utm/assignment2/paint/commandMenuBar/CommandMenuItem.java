package ca.utoronto.utm.assignment2.paint.commandMenuBar;


import ca.utoronto.utm.assignment2.paint.PaintModel;
import javafx.scene.control.MenuItem;
import javafx.scene.Scene;

public class CommandMenuItem extends MenuItem implements Command {
    private Command command;

    public CommandMenuItem(String text, Command command) {
        super(text);
        this.command = command;
        this.setOnAction(event -> execute(null, null));
    }

    @Override
    public void execute(PaintModel model, Scene scene) {
        if (command != null) {
            command.execute(model, scene);
        }
    }
}

