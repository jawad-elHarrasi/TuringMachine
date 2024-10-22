package model;

import model.command.CommandManager;
import model.command.InputCode;
import model.command.NextTurn;
import model.command.Verify;
import model.validators.ValidatorFactory;
import model.validators.ValidatorModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Game} class represents the main logic for a Turing game. It manages the game state,
 * including the secret code, validators, commands, and scoring.
 *
 * <p>This class provides methods to launch the game, input a code, perform next turns, check validators,
 * and manage commands. It also supports undo and redo functionality.</p>
 *
 * <p>The game follows the rules of a Turing game, where players input codes and verify against a secret code
 * with the help of various validators. The goal is to achieve a maximum score by correctly guessing the code.</p>
 *
 * @author [JAWAD EL HARRASI]
 * @version 1.0
 * @see ValidatorModel
 * @see ValidatorFactory
 * @see Code
 * @see TuringException
 * @see CommandManager
 * @see InputCode
 * @see NextTurn
 * @see Verify
 */
public class Game {

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private final List<ValidatorModel> validators;
    private final CommandManager commandManager;
    private final List<Object> forUndoForNextTurn;
    private final Problems problems;
    private final int level;
    private final List<Integer> validatorIndexes;
    private Code inputCode;
    private int score;
    private int turn;
    private Code SecretCode;

    /**
     * Constructs a new {@code Game} with the specified level.
     *
     * @param level The level of the game, ranging from 1 to 16.
     * @throws TuringException if the provided level is invalid.
     */
    public Game(int level) throws TuringException {
        if (level > 16 || level < 1) {
            throw new TuringException("Invalid level. Must be between 1 and 16.");
        }
        this.forUndoForNextTurn = new ArrayList<>();
        this.commandManager = new CommandManager();
        this.validatorIndexes = new ArrayList<>();
        this.validators = new ArrayList<>();
        this.problems = new Problems();
        this.level = level;
        this.score = 0;
        this.turn = 1;
    }
    /**
     * Retrieves the input code for testing purposes.
     *
     * @return input code
     */
    Code getInputCode() {
        return inputCode;
    }

    /**
     * Retrieves the list of validators for testing purposes.
     *
     * @return The list of validators.
     */
    List<ValidatorModel> getValidators() {
        return validators;
    }

    /**
     * Retrieves the list of validator indexes.
     *
     * @return The list of validator indexes.
     */
    public List<Integer> getValidatorIndexes() {
        return validatorIndexes;
    }

    /**
     * Launches the game by initializing the secret code and validators based on the specified level.
     *
     * @throws TuringException if there is an issue initializing the game.
     */
    public void launchGame() throws TuringException {
        this.SecretCode = problems.getSecretCode(this.level);
        ValidatorFactory validatorFactory = new ValidatorFactory(this.SecretCode);
        String[] toAddValidators = problems.getValidator(this.level);
        for (int i = 0; i < toAddValidators.length - 1; i++) {
            validators.add(validatorFactory.createAValidator(Integer.parseInt(toAddValidators[i])));
            validatorIndexes.add(Integer.parseInt(toAddValidators[i]));
        }
    }

    /**
     * Verifies if the provided code matches the secret code.
     *
     * @return {@code true} if the codes match, {@code false} otherwise.
     * @throws TuringException if the input code is null.
     */
    public boolean confirmACode() throws TuringException {
        if (inputCode == null) {
            throw new TuringException("Cannot verify a null number.");
        }
        return this.SecretCode.getCode() == this.inputCode.getCode();
    }

    /**
     * Sets the input code and notifies observers about the modification.
     *
     * @param inputCode The input code to be set.
     * @return The previous input code.
     */
    public Code inputACode(Code inputCode) {
        Code oldCode = this.inputCode;
        this.inputCode = inputCode;
        support.firePropertyChange("INPUT CODE MODIFICATION", null, this.inputCode);
        return oldCode;
    }

    /**
     * Creates a new input code command and adds it to the command manager.
     *
     * @param input The input code to be set.
     * @throws TuringException if an input code has already been set.
     */
    public void inputCodeCommand(int input) throws TuringException {
        if (this.inputCode != null) {
            throw new TuringException("You already inputted a code. Go to the next turn to input a new one.");
        }
        this.commandManager.newCommand(new InputCode(this, new Code(input)));
    }

