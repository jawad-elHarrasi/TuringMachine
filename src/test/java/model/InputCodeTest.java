package model;

import model.Code;
import model.Game;
import model.TuringException;
import model.command.InputCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputCodeTest {

    @Test
    void execute() throws TuringException {
        Game game = new Game(10);
        game.launchGame();
        Code code = new Code(123);
        InputCode input = new InputCode(game,code);
        input.execute();
        assertEquals(game.getInputCode(), code);
    }
    @Test
    void unexecute() throws TuringException {
        Game game = new Game(10);
        game.launchGame();
        Code code = new Code(123);
        InputCode input = new InputCode(game,code);
        input.execute();
        input.unexecute();
        assertNull(game.getInputCode());
    }
}