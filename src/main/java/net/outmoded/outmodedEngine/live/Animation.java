package net.outmoded.outmodedEngine.live;

import java.util.UUID;

public class Animation {
    private final UUID uuid;
    private int frame = 0;

    /**
     * there are no checks on if it exists or not
     * @param uuid
     */
    public Animation(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public int getFrame() {
        return frame;
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }
}
