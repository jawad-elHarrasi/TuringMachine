package model.validators;

import model.Code;
import model.TuringException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SumParityValidatorTest {

    @Test
    void pairSum() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(422));
        SumParityValidator validator = (SumParityValidator) validatorCreator.createAValidator(18);
        validator.matching(new Code(112));
        assertTrue(validator.isMatching());
    }

    @Test
    void impairSum() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(421));
        SumParityValidator validator = (SumParityValidator) validatorCreator.createAValidator(18);
        validator.matching(new Code(111));
        assertTrue(validator.isMatching());
    }

    @Test
    void incoherentSumOfInputAndSecretThrowsFalse() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(421));
        SumParityValidator validator = (SumParityValidator) validatorCreator.createAValidator(18);
        validator.matching(new Code(112));
        assertTrue(validator.isMatching());
    }

}