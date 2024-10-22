package controller;

import model.Game;
import model.TuringException;

import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * The controller class that serves as an intermediary between the graphical user interface and the game model.
 * It provides methods to interact with the underlying game logic.
 *
 * @author Your Name
 * @version 1.0
 */
public class GraphicController {

    private final Game game;

    /**
     * Constructs a new GraphicApp instance with a specified game level.
     *
     * @param level The level of the game.
     * @throws TuringException If an error occurs during the creation of the Game object.
     */
    public GraphicController(int level) throws TuringException {
        this.game = new Game(level);
    }

    /**
     * Launches the game.
     *
     * @throws TuringException If an error occurs during the game launch.
     */
    public void lunchTheGame() throws TuringException {
        game.launchGame();
    }

    /**
     * Adds a property change listener to observe changes in the game state.
     *
     * @param observer The observer to be added.
     */
    public void addObserver(PropertyChangeListener observer) {
        this.game.addObserver(observer);
    }

    /**
     * Gets the indexes of validators in the game.
     *
     * @return A list of integer indexes representing validators.
     */
    public List<Integer> getIndexesValidators() {
        return game.getValidatorIndexes();
    }

    /**
     * Adds a property change listener to observe changes in a specific validator.
     *
     * @param index    The index of the validator.
     * @param observer The observer to be added.
     */
    public void addObserverForValidator(int index, PropertyChangeListener observer) {
        game.addObserverForValidator(index, observer);
    }

    /**
     * Executes the next turn command in the game.
     *
     * @throws TuringException If an error occurs during the execution of the next turn command.
     */
    public void nextTurnCommand() throws TuringException {
        game.nextTurnCommand();
    }

    /**
     * Ends the game.
     */
    public void end() {
        game.end();
    }

    /**
     * Undoes the last command in the game.
     *
     * @throws TuringException If an error occurs during the undo operation.
     */
    public void undo() throws TuringException {
        game.undo();
    }

    /**
     * Redoes the previously undone command in the game.
     *
     * @throws TuringException If an error occurs during the redo operation.
     */
    public void redo() throws TuringException {
        game.redo();
    }

    /**
     * Confirms the entered code in the game.
     *
     * @return True if the code is confirmed successfully, false otherwise.
     * @throws TuringException If an error occurs during the confirmation of the code.
     */
    public boolean confirmACode() throws TuringException {
        return game.confirmACode();
    }

    /**
     * Executes the input code command in the game.
     *
     * @param input The input code to be executed.
     * @throws TuringException If an error occurs during the execution of the input code command.
     */
    public void inputCodeCommand(int input) throws TuringException {
        game.inputCodeCommand(input);
    }

    /**
     * Executes the verify command for a specific validator in the game.
     *
     * @param index The index of the validator to be verified.
     * @throws TuringException If an error occurs during the verification of the validator.
     */
    public void verifyCommand(int index) throws TuringException {
        game.verifyCommand(index);
    }
}
