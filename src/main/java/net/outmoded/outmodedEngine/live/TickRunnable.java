package net.outmoded.outmodedEngine.live;

import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.atomic.AtomicBoolean;

public class TickRunnable extends BukkitRunnable {

    private final AtomicBoolean aBoolean;

    public TickRunnable(AtomicBoolean aBoolean){
        this.aBoolean = aBoolean;
    }

    @Override
    public void run() {
        aBoolean.set(true);
        ModelManager.getInstance().tickAllModels();
        aBoolean.set(false);
    }

}