    /**
     * Advances the game to the next turn, resets validators, and notifies observers.
     *
     * @return The list of objects for undo/redo functionality.
     */
    public List<Object> nextTurn() {
        this.forUndoForNextTurn.add(inputCode);

        for (var validator : this.validators) {
            if (validator.isAsked()) {
                this.forUndoForNextTurn.add(validator);
            }
        }
        for (var validator : this.validators) {
            validator.makeNotAsked();
        }
        this.turn++;
        support.firePropertyChange("TURN MODIFICATION", null, turn);

        this.inputCode = null;
        support.firePropertyChange("INPUT CODE MODIFICATION", null, inputCode);
        return this.forUndoForNextTurn;
    }

    /**
     * Creates a new next turn command and adds it to the command manager.
     *
     * @throws TuringException if there is an issue creating the command.
     */
    public void nextTurnCommand() throws TuringException {
        this.commandManager.newCommand(new NextTurn(this));
    }

    /**
     * Reverts the game to the previous turn with the provided code and validators.
     *
     * @param code      The code for the previous turn.
     * @param toPutBack The list of validators to be put back.
     * @throws TuringException if there is an issue reverting to the previous turn.
     */
    public void previousTurn(Code code, List<ValidatorModel> toPutBack) throws TuringException {
        this.turn--;
        support.firePropertyChange("TURN MODIFICATION", null, turn);
        this.inputCode = code;
        support.firePropertyChange("INPUT CODE MODIFICATION", null, inputCode);

        for (ValidatorModel validatorToPutBack : toPutBack) {
            for (int i = 0; i < this.validators.size(); i++) {
                if (validators.get(i) == validatorToPutBack) {
                    checkValidator(i);
                }
            }
        }
    }

    /**
     * Checks a validator, increments the score, and notifies observers.
     *
     * @param indexValidator The index of the validator to be checked.
     * @throws TuringException if there is an issue checking the validator.
     */
    public void checkValidator(int indexValidator) throws TuringException {
        if (indexValidator < 0 || indexValidator > validators.size() - 1) {
            throw new TuringException("this validator doesn't exist");
        }
        if (validators.get(indexValidator).isAsked()) {
            throw new TuringException("Cannot recheck this validator.");
        } else if (inputCode == null) {
            throw new TuringException("Cannot verify a null number.");
        }
        int nbValidatorChecked = 0;
        for (var validator : validators) {
            if (validator.isAsked()) {
                nbValidatorChecked++;
            }
        }
        if (nbValidatorChecked == 3) {
            throw new TuringException("Cannot verify more than three validators.");
        }

        this.score++;
        support.firePropertyChange("SCORE MODIFICATION", null, score);
        validators.get(indexValidator).matching(this.inputCode);
    }

    /**
     * Unchecks a validator, decrements the score, and notifies observers.
     *
     * @param indexValidator The index of the validator to be unchecked.
     */
    public void unCheckValidator(int indexValidator) {
        this.score--;
        support.firePropertyChange("SCORE MODIFICATION", null, score);
        validators.get(indexValidator).makeNotAsked();
    }

    /**
     * Creates a new verify command for the specified index and adds it to the command manager.
     *
     * @param index The index of the validator to be verified.
     * @throws TuringException if there is an issue creating the verify command.
     */
    public void verifyCommand(int index) throws TuringException {
        this.commandManager.newCommand(new Verify(this, index));
    }

    /**
     * Exits the game.
     */
    public void end() {
        System.exit(0);
    }

    /**
     * Undoes the last command using the command manager.
     *
     * @throws TuringException if there is an issue performing the undo.
     */
    public void undo() throws TuringException {
        commandManager.undo();
    }

    /**
     * Redoes the last undone command using the command manager.
     *
     * @throws TuringException if there is an issue performing the redo.
     */
    public void redo() throws TuringException {
        commandManager.redo();
    }

    /**
     * Retrieves a string representation of a validator based on the specified index.
     *
     * @param index The index of the validator.
     * @return The string representation of the validator.
     */
    public String stringOfAValidator(int index) {
        return this.validators.get(index).toString();
    }

    /**
     * Adds an observer for a specific validator based on the index.
     *
     * @param index    The index of the validator.
     * @param observer The observer to be added.
     */
    public void addObserverForValidator(int index, PropertyChangeListener observer) {
        this.validators.get(index).addObserver(observer);
    }

    /**
     * Adds a general observer for the game state.
     *
     * @param observer The observer to be added.
     */
    public void addObserver(PropertyChangeListener observer) {
        support.addPropertyChangeListener(observer);
    }
}

