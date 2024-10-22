package model.validators;

import model.Code;
import model.TuringException;

/**
 * The InternalCompareValidator class is responsible for comparing two different positions
 * within a code and notifying observers about the matching state and whether the comparison
 * has been asked.
 *
 * <p>The class extends ValidatorModel and provides functionality to compare the values at
 * two specified positions in the user's input code with the corresponding values in the
 * secret code.
 *
 * <p>Usage example:
 * <pre>{@code
 * Code secretCode = new Code("123");
 * InternalCompareValidator validator = new InternalCompareValidator(secretCode, 0, 2);
 * Code userInput = new Code("456");
 * validator.matching(userInput);
 * }</pre>
 *
 * @author Your Name
 * @version 1.0
 * @see ValidatorModel
 */
public class InternalCompareValidator extends ValidatorModel {
    private final int firstPosition;
    private final int secondPosition;
    private final boolean[] toNotify = new boolean[2];
    private boolean asked;
    private boolean isMatching;

    /**
     * Constructs an InternalCompareValidator object with the specified parameters.
     *
     * @param secretCode     The secret code against which the comparison is made.
     * @param firstPosition  The position of the first digit to compare.
     * @param secondPosition The position of the second digit to compare.
     */
    public InternalCompareValidator(Code secretCode, int firstPosition, int secondPosition) {
        super(secretCode);
        this.firstPosition = firstPosition;
        this.secondPosition = secondPosition;
        this.isMatching = false;
    }

    /**
     * Gets the matching state.
     *
     * @return True if the values at the specified positions are matching, false otherwise.
     */
    boolean isMatching() {
        return isMatching;
    }

    /**
     * Checks if the values at the specified positions in the user's input code match the
     * corresponding values in the secret code. Notifies observers about the matching state
     * and whether the comparison has been asked.
     *
     * @param inputCode The user's input code to compare.
     * @throws TuringException If the input code contains a number with more or less than 3 digits.
     */
    @Override
    public void matching(Code inputCode) throws TuringException {
        if (numberAtPos(inputCode.getCode(), 0) > 5 || numberAtPos(inputCode.getCode(), 0) < 1
                || numberAtPos(inputCode.getCode(), 1) > 5 || numberAtPos(inputCode.getCode(), 1) < 1
                || numberAtPos(inputCode.getCode(), 2) > 5 || numberAtPos(inputCode.getCode(), 2) < 1) {
            throw new TuringException("every number of the code must be between 1 and 5");
        }


        isMatching = numberAtPos(inputCode.getCode(), firstPosition) == numberAtPos(inputCode.getCode(), secondPosition)
                && numberAtPos(secretCode.getCode(), firstPosition) == numberAtPos(secretCode.getCode(), secondPosition)
                || numberAtPos(inputCode.getCode(), firstPosition) > numberAtPos(inputCode.getCode(), secondPosition)
                && numberAtPos(secretCode.getCode(), firstPosition) > numberAtPos(secretCode.getCode(), secondPosition)
                || numberAtPos(inputCode.getCode(), firstPosition) < numberAtPos(inputCode.getCode(), secondPosition)
                && numberAtPos(secretCode.getCode(), firstPosition) < numberAtPos(secretCode.getCode(), secondPosition);
        this.asked = true;
        this.toNotify[0] = asked;
        this.toNotify[1] = isMatching;
        support.firePropertyChange("NEW STATE FOR MATCHING AND ASKED", null, toNotify);
    }

    /**
     * Checks if the comparison has been asked.
     *
     * @return True if the comparison has been asked, false otherwise.
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

    /**
     * Returns a string representation of the InternalCompareValidator object.
     *
     * @return A string describing the comparison details.
     */
    @Override
    public String toString() {
        String word = "";
        switch (firstPosition) {
            case 0 -> word = "first";
            case 1 -> word = "second";
            case 2 -> word = "third";
        }
        String word2 = "";
        switch (firstPosition) {
            case 0 -> word2 = "first";
            case 1 -> word2 = "second";
            case 2 -> word2 = "third";
        }
        return "verify the " + word + " number compared to the "
                + word2 + " one (first < second or = or >";
    }
}
