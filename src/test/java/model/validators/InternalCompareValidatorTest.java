package model.validators;

import model.Code;
import model.TuringException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InternalCompareValidatorTest {

    @Test
    void matchingEqualFirstWithSecond() throws TuringException {
        InternalCompareValidator validator = new InternalCompareValidator(new Code(223), 0, 1);
        validator.matching(new Code(114));
        assertTrue(validator.isMatching());
    }

    @Test
    void matchingBiggerFirstWithSecond() throws TuringException {
        InternalCompareValidator validator = new InternalCompareValidator(new Code(423), 0, 1);
        validator.matching(new Code(214));
        assertTrue(validator.isMatching());
    }

    @Test
    void matchingLowerFirstWithSecond() throws TuringException {
        InternalCompareValidator validator = new InternalCompareValidator(new Code(123), 0, 1);
        validator.matching(new Code(234));
        assertTrue(validator.isMatching());
    }

    @Test
    void noMatchingFirstWithSecond() throws TuringException {
        InternalCompareValidator validator = new InternalCompareValidator(new Code(213), 0, 1);
        validator.matching(new Code(114));
        assertFalse(validator.isMatching());
    }

    //------------------------------------------------------------------------------------------------------------
    @Test
    void matchingEqualFirstWithThird() throws TuringException {
        InternalCompareValidator validator = new InternalCompareValidator(new Code(223), 0, 2);
        validator.matching(new Code(114));
        assertTrue(validator.isMatching());
    }

    @Test
    void matchingBiggerFirstWithThird() throws TuringException {
        InternalCompareValidator validator = new InternalCompareValidator(new Code(421), 0, 2);
        validator.matching(new Code(412));
        assertTrue(validator.isMatching());
    }

    @Test
    void matchingLowerFirstWithThird() throws TuringException {
        InternalCompareValidator validator = new InternalCompareValidator(new Code(123), 0, 2);
        validator.matching(new Code(234));
        assertTrue(validator.isMatching());
    }

    @Test
    void noMatchingFirstWithThird() throws TuringException {
        InternalCompareValidator validator = new InternalCompareValidator(new Code(443), 0, 2);
        validator.matching(new Code(114));
        assertFalse(validator.isMatching());
    }

    //-----------------------------------------------------------------------------------------------------------------
    @Test
    void matchingEqualSecondWithThird() throws TuringException {
        InternalCompareValidator validator = new InternalCompareValidator(new Code(223), 1, 2);
        validator.matching(new Code(114));
        assertTrue(validator.isMatching());
    }

    @Test
    void matchingBiggerSecondWithThird() throws TuringException {
        InternalCompareValidator validator = new InternalCompareValidator(new Code(423), 1, 2);
        validator.matching(new Code(214));
        assertTrue(validator.isMatching());
    }

    @Test
    void matchingLowerSecondWithThird() throws TuringException {
        InternalCompareValidator validator = new InternalCompareValidator(new Code(123), 1, 2);
        validator.matching(new Code(234));
        assertTrue(validator.isMatching());
    }

    @Test
    void noMatchingSecondWithThird() throws TuringException {
        InternalCompareValidator validator = new InternalCompareValidator(new Code(221), 1, 2);
        validator.matching(new Code(114));
        assertFalse(validator.isMatching());
    }
}