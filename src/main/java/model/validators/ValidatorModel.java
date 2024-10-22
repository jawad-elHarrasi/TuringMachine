package model.validators;

import model.Code;
import model.TuringException;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The {@code ValidatorModel} class is an abstract base class for various validators used in the application.
 * It provides a common structure and methods that are shared among different types of validators.
 *
 * <p>This class includes methods for checking code matches, determining if the validator has been asked,
 * and notifying observers about changes in the validator's state.</p>
 *
 * @author [JAWAD EL HARRASI]
 * @version 1.0
 * @see Code
 * @see TuringException
 * @see PropertyChangeListener
 */
public abstract class ValidatorModel {
    protected final PropertyChangeSupport support = new PropertyChangeSupport(this);
    Code secretCode;

    /**
     * Constructs a {@code ValidatorModel} with the specified secret code.
     *
     * @param secretCode The secret code to be used in validation.
     */
    public ValidatorModel(Code secretCode) {
        this.secretCode = secretCode;
    }

    /**
     * Abstract method to be implemented by subclasses for performing the matching/validation of the input code.
     *
     * @param inputCode The input code to be validated.
     * @throws TuringException If an error occurs during the validation process.
     */
    public abstract void matching(Code inputCode) throws TuringException;

    /**
     * Abstract method to be implemented by subclasses for checking if the validator has been asked.
     *
     * @return {@code true} if the validator has been asked, {@code false} otherwise.
     */
    public abstract boolean isAsked();

    /**
     * Abstract method to be implemented by subclasses for resetting the state to indicate that the validator
     * has not been asked.
     */
    public abstract void makeNotAsked();

    /**
     * Returns the digit at the specified position in the given number.
     *
     * @param number   The number from which to extract the digit.
     * @param position The position of the digit (0, 1, or 2).
     * @return The digit at the specified position.
     * @throws IllegalArgumentException If the parameters are not within the valid range.
     */
    public int numberAtPos(int number, int position) {
        if (number > 999 || number < 0 || position > 2 || position < 0) {
            throw new IllegalArgumentException("the input code can't have more or less than 3 digits");
        }
        switch (position) {
            case 0 -> {
                return (number / 100) % 10;
            }
            case 1 -> {
                return (number / 10) % 10;
            }
            case 2 -> {
                return number % 10;
            }
        }
        throw new IllegalArgumentException("Error at numberAtPos");
    }

    /**
     * Adds an observer to be notified of changes in the validator's state.
     *
     * @param observer The observer to be added.
     */
    public void addObserver(PropertyChangeListener observer) {
        support.addPropertyChangeListener(observer);
    }
}
