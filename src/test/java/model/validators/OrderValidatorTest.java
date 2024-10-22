package model.validators;

import model.Code;
import model.TuringException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderValidatorTest {

    @Test
    void croissant() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(123));
        OrderValidator validator = (OrderValidator) validatorCreator.createAValidator(22);
        validator.matching(new Code(345));
        assertTrue(validator.isMatching());
    }

    @Test
    void decroissant() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(321));
        OrderValidator validator = (OrderValidator) validatorCreator.createAValidator(22);
        validator.matching(new Code(543));
        assertTrue(validator.isMatching());
    }



    @Test
    void disorder() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(314));
        OrderValidator validator = (OrderValidator) validatorCreator.createAValidator(22);
        validator.matching(new Code(251));
        assertTrue(validator.isMatching());
    }

    @Test
    void oneCroissantOneNot() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(123));
        OrderValidator validator = (OrderValidator) validatorCreator.createAValidator(22);
        validator.matching(new Code(251));
        assertFalse(validator.isMatching());
    }
}