package dev.breno.enviroment;

import dev.breno.engine.Element;

import java.awt.*;

import static java.awt.Font.PLAIN;

public class Score extends Element {
    private Integer points = 0;
    public Score(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Arial", PLAIN, 24));
        graphics.drawString(points.toString(), x, y);
    }

    public void addPoint() {
        this.points++;
    }
}
