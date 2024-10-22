package model.command;

import model.Game;
import model.TuringException;

/**
 * The {@code Verify} class represents a command to verify a specific validator in the game.
 *
 * <p>This class implements the {@link Command} interface and is responsible for capturing
 * the state of the game before and after the execution of the command.</p>
 *
 * <p>When executed, it checks the specified validator in the game, and when undone,
 * it unchecks the same validator.</p>
 *
 * @author [JAWAD EL HARRASI]
 * @version 1.0
 */
public class Verify implements Command {
    private final Game game;
    private final int indexValidator;

    /**
     * Constructs a new {@code Verify} command for the specified game and validator index.
     *
     * @param game           The game for which the verification is to be executed.
     * @param indexValidator The index of the validator to be verified.
     */
    public Verify(Game game, int indexValidator) {
        this.game = game;
        this.indexValidator = indexValidator;
    }

    /**
     * Executes the verification command, checking the specified validator in the game.
     *
     * @throws TuringException If an error occurs during the execution of the command.
     */
    @Override
    public void execute() throws TuringException {
        game.checkValidator(indexValidator);
    }

    /**
     * Undoes the verification command, unchecking the specified validator in the game.
     */
    @Override
    public void unexecute() {
        game.unCheckValidator(indexValidator);
    }
}
