package model.validators;

import model.Code;
import model.TuringException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TwinValidatorTest {

    @Test
    void twin() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(113));
        TwinValidator validator = (TwinValidator) validatorCreator.createAValidator(21);
        validator.matching(new Code(323));
        assertTrue(validator.isMatching());
    }

    @Test
    void notTwin() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(123));
        TwinValidator validator = (TwinValidator) validatorCreator.createAValidator(21);
        validator.matching(new Code(325));
        assertTrue(validator.isMatching());
    }

    @Test
    void twinInOneOfOneOfTheCode() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(123));
        TwinValidator validator = (TwinValidator) validatorCreator.createAValidator(21);
        validator.matching(new Code(323));
        assertFalse(validator.isMatching());
    }
}