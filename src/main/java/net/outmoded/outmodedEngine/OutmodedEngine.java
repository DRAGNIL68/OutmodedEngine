package net.outmoded.outmodedEngine;

import net.outmoded.outmodedEngine.live.ModelManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.joml.Vector3f;
import org.joml.Vector3fc;

public final class OutmodedEngine extends JavaPlugin {

    @Override
    public void onEnable() {

        new BukkitRunnable() {
            @Override
            public void run() {
                ModelManager.getInstance().tickAllModels();
            }

        }.runTaskAsynchronously(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public static OutmodedEngine getInstance() {
        return getPlugin(OutmodedEngine.class);
    }
}
