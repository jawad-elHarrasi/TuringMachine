package model.validators;

import model.Code;
import model.TuringException;

/**
 * The {@code ParityFrequencyValidator} class is responsible for validating the parity frequency of numbers
 * in a given code. It checks whether the number of even and odd numbers in the code is matching the
 * secret code's parity distribution.
 *
 * <p>This class is designed to work with 3-digit codes where each digit is between 1 and 5 (inclusive).</p>
 *
 * @author [JAWAD EL HARRASI]
 * @version 1.0
 * @see ValidatorModel
 */
public class ParityFrequencyValidator extends ValidatorModel {

    private final boolean[] toNotify = new boolean[2];
    private boolean asked;
    private boolean isMatching;

    /**
     * Constructs a {@code ParityFrequencyValidator} with the specified secret code.
     *
     * @param secretCode The secret code to validate against.
     */
    public ParityFrequencyValidator(Code secretCode) {
        super(secretCode);
        this.isMatching = false;
    }

    /**
     * Checks whether the parity frequency of even numbers in the input code matches the secret code.
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

        int cptPairSecret = 0;
        int cptPairInput = 0;

        for (int i = 0; i < 3; i++) {
            if (numberAtPos(secretCode.getCode(), i) % 2 == 0) {
                cptPairSecret++;
            }
            if (numberAtPos(inputCode.getCode(), i) % 2 == 0) {
                cptPairInput++;
            }
        }

        isMatching = cptPairSecret >= 2 && cptPairInput >= 2 || cptPairSecret < 2 && cptPairInput < 2;
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
     * Gets the current state of whether the parity frequency has been asked.
     *
     * @return {@code true} if the parity frequency has been asked, {@code false} otherwise.
     */
    public boolean isAsked() {
        return asked;
    }

    /**
     * Resets the state to indicate that the parity frequency has not been asked.
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
        return "Verify the number of even numbers compared to the number of odd numbers.";
    }
}
