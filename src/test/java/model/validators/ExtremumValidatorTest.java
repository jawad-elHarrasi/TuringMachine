package model.validators;

import model.Code;
import model.TuringException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExtremumValidatorTest {

    @Test
    void matchingFirstNumberIsTheLower() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(135));
        ExtremumValidator extremumValidator = (ExtremumValidator) validatorCreator.createAValidator(14);
        extremumValidator.matching(new Code(345));
        assertTrue(extremumValidator.isMatching());
    }

    @Test
    void matchingFirstNumberIsNotTheLower() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(435));
        ExtremumValidator extremumValidator = (ExtremumValidator) validatorCreator.createAValidator(14);
        extremumValidator.matching(new Code(543));
        assertFalse(extremumValidator.isMatching());
    }

    //-------------------------------------------------------------------------------------------
    @Test
    void matchingSecondNumberIsTheLower() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(315));
        ExtremumValidator extremumValidator = (ExtremumValidator) validatorCreator.createAValidator(14);
        extremumValidator.matching(new Code(325));
        assertTrue(extremumValidator.isMatching());
    }

    @Test
    void matchingSecondNumberIsNotTheLower() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(435));
        ExtremumValidator extremumValidator = (ExtremumValidator) validatorCreator.createAValidator(14);
        extremumValidator.matching(new Code(543));
        assertFalse(extremumValidator.isMatching());
    }

    //--------------------------------------------------------------------------------------------
    @Test
    void matchingThirdNumberIsTheLower() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(342));
        ExtremumValidator extremumValidator = (ExtremumValidator) validatorCreator.createAValidator(14);
        extremumValidator.matching(new Code(351));
        assertTrue(extremumValidator.isMatching());
    }

    @Test
    void matchingThirdNumberIsNotTheLower() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(435));
        ExtremumValidator extremumValidator = (ExtremumValidator) validatorCreator.createAValidator(14);
        extremumValidator.matching(new Code(543));
        assertFalse(extremumValidator.isMatching());
    }

    @Test
    void anyLower() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(444));
        ExtremumValidator extremumValidator = (ExtremumValidator) validatorCreator.createAValidator(15);
        extremumValidator.matching(new Code(555));
        assertFalse(extremumValidator.isMatching());
    }

    //bigger-------------------------------------------------------------------------------------------
    @Test
    void matchingFirstNumberIsTheBigger() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(534));
        ExtremumValidator extremumValidator = (ExtremumValidator) validatorCreator.createAValidator(15);
        extremumValidator.matching(new Code(543));
        assertTrue(extremumValidator.isMatching());
    }

    @Test
    void matchingFirstNumberIsNotTheBigger() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(435));
        ExtremumValidator extremumValidator = (ExtremumValidator) validatorCreator.createAValidator(15);
        extremumValidator.matching(new Code(143));
        assertFalse(extremumValidator.isMatching());
    }

    //-------------------------------------------------------------------------------------------
    @Test
    void matchingSecondNumberIsTheBigger() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(354));
        ExtremumValidator extremumValidator = (ExtremumValidator) validatorCreator.createAValidator(15);
        extremumValidator.matching(new Code(354));
        assertTrue(extremumValidator.isMatching());
    }

    @Test
    void matchingSecondNumberIsNotTheBigger() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(435));
        ExtremumValidator extremumValidator = (ExtremumValidator) validatorCreator.createAValidator(15);
        extremumValidator.matching(new Code(543));
        assertFalse(extremumValidator.isMatching());
    }

    //--------------------------------------------------------------------------------------------
    @Test
    void matchingThirdNumberIsTheBigger() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(334));
        ExtremumValidator extremumValidator = (ExtremumValidator) validatorCreator.createAValidator(15);
        extremumValidator.matching(new Code(345));
        assertTrue(extremumValidator.isMatching());
    }

    @Test
    void matchingThirdNumberIsNotTheBigger() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(435));
        ExtremumValidator extremumValidator = (ExtremumValidator) validatorCreator.createAValidator(15);
        extremumValidator.matching(new Code(543));
        assertFalse(extremumValidator.isMatching());
    }

    @Test
    void anyBigger() throws TuringException {
        ValidatorFactory validatorCreator = new ValidatorFactory(new Code(444));
        ExtremumValidator extremumValidator = (ExtremumValidator) validatorCreator.createAValidator(15);
        extremumValidator.matching(new Code(555));
        assertFalse(extremumValidator.isMatching());
    }

}