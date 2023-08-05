package dev.breno.engine;

import java.util.List;
import java.util.Objects;

public class GameLoop implements Runnable {
    private static GameLoop instance = null;
    private BufferRender bufferRender;
    private final Thread thread = new Thread(this);
    private long MS_PER_UPDATE;
    private boolean running = true;
    private boolean exit = false;
    private List<Element> observers;

    private GameLoop() {}

    public static GameLoop getInstance() {
        if (Objects.isNull(instance)) {
            instance = new GameLoop();
        }
        return instance;
    }

    public GameLoop setBufferRender(BufferRender bufferRender) {
        this.bufferRender = bufferRender;
        return this;
    }

    public GameLoop setFramesPerSeconds(int framesPerSeconds) {
        this.MS_PER_UPDATE = 1000 / framesPerSeconds;
        return this;
    }

    public GameLoop setObservers(List<Element> observers) {
        this.observers = observers;
        return this;
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
            long current = System.currentTimeMillis();
            lag += (current - previous);
            previous = current;

            while (lag >= MS_PER_UPDATE ) {
                if (running) {
                    update();
                }
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
            if (running) {
                render(lag / MS_PER_UPDATE);
            } else {
                bufferRender.pause();
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

    public void pause() {
        running = !running;
        System.out.println("GAME LOOP PAUSE = " + running);
    }

    public void exit() {
        exit = true;
    }

}
