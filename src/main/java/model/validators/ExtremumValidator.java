package model.validators;

import model.Code;
import model.TuringException;

import java.util.function.BiPredicate;

/**
 * The ExtremumValidator class is responsible for verifying the order of digits in a code
 * based on a provided BiPredicate. It notifies observers about the matching state and
 * whether the verification has been asked.
 *
 * <p>The class extends ValidatorModel and provides functionality to compare the order of
 * digits in the user's input code with the expected order based on a BiPredicate.
 *
 * <p>Usage example:
 * <pre>{@code
 * Code secretCode = new Code("123");
 * BiPredicate<Integer, Integer> orderPredicate = (a, b) -> a < b;
 * ExtremumValidator validator = new ExtremumValidator(secretCode, orderPredicate);
 * Code userInput = new Code("456");
 * validator.matching(userInput);
 * }</pre>
 *
 * @author Your Name
 * @version 1.0
 * @see ValidatorModel
 */
public class ExtremumValidator extends ValidatorModel {
    private final boolean[] toNotify = new boolean[2];
    BiPredicate<Integer, Integer> order;
    private boolean asked;
    private boolean isMatching;

    /**
     * Constructs an ExtremumValidator object with the specified parameters.
     *
     * @param secretCode The secret code against which the order is verified.
     * @param order      The BiPredicate used to define the order criteria.
     */
    public ExtremumValidator(Code secretCode, BiPredicate<Integer, Integer> order) {
        super(secretCode);
        this.order = order;
        this.isMatching = false;
    }

    /**
     * Gets the matching state.
     *
     * @return True if the order of digits is matching, false otherwise.
     */
    boolean isMatching() {
        return isMatching;
    }

    /**
     * Checks if the order of digits in the user's input code matches the expected order
     * based on the provided BiPredicate. Notifies observers about the matching state
     * and whether the verification has been asked.
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
        isMatching = order.test(numberAtPos(inputCode.getCode(), 0), numberAtPos(inputCode.getCode(), 1))
                && order.test(numberAtPos(secretCode.getCode(), 0), numberAtPos(secretCode.getCode(), 1))
                && order.test(numberAtPos(inputCode.getCode(), 0), numberAtPos(inputCode.getCode(), 2))
                && order.test(numberAtPos(secretCode.getCode(), 0), numberAtPos(secretCode.getCode(), 2))
                || order.test(numberAtPos(inputCode.getCode(), 1), numberAtPos(inputCode.getCode(), 0))
                && order.test(numberAtPos(secretCode.getCode(), 1), numberAtPos(secretCode.getCode(), 0))
                && order.test(numberAtPos(inputCode.getCode(), 1), numberAtPos(inputCode.getCode(), 2))
                && order.test(numberAtPos(secretCode.getCode(), 1), numberAtPos(secretCode.getCode(), 2))
                || order.test(numberAtPos(inputCode.getCode(), 2), numberAtPos(inputCode.getCode(), 0))
                && order.test(numberAtPos(secretCode.getCode(), 2), numberAtPos(secretCode.getCode(), 0))
                && order.test(numberAtPos(inputCode.getCode(), 2), numberAtPos(inputCode.getCode(), 1))
                && order.test(numberAtPos(secretCode.getCode(), 2), numberAtPos(secretCode.getCode(), 1));
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

    /**
     * Returns a string representation of the ExtremumValidator object.
     *
     * @return A string describing the verification details.
     */
    @Override
    public String toString() {
        String word;
        if (order.test(1, 2)) {
            word = "smaller";
        } else {
            word = "bigger";
        }
        return "verify witch number are the " + word + " (firs, second or third )";
    }

}
