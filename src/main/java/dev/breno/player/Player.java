package dev.breno.player;

import dev.breno.engine.Element;

import java.awt.*;

public class Player extends Element {

    private PlayerInputHandler inputHandler;

    public Player(int x, int y, int width, int height, PlayerInputHandler inputHandler) {
        super(x, y, width, height);
        this.inputHandler = inputHandler;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(x, y, width, height);
    }

    @Override
    public void update() {
        inputHandler.getInput().execute(this);
    }

    public boolean isTopCollision(Element element) {
        return this.isColliding(element) && element.center() <= this.height / 4 + this.y;
    }

    public boolean isBottomCollision(Element element) {
        return this.isColliding(element) && element.center() >= 3 * (this.height / 4) + this.y;
    }

}
