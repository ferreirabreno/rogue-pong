package dev.breno.player.command;

import dev.breno.Configuration;
import dev.breno.engine.Element;
import dev.breno.engine.command.Command;

public class PlayerDownCommand implements Command {
    @Override
    public void execute(Element element) {
        if (element.y + element.height < Configuration.getStaticHeightWithOffside()) {
            element.y += 5;
        }
    }
}
