package dev.breno.engine.command;

import dev.breno.engine.Element;
import dev.breno.engine.GameLoop;

public class GamePauseCommand implements Command {
    @Override
    public void execute(Element element) {
        GameLoop.getInstance().pause();
    }
}
