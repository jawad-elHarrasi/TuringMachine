package model.command;

import model.TuringException;

import java.util.Stack;

public class CommandManager {
    /**
     * Stack to store commands for undo operations.
     */
    private final Stack<Command> undoStack = new Stack<>();

    /**
     * Stack to store commands for redo operations.
     */
    private final Stack<Command> redoStack = new Stack<>();

    /**
     * Executes a new command, adds it to the undo stack, and clears the redo stack.
     *
     * @param command The command to be executed and stored.
     */
    public void newCommand(Command command) throws TuringException {
        command.execute();
        undoStack.push(command);
        redoStack.clear();
    }

    /**
     * Undoes the last executed command by popping it from the undo stack,
     * unexecuting the command, and pushing it onto the redo stack.
     *
     * @throws IllegalArgumentException If there are no operations to undo.
     */
    public void undo() throws TuringException {
        if (!undoStack.isEmpty()) {
            Command command = undoStack.pop();
            command.unexecute();
            redoStack.push(command);
        } else {
            throw new IllegalArgumentException("No operation to undo");
        }
    }

    /**
     * Redoes the last undone command by popping it from the redo stack,
     * executing the command, and pushing it onto the undo stack.
     *
     * @throws IllegalArgumentException If there are no operations to redo.
     */
    public void redo() throws TuringException {
        if (!redoStack.isEmpty()) {
            Command command = redoStack.pop();
            command.execute();
            undoStack.push(command);
        } else {
            throw new IllegalArgumentException("No operation to redo");
        }
    }
}
