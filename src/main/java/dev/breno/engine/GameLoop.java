package dev.breno.engine;

import java.util.List;

public class GameLoop implements Runnable {
    private final BufferRender bufferRender;
    private final Thread thread = new Thread(this);
    private final long MS_PER_UPDATE;
    private boolean running = true;
    private boolean exit = false;
    private final List<Element> observers;

    public GameLoop(BufferRender bufferRender, int framesPerSeconds, List<Element> observers) {
        this.bufferRender = bufferRender;
        this.MS_PER_UPDATE = 1000 / framesPerSeconds;
        this.observers = observers;
    }

    public synchronized void start() {
        thread.start();
    }

    @Override
    public void run() {
        long frames = 0;
        long updates = 0;
        long timer = System.currentTimeMillis();
        long previous = System.currentTimeMillis();
        double lag = 0;
        while (!exit) {
            while(isRunning()) {
                long current = System.currentTimeMillis();
                lag += (current - previous);
                previous = current;

                while (lag >= MS_PER_UPDATE) {
                    update();
                    lag -= MS_PER_UPDATE;
                    updates++;
//                frames++;
//                render(lag / MS_PER_UPDATE);
                }

                if (System.currentTimeMillis() - timer >= 1000) {
                    System.out.println("FPS " + frames + " UPS " + updates + " " + lag + "ms");
                    timer = System.currentTimeMillis();
                    frames = 0;
                    updates = 0;
                }

                frames++;
                render(lag / MS_PER_UPDATE);
            }
        }
        System.exit(0);
    }

    private void render(double delay) {
        bufferRender.render(delay);
    }

    private void update() {
        observers.forEach(Element::update);
    }

    public boolean isRunning() {
        return running && !exit;
    }

}
