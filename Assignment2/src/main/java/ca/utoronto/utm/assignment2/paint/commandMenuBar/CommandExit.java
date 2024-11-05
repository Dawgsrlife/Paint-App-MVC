package ca.utoronto.utm.assignment2.paint.commandMenuBar;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import javafx.application.Platform;

public class CommandExit implements Command {

    @Override
    public void execute(PaintModel model) {
        Platform.exit();
    }
}
