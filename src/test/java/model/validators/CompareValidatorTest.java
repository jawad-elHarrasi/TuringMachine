package model.validators;

import model.Code;
import model.TuringException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CompareValidatorTest {

    @Test
    void matchingWithOneAtTheFirstPlace() throws TuringException {
        CompareValidator compareValidator = new CompareValidator(new Code(123), 1,0);
        compareValidator.matching(new Code(123));
        Assertions.assertTrue(compareValidator.isMatching());
    }

    @Test
    void matchingWithOutOneAtFirstPlace() throws TuringException {
        CompareValidator compareValidator = new CompareValidator(new Code(523), 1,0);
        compareValidator.matching(new Code(244));
        Assertions.assertTrue(compareValidator.isMatching());
    }

    @Test
    void notMatchingValidator1() throws TuringException {
        CompareValidator compareValidator = new CompareValidator(new Code(423), 1,0);
        compareValidator.matching(new Code(132));
        Assertions.assertFalse(compareValidator.isMatching());
    }

    //----------------------------------------------------------------------------------------------
    @Test
    void matchingWithTreeAtTheFirstPlace() throws TuringException {
        CompareValidator compareValidator = new CompareValidator(new Code(323), 3,0);
        compareValidator.matching(new Code(355));
        Assertions.assertTrue(compareValidator.isMatching());
    }

    @Test
    void matchingWithMoreThanTreeAtFirstPlace() throws TuringException {
        CompareValidator compareValidator = new CompareValidator(new Code(523), 3,0);
        compareValidator.matching(new Code(522));
        Assertions.assertTrue(compareValidator.isMatching());
    }

    @Test
    void matchingWithLessThanTreeAtFirstPlace() throws TuringException {
        CompareValidator compareValidator = new CompareValidator(new Code(223), 3,0);
        compareValidator.matching(new Code(244));
        Assertions.assertTrue(compareValidator.isMatching());
    }

    @Test
    void notMatchingValidator2() throws TuringException {
        CompareValidator compareValidator = new CompareValidator(new Code(423), 3,0);
        compareValidator.matching(new Code(122));
        Assertions.assertFalse(compareValidator.isMatching());
    }

    //-----------------------------------------------------------------------------------------------------
    @Test
    void matchingWithTreeAtTheSecondPlace() throws TuringException {
        CompareValidator compareValidator = new CompareValidator(new Code(333), 3,1);
        compareValidator.matching(new Code(331));
        Assertions.assertTrue(compareValidator.isMatching());
    }

    @Test
    void matchingWithMoreThanTreeAtSecondPlace() throws TuringException {
        CompareValidator compareValidator = new CompareValidator(new Code(555), 3,1);
        compareValidator.matching(new Code(551));
        Assertions.assertTrue(compareValidator.isMatching());
    }

    @Test
    void matchingWithLessThanTreeAtSecondPlace() throws TuringException {
        CompareValidator compareValidator = new CompareValidator(new Code(223), 3,1);
        compareValidator.matching(new Code(221));
        Assertions.assertTrue(compareValidator.isMatching());
    }

    @Test
    void notMatchingValidator3() throws TuringException {
        CompareValidator compareValidator = new CompareValidator(new Code(423), 3,1);
        compareValidator.matching(new Code(154));
        Assertions.assertFalse(compareValidator.isMatching());
    }
    //----------------------------------------------------------------------------------------------
    @Test
    void matchingWithFourAtTheSecondPlace() throws TuringException {
        CompareValidator compareValidator = new CompareValidator(new Code(343), 4,1);
        compareValidator.matching(new Code(341));
        Assertions.assertTrue(compareValidator.isMatching());
    }

    @Test
    void matchingWithMoreThanFourAtSecondPlace() throws TuringException {
        CompareValidator compareValidator = new CompareValidator(new Code(555), 4,1);
        compareValidator.matching(new Code(551));
        Assertions.assertTrue(compareValidator.isMatching());
    }

    @Test
    void matchingWithLessThanFourAtSecondPlace() throws TuringException {
        CompareValidator compareValidator = new CompareValidator(new Code(223), 4,1);
        compareValidator.matching(new Code(224));
        Assertions.assertTrue(compareValidator.isMatching());
    }

    @Test
    void notMatchingValidator4() throws TuringException {
        CompareValidator compareValidator = new CompareValidator(new Code(423), 4,1);
        compareValidator.matching(new Code(152));
        Assertions.assertFalse(compareValidator.isMatching());
    }

    @Test
    void Matchingerror() throws TuringException {
        CompareValidator compareValidator = new CompareValidator(new Code(423), 4,1);
        Assertions.assertThrows(IllegalArgumentException.class, ()
                -> compareValidator.matching(new Code(1719)));
    }
}