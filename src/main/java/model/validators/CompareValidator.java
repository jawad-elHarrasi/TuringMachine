package model.validators;

import model.Code;
import model.TuringException;

/**
 * The CompareValidator class is responsible for comparing a specific digit in a secret code
 * with a provided value in the user's input code. It notifies observers about the matching state
 * and whether the comparison has been asked.
 *
 * <p>The class extends ValidatorModel and provides functionality to compare a specified digit
 * in the secret code with a given value in the user's input code.
 *
 * <p>Usage example:
 * <pre>{@code
 * Code secretCode = new Code("123");
 * CompareValidator validator = new CompareValidator(secretCode, 2, 1);
 * Code userInput = new Code("456");
 * validator.matching(userInput);
 * }</pre>
 *
 * @author Your Name
 * @version 1.0
 * @see ValidatorModel
 */
public class CompareValidator extends ValidatorModel {
    private final int toCompareWith;
    private final boolean[] toNotify = new boolean[2];
    private final int index;
    private boolean asked;
    private boolean isMatching;

    /**
     * Constructs a CompareValidator object with the specified parameters.
     *
     * @param secretCode    The secret code against which the comparison is made.
     * @param toCompareWith The value to compare with.
     * @param index         The index of the digit to compare in the code.
     */
    public CompareValidator(Code secretCode, int toCompareWith, int index) {
        super(secretCode);

        this.toCompareWith = toCompareWith;
        this.index = index;
        this.isMatching = false;
    }

    /**
     * Gets the matching state.
     *
     * @return True if the digit at the specified index is matching, false otherwise.
     */
    boolean isMatching() {
        return isMatching;
    }

    /**
     * Checks if the digit at the specified index in the user's input code matches the
     * expected value in the secret code. Notifies observers about the matching state and
     * whether the comparison has been asked.
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

        isMatching = numberAtPos(inputCode.getCode(), index) == toCompareWith
                && numberAtPos(secretCode.getCode(), index) == toCompareWith
                || numberAtPos(inputCode.getCode(), index) > toCompareWith
                && numberAtPos(secretCode.getCode(), index) > toCompareWith
                || numberAtPos(inputCode.getCode(), index) < toCompareWith
                && numberAtPos(secretCode.getCode(), index) < toCompareWith;

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
     * Returns a string representation of the CompareValidator object.
     *
     * @return A string describing the comparison details.
     */
    @Override
    public String toString() {
        String word = "";
        switch (index) {
            case 0 -> word = "first";
            case 1 -> word = "second";
            case 2 -> word = "third";
        }
        return "The " + word + " number compared to " +
                toCompareWith + " ( > " + toCompareWith + " or = " + toCompareWith + ") ";
    }
}
