package dev.breno.engine;

import java.awt.*;

public abstract class Element {
    public int x, y, width, height;

    public Element(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void render(Graphics graphics) {
        throw new NotImplementedException();
    }

    public void update() {
        throw new NotImplementedException();
    }

    public boolean isColliding(Element element) {
        return isCollidingInAxiosX(element) && isCollidingInAxiosY(element);
    }

    private boolean isCollidingInAxiosY(Element element) {
        return y < element.y + element.height && y + height > element.y;
    }

    private boolean isCollidingInAxiosX(Element element) {
        return x <  element.x + element.width && x + width > element.x;
    }

    public int center() {
        return this.y + (this.height / 2);
    }

}
