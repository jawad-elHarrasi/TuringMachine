package model.validators;

import model.Code;
import model.TuringException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepetitionValidatorTest {

    @Test
    void tripe() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(222));
        RepetitionValidator validator = (RepetitionValidator) validatorCreator.createAValidator(20);
        validator.matching(new Code(111));
        assertTrue(validator.isMatching());
    }

    @Test
    void doubleRep() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(113));
        RepetitionValidator validator = (RepetitionValidator) validatorCreator.createAValidator(20);
        validator.matching(new Code(323));
        assertTrue(validator.isMatching());
    }

    @Test
    void anyRepetition() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(234));
        RepetitionValidator validator = (RepetitionValidator) validatorCreator.createAValidator(20);
        validator.matching(new Code(123));
        assertTrue(validator.isMatching());
    }

    @Test
    void repetitionInSecretAndAnyInInputThrowsFalse() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(224));
        RepetitionValidator validator = (RepetitionValidator) validatorCreator.createAValidator(20);
        validator.matching(new Code(123));
        assertFalse(validator.isMatching());
    }
}