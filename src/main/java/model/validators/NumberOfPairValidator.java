package model.validators;

import model.Code;
import model.TuringException;

/**
 * The NumberOfPairValidator class is responsible for verifying the number of even digits
 * in a code and notifying observers about the matching state and whether the verification
 * has been asked.
 *
 * <p>The class extends ValidatorModel and provides functionality to count the number of
 * even digits in the user's input code and compare it with the number of even digits in the
 * secret code.
 *
 * <p>Usage example:
 * <pre>{@code
 * Code secretCode = new Code("123");
 * NumberOfPairValidator validator = new NumberOfPairValidator(secretCode);
 * Code userInput = new Code("456");
 * validator.matching(userInput);
 * }</pre>
 *
 * @author Your Name
 * @version 1.0
 * @see ValidatorModel
 */
public class NumberOfPairValidator extends ValidatorModel {

    private final boolean[] toNotify = new boolean[2];
    private boolean asked;
    private boolean isMatching;

    /**
     * Constructs a NumberOfPairValidator object with the specified parameters.
     *
     * @param secretCode The secret code against which the number of even digits is verified.
     */
    public NumberOfPairValidator(Code secretCode) {
        super(secretCode);
        this.isMatching = false;
    }

    /**
     * Gets the matching state.
     *
     * @return True if the number of even digits is matching, false otherwise.
     */
    boolean isMatching() {
        return isMatching;
    }

    /**
     * Checks if the number of even digits in the user's input code matches the number of even
     * digits in the secret code. Notifies observers about the matching state and whether
     * the verification has been asked.
     *
     * @param inputCode The user's input code to verify.
     * @throws TuringException If the input code contains a number with more or less than 3 digits.
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
        isMatching = cptPairInput == cptPairSecret;
        this.asked = true;
        this.toNotify[0] = asked;
        this.toNotify[1] = isMatching;
        support.firePropertyChange("NEW STATE FOR MATCHING AND ASKED", null, toNotify);

    }

    /**
     * Checks if the verification has been asked.
     *
     * @return True if the verification has been asked, false otherwise.
     */
    public boolean isAsked() {
        return asked;
    }

    /**
     * Resets the asked state to false.
     */
    @Override
    public void makeNotAsked() {
        this.asked = false;
        this.toNotify[0] = asked;
        this.toNotify[1] = isMatching;
        support.firePropertyChange("NEW STATE FOR MATCHING AND ASKED", null, toNotify);
    }


    @Override
    public String toString() {
        return "verify how many even numbers are in the code";
    }
}
