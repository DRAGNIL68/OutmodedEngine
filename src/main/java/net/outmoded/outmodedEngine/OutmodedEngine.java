package net.outmoded.outmodedEngine;

import net.outmoded.outmodedEngine.live.Model;
import net.outmoded.outmodedEngine.live.ModelManager;
import net.outmoded.outmodedEngine.templates.ModelTemplate;
import net.outmoded.outmodedEngine.templates.ModelTemplateManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.joml.Vector3f;
import org.joml.Vector3fc;

public final class OutmodedEngine extends JavaPlugin {

    @Override
    public void onEnable() {
        ModelTemplate modelTemplate = new ModelTemplate.Builder().build();

        NamespacedKey namespacedKey = new NamespacedKey("frog", "test");

        ModelTemplateManager.getInstance().addModelTemplate(namespacedKey, modelTemplate);

        getServer().getPluginManager().registerEvents(new Evet(), this); // model saving and loading


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public static OutmodedEngine getInstance() {
        return getPlugin(OutmodedEngine.class);
    }
}
