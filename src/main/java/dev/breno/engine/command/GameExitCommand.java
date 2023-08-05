package dev.breno.engine.command;

import dev.breno.engine.command.Command;
import dev.breno.engine.Element;
import dev.breno.engine.GameLoop;

public class GameExitCommand implements Command {
    @Override
    public void execute(Element element) {
        GameLoop.getInstance().exit();
    }
}
