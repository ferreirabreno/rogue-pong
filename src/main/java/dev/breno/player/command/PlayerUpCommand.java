package dev.breno.player.command;

import dev.breno.Configuration;
import dev.breno.engine.Command;
import dev.breno.engine.Element;

public class PlayerUpCommand implements Command {
    @Override
    public void execute(Element element) {
        if (element.y > Configuration.getOffside()) {
            element.y -= 5;
        }
    }
}
