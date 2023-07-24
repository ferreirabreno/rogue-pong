package dev.breno.enviroment;

import dev.breno.Configuration;
import dev.breno.engine.Element;
import dev.breno.player.Player;

import java.awt.*;

public class Ball extends Element {

    private int direction = 1;
    private int speed = 3;
    private int inclination = 0;
    private Player playerOne;
    private Player playerTwo;
    private Score scoreOne;
    private Score scoreTwo;
    private int xInitial;
    private int yInitial;

    public Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.xInitial = x;
        this.yInitial = y;
    }

    public Ball setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
        return this;
    }

    public Ball setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
        return this;
    }

    public Ball setScoreOne(Score scoreOne) {
        this.scoreOne = scoreOne;
        return this;
    }

    public Ball setScoreTwo(Score scoreTwo) {
        this.scoreTwo = scoreTwo;
        return this;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillOval(x, y , width, height);
    }

    @Override
    public void update() {
        x += speed * direction;
        y += speed * inclination;

        if (this.isColliding(playerOne)) {
            checkPlayerCollision(playerOne);
        } else if (this.isColliding(playerTwo)) {
            checkPlayerCollision(playerTwo);
        }

        if (Configuration.getOffside() > x) {
            scoreOne.addPoint();
            this.reset();
        } else if (Configuration.getStaticWidthWithOffside() - width < x) {
            scoreTwo.addPoint();
            this.reset();
        }

        if (Configuration.getOffside() > y || Configuration.getStaticHeightWithOffside() - height < y) {
            inclination*=-1;
        }
    }

    private void checkPlayerCollision(Player player) {
        if (player.isTopCollision(this)) {
            inclination = -1;
        } else if (player.isBottomCollision(this)) {
            inclination = 1;
        } else {
            inclination = 0;
        }

        direction*=-1;
    }

    public void reset() {
        x = xInitial;
        y = yInitial;
        direction*=-1;
        inclination*=-1;
    }

}
