package model.validators;

import model.Code;
import model.TuringException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParityValidatorTest {

    @Test
    void matchingPairFirstValue() throws TuringException {
        ParityValidator parityValidator = new ParityValidator(new Code(233), 0);
        parityValidator.matching(new Code(455));
        assertTrue(parityValidator.isMatching());
    }

    @Test
    void matchingImpairFirstValue() throws TuringException {
        ParityValidator parityValidator = new ParityValidator(new Code(133), 0);
        parityValidator.matching(new Code(355));
        assertTrue(parityValidator.isMatching());
    }

    @Test
    void notMatchingFirstValue() throws TuringException {
        ParityValidator parityValidator = new ParityValidator(new Code(233), 0);
        parityValidator.matching(new Code(355));
        assertFalse(parityValidator.isMatching());
    }

    //-----------------------------------------------------------------------------------------
    @Test
    void matchingPairSecondValue() throws TuringException {
        ParityValidator parityValidator = new ParityValidator(new Code(223), 1);
        parityValidator.matching(new Code(445));
        assertTrue(parityValidator.isMatching());
    }

    @Test
    void matchingImpairSecondValue() throws TuringException {
        ParityValidator parityValidator = new ParityValidator(new Code(133), 1);
        parityValidator.matching(new Code(355));
        assertTrue(parityValidator.isMatching());
    }

    @Test
    void notMatchingSecondValue() throws TuringException {
        ParityValidator parityValidator = new ParityValidator(new Code(222), 1);
        parityValidator.matching(new Code(355));
        assertFalse(parityValidator.isMatching());
    }

    //------------------------------------------------------------------------------------------
    @Test
    void matchingPairThirdValue() throws TuringException {
        ParityValidator parityValidator = new ParityValidator(new Code(222), 2);
        parityValidator.matching(new Code(444));
        assertTrue(parityValidator.isMatching());
    }

    @Test
    void matchingImpairThirdValue() throws TuringException {
        ParityValidator parityValidator = new ParityValidator(new Code(133), 2);
        parityValidator.matching(new Code(355));
        assertTrue(parityValidator.isMatching());
    }

    @Test
    void notMatchingThirdValue() throws TuringException {
        ParityValidator parityValidator = new ParityValidator(new Code(212), 2);
        parityValidator.matching(new Code(355));
        assertFalse(parityValidator.isMatching());
    }
}