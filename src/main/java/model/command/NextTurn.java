package model.command;

import model.Code;
import model.Game;
import model.TuringException;
import model.validators.ValidatorModel;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code NextTurn} class represents a command to move to the next turn in the game.
 *
 * <p>This class implements the {@link Command} interface and is responsible for capturing
 * the state of the game before and after the execution of the command.</p>
 *
 * <p>When executed, it records the current input code and validators, and when undone,
 * it restores the previous input code and validators.</p>
 *
 * @author [Your Name]
 * @version 1.0
 */
public class NextTurn implements Command {
    private final List<ValidatorModel> previousValidators;
    private final Game game;
    private Code oldCode;

    /**
     * Constructs a new {@code NextTurn} command.
     *
     * @param game The game for which the next turn is to be executed.
     */
    public NextTurn(Game game) {
        this.previousValidators = new ArrayList<>();
        this.game = game;
    }

    /**
     * Retrieves the list of elements to submit for testing purposes.
     *
     * @return the list of elements to submit
     */
    List<ValidatorModel> getPreviousValidators() {
        return previousValidators;
    }

    /**
     * Retrieves the old code for testing purposes.
     *
     * @return old code
     */
    Code getOldCode() {
        return oldCode;
    }

    /**
     * Executes the next turn command, capturing the current state of the game.
     */
    @Override
    public void execute() {
        List<Object> previousElements = game.nextTurn();
        if (previousElements.get(0) != null) {
            this.oldCode = (Code) previousElements.get(0);
        } else {
            this.oldCode = null;
        }
        for (int i = 1; i < previousElements.size(); i++) {
            previousValidators.add((ValidatorModel) previousElements.get(i));
        }
    }

    /**
     * Undoes the next turn command, restoring the previous state of the game.
     *
     * @throws TuringException If an error occurs during the undo process.
     */
    @Override
    public void unexecute() throws TuringException {
        game.previousTurn(oldCode, previousValidators);
    }
}
