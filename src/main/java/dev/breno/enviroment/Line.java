package dev.breno.enviroment;

import dev.breno.Configuration;
import dev.breno.engine.Element;

import java.awt.*;

public class Line extends Element {

    private  int numStep = Configuration.getStaticHeight() / height;

    public Line(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        for (int step = 0; step < numStep; step++) {
            graphics.fillRect(x, step * (height + 10), width, height);
        }
    }


}
