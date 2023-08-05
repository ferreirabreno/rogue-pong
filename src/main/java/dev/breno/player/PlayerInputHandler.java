package dev.breno.player;

import dev.breno.engine.KeyEventEnum;
import dev.breno.engine.command.Command;
import dev.breno.player.command.PlayerDoNothingCommand;
import dev.breno.player.command.PlayerDownCommand;
import dev.breno.player.command.PlayerUpCommand;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerInputHandler implements KeyListener {

    private KeyEventEnum up;
    private KeyEventEnum down;
    private Command upCommand = new PlayerUpCommand();
    private Command downCommand = new PlayerDownCommand();
    private Command doNothingCommand = new PlayerDoNothingCommand();
    private Command input = doNothingCommand;

    public PlayerInputHandler(KeyEventEnum up, KeyEventEnum down) {
        this.up = up;
        this.down = down;
    }

    public Command getInput() {
        return input;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();
        if (up.isCodeEquals(code) || down.isCodeEquals(code)) {
            this.input = doNothingCommand;
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();
        if (up.isCodeEquals(code)) {
            this.input = upCommand;
        } else if (down.isCodeEquals(code)) {
            this.input = downCommand;
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {}

}
