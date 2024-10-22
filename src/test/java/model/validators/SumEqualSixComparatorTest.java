package model.validators;

import model.Code;
import model.TuringException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SumEqualSixComparatorTest {
    @Test
    void sumEqualSix() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(331));
        SumEqualSixComparator validator = (SumEqualSixComparator) validatorCreator.createAValidator(19);
        validator.matching(new Code(422));
        assertTrue(validator.isMatching());
    }

    @Test
    void sumNotEqualSix() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(323));
        SumEqualSixComparator validator = (SumEqualSixComparator) validatorCreator.createAValidator(19);
        validator.matching(new Code(232));
        assertTrue(validator.isMatching());
    }

    @Test
    void sumOfInputEqualSixButNotSecretThrowsFalse() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(333));
        SumEqualSixComparator validator = (SumEqualSixComparator) validatorCreator.createAValidator(19);
        validator.matching(new Code(222));
        assertFalse(validator.isMatching());
    }


}