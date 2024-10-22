package model;

/**
 * The {@code Code} class represents a three-digit code used in the application.
 * It provides methods for creating and accessing the code, with validation to ensure
 * that the code meets the specified criteria.
 *
 * <p>This class includes a constructor for creating a code, and a method to retrieve the code.</p>
 *
 * @author [Your Name]
 * @version 1.0
 * @see TuringException
 */
public class Code {
    private final int code;

    /**
     * Constructs a {@code Code} with the specified code value.
     *
     * @param code The three-digit code to be set.
     * @throws TuringException If the provided code does not meet the required criteria.
     */
    public Code(int code) throws TuringException {
        if (numberAtPos(code, 0) > 5 || numberAtPos(code, 0) < 1
                || numberAtPos(code, 1) > 5 || numberAtPos(code, 1) < 1
                || numberAtPos(code, 2) > 5 || numberAtPos(code, 2) < 1) {
            throw new TuringException("every number of the code must be between 1 and 5");
        }
        this.code = code;
    }

    /**
     * Retrieves the code value.
     *
     * @return The code value.
     */
    public int getCode() {
        return code;
    }

    private int numberAtPos(int number, int position) {
        if (number > 999 || number < 111 || position > 2 || position < 0) {
            throw new IllegalArgumentException("the input code can't have more or less than 3 digits");
        }
        switch (position) {
            case 0 -> {
                return (number / 100) % 10;
            }
            case 1 -> {
                return (number / 10) % 10;
            }
            case 2 -> {
                return number % 10;
            }
        }
        throw new IllegalArgumentException("Error at numberAtPos");
    }
}
