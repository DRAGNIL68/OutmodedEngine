package net.outmoded.outmodedEngine.default_formats.blockbench.live;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Animation {
    private final String name;
    private AtomicInteger frame = new AtomicInteger(0);
    private AtomicBoolean paused = new AtomicBoolean(false);

    /**
     * there are no checks on if it exists or not
     * @param name
     */
    public Animation(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getFrame() {
        return frame.get();
    }

    public void setFrame(int frame) {
        this.frame.set(frame);
    }

    public boolean isPaused() {
        return paused.get();
    }

    public void setPaused(boolean b) {
        this.paused.set(b);
    }
}
