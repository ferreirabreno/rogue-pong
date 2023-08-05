package dev.breno;

import dev.breno.engine.GameLoop;
import dev.breno.engine.KeyEventEnum;
import dev.breno.engine.command.Command;
import dev.breno.engine.command.GameExitCommand;
import dev.breno.engine.command.GamePauseCommand;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameInputHandler implements KeyListener {
    private KeyEventEnum pause = KeyEventEnum.VK_SPACE;
    private KeyEventEnum exit = KeyEventEnum.VK_ESCAPE;
    private Command exitCommand = new GameExitCommand();
    private Command pauseCommand = new GamePauseCommand();

    public GameInputHandler() {}

    public GameInputHandler(KeyEventEnum pause, KeyEventEnum exit) {
        this.pause = pause;
        this.exit = exit;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();
        if (pause.isCodeEquals(code)) {
            System.out.println("GAME INPUT HANDLER");
            pauseCommand.execute(null);
        } else if (exit.isCodeEquals(code)) {
            exitCommand.execute(null);
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {}

}
