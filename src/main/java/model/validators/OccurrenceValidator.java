package model.validators;

import model.Code;
import model.TuringException;

/**
 * The {@code OccurrenceValidator} class is responsible for validating the occurrence of a specific number
 * in a given code. It extends the {@code ValidatorModel} class and implements the logic to check if the
 * specified occurrence is present in both the secret code and the input code.
 *
 * <p>The class supports notification of changes in its state, such as whether the occurrence has been
 * asked and whether it matches in the secret and input codes.</p>
 *
 * <p>This class is designed to work with 3-digit codes where each digit is between 1 and 5 (inclusive).</p>
 *
 * @author [JAWAD EL HARRASI]
 * @version 1.0
 * @see ValidatorModel
 */
public class OccurrenceValidator extends ValidatorModel {

    private final int occurrenceToFind;
    private final boolean[] toNotify = new boolean[2];
    private boolean asked;
    private boolean isMatching;

    /**
     * Constructs an {@code OccurrenceValidator} with the specified secret code and occurrence to find.
     *
     * @param secretCode       The secret code to validate against.
     * @param occurrenceToFind The occurrence to find in the codes.
     */
    public OccurrenceValidator(Code secretCode, int occurrenceToFind) {
        super(secretCode);
        this.occurrenceToFind = occurrenceToFind;
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
     * Checks if the occurrence is matching in the input code compared to the secret code.
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

        int cptSecretCode = 0;
        int cptInputCode = 0;
        for (int i = 0; i < 3; i++) {
            if (numberAtPos(secretCode.getCode(), i) == occurrenceToFind) {
                cptSecretCode++;
            }
            if (numberAtPos(inputCode.getCode(), i) == occurrenceToFind) {
                cptInputCode++;
            }
        }
        isMatching = cptSecretCode == cptInputCode;
        this.asked = true;
        this.toNotify[0] = asked;
        this.toNotify[1] = isMatching;
        support.firePropertyChange("NEW STATE FOR MATCHING AND ASKED", null, toNotify);

    }

    /**
     * Gets the current state of whether the occurrence has been asked.
     *
     * @return {@code true} if the occurrence has been asked, {@code false} otherwise.
     */
    public boolean isAsked() {
        return asked;
    }

    /**
     * Resets the state to indicate that the occurrence has not been asked.
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
        return "verify the number of " + occurrenceToFind + " in the code (one time, two or three ";
    }
}
