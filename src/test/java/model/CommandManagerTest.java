package model;

import model.Code;
import model.Game;
import model.TuringException;
import model.command.CommandManager;
import model.command.InputCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandManagerTest {

    @Test
    void undo() throws TuringException {
        CommandManager commandManager = new CommandManager();
        Game game = new Game(2);
        game.launchGame();
        commandManager.newCommand(new InputCode(game, new Code(123)));
        commandManager.undo();
        assertNull(game.getInputCode());
    }

    @Test
    void redo() throws TuringException {
        CommandManager commandManager = new CommandManager();
        Game game = new Game(2);
        game.launchGame();
        Code code = new Code(123);
        commandManager.newCommand(new InputCode(game, code));
        commandManager.undo();
        commandManager.redo();
        assertEquals(code, game.getInputCode());
    }
}