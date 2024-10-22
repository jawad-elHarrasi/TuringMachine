package model.validators;

import model.Code;
import model.TuringException;

/**
 * The {@code ParityValidator} class is responsible for validating the parity (even or odd) of a specific
 * number at a given index in a code. It extends the {@code ValidatorModel} class and implements the logic
 * to check if the parity of the specified number in the input code matches the parity in the secret code.
 *
 * <p>This class is designed to work with 3-digit codes where each digit is between 1 and 5 (inclusive).</p>
 *
 * @author [JAWAD EL HARRASI]
 * @version 1.0
 * @see ValidatorModel
 */
public class ParityValidator extends ValidatorModel {

    private final int index;
    private final boolean[] toNotify = new boolean[2];
    private boolean asked;
    private boolean isMatching;

    /**
     * Constructs a {@code ParityValidator} with the specified secret code and index.
     *
     * @param secretCode The secret code to validate against.
     * @param index      The index of the number to check for parity (0 for the first, 1 for the second, 2 for the third).
     */
    public ParityValidator(Code secretCode, int index) {
        super(secretCode);
        this.index = index;
        this.isMatching = false;
    }

    /**
     * Checks whether the parity of the number at the specified index in the input code matches the secret code.
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

        isMatching = numberAtPos(inputCode.getCode(), index) % 2 == 0
                && numberAtPos(secretCode.getCode(), index) % 2 == 0
                || numberAtPos(inputCode.getCode(), index) % 2 != 0
                && numberAtPos(secretCode.getCode(), index) % 2 != 0;
        this.asked = true;
        this.toNotify[0] = asked;
        this.toNotify[1] = isMatching;
        support.firePropertyChange("NEW STATE FOR MATCHING AND ASKED", null, toNotify);
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
     * Gets the current state of whether the parity has been asked.
     *
     * @return {@code true} if the parity has been asked, {@code false} otherwise.
     */
    public boolean isAsked() {
        return asked;
    }

    /**
     * Resets the state to indicate that the parity has not been asked.
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
        String word = switch (index) {
            case 0 -> "first";
            case 1 -> "second";
            case 2 -> "third";
            default -> "";
        };
        return "Verify if the " + word + " number is even or odd.";
    }

}
