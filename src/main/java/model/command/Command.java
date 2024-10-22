package model.command;

import model.TuringException;

public interface Command {

    /**
     * Executes the command, performing the associated operation.
     */
    void execute() throws TuringException;

    /**
     * Undoes the command, reverting the effects of the executed operation.
     */
    void unexecute() throws TuringException;
}