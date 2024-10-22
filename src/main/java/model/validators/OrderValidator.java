package model.validators;

import model.Code;
import model.TuringException;

/**
 * The {@code OrderValidator} class is responsible for validating the order of numbers in a given code.
 * It checks whether the numbers are in ascending order, descending order, or no specific order.
 * It extends the {@code ValidatorModel} class and implements the logic to compare the order of numbers
 * in the secret code and the input code.
 *
 * <p>This class is designed to work with 3-digit codes where each digit is between 1 and 5 (inclusive).</p>
 *
 * @author [JAWAD EL HARRASI]
 * @version 1.0
 * @see ValidatorModel
 */
public class OrderValidator extends ValidatorModel {

    private final boolean[] toNotify = new boolean[2];
    private boolean asked;
    private boolean isMatching;

    /**
     * Constructs an {@code OrderValidator} with the specified secret code.
     *
     * @param secretCode The secret code to validate against.
     */
    public OrderValidator(Code secretCode) {
        super(secretCode);
        this.isMatching = false;
    }

    /**
     * Checks whether the occurrence in the input code matches the occurrence in the secret code.
     *
     * @return {@code true} if the occurrences match, {@code false} otherwise.
     */
    boolean isMatching() {
        return isMatching;
    }

    /**
     * Checks whether the order of numbers in the input code matches the order in the secret code.
     *
     * @param inputCode The input code to be validated.
     * @throws TuringException If the input code is not a 3-digit code with digits between 1 and 5.
     */
    @Override
    public void matching(Code inputCode) throws TuringException {
        if (numberAtPos(inputCode.getCode(), 0) > 5 || numberAtPos(inputCode.getCode(), 0) < 1
                || numberAtPos(inputCode.getCode(), 1) > 5 || numberAtPos(inputCode.getCode(), 1) < 1
                || numberAtPos(inputCode.getCode(), 2) > 5 || numberAtPos(inputCode.getCode(), 2) < 1) {
            throw new TuringException("every number of the code must be between 1 and 5");
        }

        boolean inputCroissant = numberAtPos(inputCode.getCode(), 0) < numberAtPos(inputCode.getCode(), 1)
                && numberAtPos(inputCode.getCode(), 1) < numberAtPos(inputCode.getCode(), 2);

        boolean secretCroissant = numberAtPos(secretCode.getCode(), 0) < numberAtPos(secretCode.getCode(), 1)
                && numberAtPos(secretCode.getCode(), 1) < numberAtPos(secretCode.getCode(), 2);

        boolean inputDecreasing = numberAtPos(inputCode.getCode(), 0) > numberAtPos(inputCode.getCode(), 1)
                && numberAtPos(inputCode.getCode(), 1) > numberAtPos(inputCode.getCode(), 2);

        boolean secretDecreasing = numberAtPos(secretCode.getCode(), 0) > numberAtPos(secretCode.getCode(), 1)
                && numberAtPos(secretCode.getCode(), 1) > numberAtPos(secretCode.getCode(), 2);

        isMatching = (inputCroissant && secretCroissant || inputDecreasing && secretDecreasing
                || !inputCroissant && !inputDecreasing && !secretCroissant && !secretDecreasing);

        this.asked = true;
        this.toNotify[0] = asked;
        this.toNotify[1] = isMatching;
        support.firePropertyChange("NEW STATE FOR MATCHING AND ASKED", null, toNotify);
    }

    /**
     * Gets the current state of whether the order has been asked.
     *
     * @return {@code true} if the order has been asked, {@code false} otherwise.
     */
    public boolean isAsked() {
        return asked;
    }

    /**
     * Resets the state to indicate that the order has not been asked.
     */
    @Override
    public void makeNotAsked() {
        this.asked = false;
        this.toNotify[0] = asked;
        this.toNotify[1] = isMatching;
        support.firePropertyChange("NEW STATE FOR MATCHING AND ASKED", null, toNotify);
    }

    /**
     * Gets a string representation of the validator, describing its purpose.
     *
     * @return A string describing the purpose of the validator.
     */
    @Override
    public String toString() {
        return "Verify if the 3 numbers in the code are in ascending order, descending order, or no specific order.";
    }
}

