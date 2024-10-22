package model.validators;

import model.Code;
import model.TuringException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OccurrenceValidatorTest {

    @Test
    void matchingFindOneTimeAOne() throws TuringException {
        OccurrenceValidator occurrenceValidator = new OccurrenceValidator(new Code(122), 1);
        occurrenceValidator.matching(new Code(212));
        assertTrue(occurrenceValidator.isMatching());
    }

    @Test
    void matchingFindTwoTimeAOne() throws TuringException {
        OccurrenceValidator occurrenceValidator = new OccurrenceValidator(new Code(112), 1);
        occurrenceValidator.matching(new Code(211));
        assertTrue(occurrenceValidator.isMatching());
    }

    @Test
    void matchingFindTreeTimeAOne() throws TuringException {
        OccurrenceValidator occurrenceValidator = new OccurrenceValidator(new Code(111), 1);
        occurrenceValidator.matching(new Code(111));
        assertTrue(occurrenceValidator.isMatching());
    }

    @Test
    void matchingFindZeroTimeAOne() throws TuringException {
        OccurrenceValidator occurrenceValidator = new OccurrenceValidator(new Code(222), 1);
        occurrenceValidator.matching(new Code(444));
        assertTrue(occurrenceValidator.isMatching());
    }

    @Test
    void noMatchingFindingOne() throws TuringException {
        OccurrenceValidator occurrenceValidator = new OccurrenceValidator(new Code(122), 1);
        occurrenceValidator.matching(new Code(444));
        assertFalse(occurrenceValidator.isMatching());
    }

}