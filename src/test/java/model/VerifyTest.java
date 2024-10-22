package model;

import model.Code;
import model.Game;
import model.TuringException;
import model.command.Verify;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VerifyTest {

    @Test
    void execute() throws TuringException {
        Game game = new Game(1);
        game.launchGame();
        game.inputACode(new Code(123));
        Verify command = new Verify(game, 0);
        command.execute();
        assertTrue(game.getValidators().get(0).isAsked());
        assertFalse(game.getValidators().get(1).isAsked());
        assertFalse(game.getValidators().get(2).isAsked());
        assertFalse(game.getValidators().get(3).isAsked());
    }

    @Test
    void unexecute() throws TuringException {
        Game game = new Game(1);
        game.launchGame();
        game.inputACode(new Code(123));
        Verify command = new Verify(game, 0);
        command.execute();
        command.unexecute();
        assertFalse(game.getValidators().get(0).isAsked());
        assertFalse(game.getValidators().get(1).isAsked());
        assertFalse(game.getValidators().get(2).isAsked());
        assertFalse(game.getValidators().get(3).isAsked());
    }
}