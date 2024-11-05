package ca.utoronto.utm.assignment2.paint.commandMenuBar;

import ca.utoronto.utm.assignment2.paint.PaintModel;

public class CommandUndo implements Command {
    @Override
    public void execute(PaintModel model) {
        model.undo();
    }
}
