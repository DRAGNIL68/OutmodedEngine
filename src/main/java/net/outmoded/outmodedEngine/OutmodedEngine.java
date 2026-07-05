package net.outmoded.outmodedEngine;

import org.bukkit.plugin.java.JavaPlugin;
import org.joml.Vector3f;
import org.joml.Vector3fc;

public final class OutmodedEngine extends JavaPlugin {

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public static OutmodedEngine getInstance() {
        return getPlugin(OutmodedEngine.class);
    }
}
