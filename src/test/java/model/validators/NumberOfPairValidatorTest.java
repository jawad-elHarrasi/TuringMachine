package model.validators;

import model.Code;
import model.TuringException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberOfPairValidatorTest {

    @Test
    void zeroPair() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(111));
        NumberOfPairValidator validator = (NumberOfPairValidator) validatorCreator.createAValidator(17);
        validator.matching(new Code(333));
        assertTrue(validator.isMatching());
    }

    @Test
    void onePair() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(211));
        NumberOfPairValidator validator = (NumberOfPairValidator) validatorCreator.createAValidator(17);
        validator.matching(new Code(332));
        assertTrue(validator.isMatching());
    }

    @Test
    void twoPair() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(241));
        NumberOfPairValidator validator = (NumberOfPairValidator) validatorCreator.createAValidator(17);
        validator.matching(new Code(342));
        assertTrue(validator.isMatching());
    }

    @Test
    void threePair() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(242));
        NumberOfPairValidator validator = (NumberOfPairValidator) validatorCreator.createAValidator(17);
        validator.matching(new Code(242));
        assertTrue(validator.isMatching());
    }

    @Test
    void notSameNumberOfPairInSecretAndInput() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(142));
        NumberOfPairValidator validator = (NumberOfPairValidator) validatorCreator.createAValidator(17);
        validator.matching(new Code(242));
        assertFalse(validator.isMatching());
    }

}