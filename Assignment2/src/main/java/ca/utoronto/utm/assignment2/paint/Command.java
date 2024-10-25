package ca.utoronto.utm.assignment2.paint;

public interface Command {
    void execute();  // Execute the command
    void undo();     // Undo the command
}
