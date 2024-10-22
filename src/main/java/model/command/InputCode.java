package model.command;

import model.Code;
import model.Game;

/**
 * The {@code InputCode} class represents a command to input a code in the game.
 *
 * <p>This class implements the {@link Command} interface and is responsible for capturing
 * the state of the game before and after the execution of the command.</p>
 *
 * <p>When executed, it records the current input code, and when undone, it restores the
 * previous input code.</p>
 *
 * @author [JAWAD EL HARRASI]
 * @version 1.0
 */
public class InputCode implements Command {
    private Code oldCode;
    private Code inputCode;
    private Game game;

    Code getInputCode() {
        return inputCode;
    }

    Code getOldCode() {
        return oldCode;
    }

    /**
     * Constructs a new {@code InputCode} command.
     *
     * @param game      The game in which the code is input.
     * @param inputCode The code to be input.
     */
    public InputCode(Game game, Code inputCode) {
        this.inputCode = inputCode;
        this.game = game;
    }

    /**
     * Executes the input code command, capturing the current state of the game.
     */
    @Override
    public void execute() {
        this.oldCode = game.inputACode(inputCode);
    }

    /**
     * Undoes the input code command, restoring the previous state of the game.
     */
    @Override
    public void unexecute() {
        game.inputACode(oldCode);
    }
}
