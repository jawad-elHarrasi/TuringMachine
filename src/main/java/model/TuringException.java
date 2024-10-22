package model;

/**
 * The {@code TuringException} class represents an exception specific to the Turing game.
 *
 * <p>Instances of this exception are thrown to indicate errors or exceptional conditions
 * that occur during the execution of the Turing game.</p>
 *
 * <p>The exception message provides additional information about the nature of the error.</p>
 *
 * @author [JAWAD EL HARRASI]
 * @version 1.0
 */
public class TuringException extends Exception {

    /**
     * Constructs a new {@code TuringException} with the specified detail message.
     *
     * @param message The detail message that provides information about the error.
     */
    public TuringException(String message) {
        super(message);
    }
}

