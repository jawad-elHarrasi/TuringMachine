package view.consoleInterface;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

/**
 * ValidatorConsole represents a console-based view of a validator's state.
 * It implements the {@link PropertyChangeListener} interface to listen for changes
 * in the validator's state and update its string representation accordingly.
 * <p>
 * The class provides constants for ANSI color codes for better console formatting.
 * </p>
 *
 * @author JAWAD EL HARRASI
 * @version 1.0
 */
public class ValidatorConsole implements PropertyChangeListener {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    String validatorString;

    /**
     * Constructs a ValidatorConsole with the given string representation.
     *
     * @param string The initial string representation of the validator.
     */
    public ValidatorConsole(String string) {
        validatorString = string;
    }

    /**
     * Returns the string representation of the validator.
     *
     * @return The string representation of the validator.
     */
    @Override
    public String toString() {
        return validatorString;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Handles property change events related to the validator's state and updates
     * the string representation accordingly, applying ANSI color codes for formatting.
     * </p>
     *
     * @param evt The property change event.
     * @see PropertyChangeListener#propertyChange(PropertyChangeEvent)
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (Objects.equals(evt.getPropertyName(), "NEW STATE FOR MATCHING AND ASKED")) {
            boolean[] newValues = (boolean[]) evt.getNewValue();
            if (newValues[0]) {
                if (newValues[1]) {
                    validatorString = ANSI_GREEN + validatorString + ANSI_RESET;
                } else {
                    validatorString = ANSI_RED + validatorString + ANSI_RESET;
                }

            } else {
                validatorString = validatorString.replaceFirst("\u001B\\[\\d{1,2}m", "");
            }

        }

    }
}
