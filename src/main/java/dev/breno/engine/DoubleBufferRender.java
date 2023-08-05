package dev.breno.engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.List;

import static java.awt.Font.PLAIN;

public class DoubleBufferRender extends Canvas implements BufferRender {
    private final JFrame frame;
    private List<Element> observers;
    private BufferedImage image;

    public DoubleBufferRender(RenderConfig config) {
        frame = new JFrame(config.title());
        setPreferredSize(new Dimension(config.width(), config.height()));
        frame.add(this);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (image != null) {
            g.drawImage(image, 0, 0, null);
        }
    }

    public void render(double delay) {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        if (observers == null) {
            return;
        }
        // create a new image if it doesn't exist or if the size has changed
        if (image == null || image.getWidth() != getWidth() || image.getHeight() != getHeight()) {
            image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        }

        // draw the observers onto the image
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, getWidth(), getHeight());

        observers.forEach(observer -> observer.render(graphics));
        graphics.dispose();
        // draw the image onto the canvas
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bs.show();
    }

    public void pause() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        if (observers == null) {
            return;
        }

        // create a new image if it doesn't exist or if the size has changed
        if (image == null || image.getWidth() != getWidth() || image.getHeight() != getHeight()) {
            image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        }

        // draw the observers onto the image
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, getWidth(), getHeight());

        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Arial", PLAIN, 32));
        graphics.drawString("PAUSED", (getWidth() / 2) - 48, getHeight() / 2 - 16);

        graphics.setFont(new Font("Arial", PLAIN, 16));
        graphics.drawString("press SPACE to unpause", (getWidth() / 2) - 80, (getHeight() / 2) + 48);
        graphics.dispose();


        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bs.show();
    }

    public DoubleBufferRender setObservers(List<Element> newObservers) {
        observers = newObservers;
        return this;
    }

    public DoubleBufferRender addKeyListeners(KeyListener... keyListeners) {
        List.of(keyListeners).forEach(this::addKeyListener);
        return this;
    }

}
