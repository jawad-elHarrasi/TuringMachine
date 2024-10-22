package model.validators;

import model.Code;
import model.TuringException;

import java.util.function.BiPredicate;

/**
 * The {@code ValidatorFactory} class is responsible for creating instances of {@code ValidatorModel}
 * based on the provided index. It serves as a factory for various validators used in the application.
 *
 * <p>This class is designed to work with a {@code Code} representing a secret code.</p>
 *
 * @author [JAWAD EL HARRASI]
 * @version 1.0
 * @see ValidatorModel
 * @see Code
 */
public class ValidatorFactory {

    private final Code secretCode;

    /**
     * Constructs a {@code ValidatorFactory} with the specified secret code.
     *
     * @param secretCode The secret code to be used in validator creation.
     */
    public ValidatorFactory(Code secretCode) {
        this.secretCode = secretCode;
    }

    /**
     * Creates a specific validator based on the provided index.
     *
     * @param indexToCreate The index indicating which validator to create.
     * @return A new instance of {@code ValidatorModel} based on the specified index.
     * @throws TuringException If the provided index is not within the valid range [0, 22].
     */
    public ValidatorModel createAValidator(int indexToCreate) throws TuringException {
        if (indexToCreate < 0 || indexToCreate > 22) {
            throw new TuringException("IndexToCreate needs to be between 0 and 22");
        }
        switch (indexToCreate) {

            case 1 -> {
                return new CompareValidator(secretCode, 1, 1);
            }
            case 2 -> {
                return new CompareValidator(secretCode, 3, 1);
            }
            case 3 -> {
                return new CompareValidator(secretCode, 3, 2);
            }
            case 4 -> {
                return new CompareValidator(secretCode, 4, 2);
            }
            case 5 -> {
                return new ParityValidator(secretCode, 0);
            }
            case 6 -> {
                return new ParityValidator(secretCode, 1);
            }
            case 7 -> {
                return new ParityValidator(secretCode, 2);
            }
            case 8 -> {
                return new OccurrenceValidator(secretCode, 1);
            }
            case 9 -> {
                return new OccurrenceValidator(secretCode, 3);
            }
            case 10 -> {
                return new OccurrenceValidator(secretCode, 4);
            }
            case 11 -> {
                return new InternalCompareValidator(secretCode, 0, 1);
            }
            case 12 -> {
                return new InternalCompareValidator(secretCode, 0, 2);
            }
            case 13 -> {
                return new InternalCompareValidator(secretCode, 1, 2);
            }
            case 14 -> {
                BiPredicate<Integer, Integer> order = (number1, number2) -> number1 < number2;
                return new ExtremumValidator(secretCode, order);

            }
            case 15 -> {
                BiPredicate<Integer, Integer> order = (number1, number2) -> number1 > number2;
                return new ExtremumValidator(secretCode, order);
            }
            case 16 -> {
                return new ParityFrequencyValidator(secretCode);
            }
            case 17 -> {
                return new NumberOfPairValidator(secretCode);
            }
            case 18 -> {
                return new SumParityValidator(secretCode);
            }
            case 19 -> {
                return new SumEqualSixComparator(secretCode);
            }
            case 20 -> {
                return new RepetitionValidator(secretCode);
            }

            case 21 -> {
                return new TwinValidator(secretCode);
            }
            case 22 -> {
                return new OrderValidator(secretCode);
            }
            default -> throw new IllegalArgumentException("Error at create in ValidatorCreator");
        }
    }
}

