package net.outmoded.outmodedEngine.live;

import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.atomic.AtomicBoolean;

public class TickRunnable extends BukkitRunnable {

    private final AtomicBoolean isRunning = new AtomicBoolean(false);

    @Override
    public void run() {
        if (isRunning.get())
            return;

        isRunning.set(true);
        ModelManager.getInstance().tickAllModels();
        isRunning.set(false);
    }

    public boolean isRunning(){
        return isRunning.get();
    }
}
