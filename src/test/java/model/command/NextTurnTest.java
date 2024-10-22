package model.command;

import model.Code;
import model.Game;
import model.TuringException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NextTurnTest {

    @Test
    void execute() throws TuringException {
        Game game = new Game(2);
        Code code = new Code(123);
        game.inputACode(code);
        NextTurn command = new NextTurn(game);
        command.execute();
        assertNotNull(command.getPreviousValidators());
    }

    @Test
    void unexecute() throws TuringException {
        CommandManager commandManager = new CommandManager();
        Game game = new Game(2);
        Code code = new Code(123);
        game.inputACode(code);
        NextTurn command = new NextTurn(game);
        commandManager.newCommand(command);
        command.execute();
        command.unexecute();
        assertEquals(code, command.getOldCode());
    }
}