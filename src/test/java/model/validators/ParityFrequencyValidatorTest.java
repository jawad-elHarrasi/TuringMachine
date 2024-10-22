package model.validators;

import model.Code;
import model.TuringException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParityFrequencyValidatorTest {

    @Test
    void morePair() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(423));
        ParityFrequencyValidator validator = (ParityFrequencyValidator) validatorCreator.createAValidator(16);
        validator.matching(new Code(421));
        assertTrue(validator.isMatching());
    }

    @Test
    void moreImpair() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(433));
        ParityFrequencyValidator validator = (ParityFrequencyValidator) validatorCreator.createAValidator(16);
        validator.matching(new Code(121));
        assertTrue(validator.isMatching());
    }

    @Test
    void notSameInInputAndSecretTrowsFalse() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(433));
        ParityFrequencyValidator validator = (ParityFrequencyValidator) validatorCreator.createAValidator(16);
        validator.matching(new Code(221));
        assertFalse(validator.isMatching());
    }

}