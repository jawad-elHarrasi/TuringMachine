package model.validators;

import model.Code;
import model.TuringException;

import java.util.HashSet;

/**
 * The {@code RepetitionValidator} class is responsible for validating whether there is any repetition of
 * numbers in a given code. It checks if any number repeats itself in the input code compared to the secret code.
 *
 * <p>This class is designed to work with 3-digit codes where each digit is between 1 and 5 (inclusive).</p>
 *
 * @author [JAWAD EL HARRASI]
 * @version 1.0
 * @see ValidatorModel
 */
public class RepetitionValidator extends ValidatorModel {

    private final boolean[] toNotify = new boolean[2];
    private boolean asked;
    private boolean isMatching;

    /**
     * Constructs a {@code RepetitionValidator} with the specified secret code.
     *
     * @param secretCode The secret code to validate against.
     */
    public RepetitionValidator(Code secretCode) {
        super(secretCode);
        this.isMatching = false;
    }

    /**
     * Checks whether there is any repetition of numbers in the input code compared to the secret code.
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

        HashSet<Integer> doubleInInput = new HashSet<>();
        doubleInInput.add(numberAtPos(inputCode.getCode(), 0));
        doubleInInput.add(numberAtPos(inputCode.getCode(), 1));
        doubleInInput.add(numberAtPos(inputCode.getCode(), 2));

        HashSet<Integer> doubleInSecret = new HashSet<>();
        doubleInSecret.add(numberAtPos(secretCode.getCode(), 0));
        doubleInSecret.add(numberAtPos(secretCode.getCode(), 1));
        doubleInSecret.add(numberAtPos(secretCode.getCode(), 2));

        isMatching = doubleInInput.size() == doubleInSecret.size();
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
     * Gets the current state of whether the repetition has been asked.
     *
     * @return {@code true} if the repetition has been asked, {@code false} otherwise.
     */
    public boolean isAsked() {
        return asked;
    }

    /**
     * Resets the state to indicate that the repetition has not been asked.
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
        return "Verify if a number repeats itself in the code.";
    }
}
