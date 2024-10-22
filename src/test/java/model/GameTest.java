package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {


    @Test
    public void confirmATrueCode() throws TuringException {
        Game game = new Game(3);
        game.launchGame();
        game.inputCodeCommand(331);
        assertTrue(game.confirmACode());
    }

    @Test
    public void confirmAFalseCode() throws TuringException {
        Game game = new Game(3);
        game.launchGame();
        game.inputCodeCommand(123);
        assertFalse(game.confirmACode());
    }

    @Test
    public void askAValidator() throws TuringException {
        Game game = new Game(3);
        game.launchGame();
        game.inputCodeCommand(123);
        game.checkValidator(0);
        assertTrue(game.getValidators().get(0).isAsked());
    }

    @Test
    public void checkMoreThanTreeValidator() throws TuringException {
        Game game = new Game(3);
        game.launchGame();
        game.inputCodeCommand(123);
        game.checkValidator(0);
        game.checkValidator(1);
        game.checkValidator(2);
        assertThrows(Exception.class, () -> game.checkValidator(3));
    }

    @Test
    public void checkAValidatorWitchDoesNotExist() throws TuringException {
        Game game = new Game(3);
        game.launchGame();
        game.inputCodeCommand(123);
        assertThrows(Exception.class, () -> game.checkValidator(7));
    }

    @Test
    public void checkAValidatorWitchDoesNotExist2() throws TuringException {
        Game game = new Game(3);
        game.launchGame();
        game.inputCodeCommand(123);
        assertThrows(Exception.class, () -> game.checkValidator(-1));
    }

    @Test
    public void checkANullInput() throws TuringException {
        Game game = new Game(3);
        game.launchGame();
        assertThrows(Exception.class, () -> game.checkValidator(3));
    }

    @Test
    public void recheckAValidator() throws TuringException {
        Game game = new Game(3);
        game.launchGame();
        game.inputCodeCommand(123);
        game.checkValidator(3);
        assertThrows(Exception.class, () -> game.checkValidator(3));
    }

    @Test
    public void uncheckValidator() throws TuringException {
        Game game = new Game(3);
        game.launchGame();
        game.inputCodeCommand(123);
        game.checkValidator(3);
        game.unCheckValidator(3);
        assertFalse(game.getValidators().get(3).isAsked());
    }

    @Test
    public void inputACode2times() throws TuringException {
        Game game = new Game(3);
        game.launchGame();
        game.inputCodeCommand(123);
        assertThrows(Exception.class, () -> game.inputCodeCommand(321));
    }

}