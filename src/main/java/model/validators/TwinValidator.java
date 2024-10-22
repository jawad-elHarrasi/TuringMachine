package model.validators;

import model.Code;
import model.TuringException;

import java.util.HashSet;

/**
 * The {@code TwinValidator} class is responsible for validating whether there is a number present
 * exactly twice in a given code. It checks if the number of unique digits in the input code matches
 * the number of unique digits in the secret code.
 *
 * <p>This class is designed to work with 3-digit codes where each digit is between 1 and 5 (inclusive).</p>
 *
 * @author [JAWAD EL HARRASI]
 * @version 1.0
 * @see ValidatorModel
 */
public class TwinValidator extends ValidatorModel {

    private final boolean[] toNotify = new boolean[2];
    private boolean asked;
    private boolean isMatching;

    /**
     * Constructs a {@code TwinValidator} with the specified secret code.
     *
     * @param secretCode The secret code to validate against.
     */
    public TwinValidator(Code secretCode) {
        super(secretCode);
        this.isMatching = false;
    }

    /**
     * Checks whether there is a number present exactly twice in the input code compared to the secret code.
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

        HashSet<Integer> uniqueDigitsInInput = new HashSet<>();
        uniqueDigitsInInput.add(numberAtPos(inputCode.getCode(), 0));
        uniqueDigitsInInput.add(numberAtPos(inputCode.getCode(), 1));
        uniqueDigitsInInput.add(numberAtPos(inputCode.getCode(), 2));

        HashSet<Integer> uniqueDigitsInSecret = new HashSet<>();
        uniqueDigitsInSecret.add(numberAtPos(secretCode.getCode(), 0));
        uniqueDigitsInSecret.add(numberAtPos(secretCode.getCode(), 1));
        uniqueDigitsInSecret.add(numberAtPos(secretCode.getCode(), 2));

        isMatching = uniqueDigitsInInput.size() == 2 && uniqueDigitsInSecret.size() == 2
                || uniqueDigitsInInput.size() != 2 && uniqueDigitsInSecret.size() != 2;
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
     * Gets the current state of whether the twin presence has been asked.
     *
     * @return {@code true} if the twin presence has been asked, {@code false} otherwise.
     */
    public boolean isAsked() {
        return asked;
    }

    /**
     * Resets the state to indicate that the twin presence has not been asked.
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
        return "Verify if there is a number present exactly twice.";
    }
}
