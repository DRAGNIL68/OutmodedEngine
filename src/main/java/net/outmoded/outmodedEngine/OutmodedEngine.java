package net.outmoded.outmodedEngine;

import com.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import net.outmoded.outmodedEngine.live.ModelManager;
import net.outmoded.outmodedEngine.packets.PacketUtils;
import net.outmoded.outmodedEngine.templates.ModelTemplate;
import net.outmoded.outmodedEngine.templates.ModelTemplateManager;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class OutmodedEngine extends JavaPlugin {


    @Override
    public void onLoad() {
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
        PacketEvents.getAPI().load();

    }

    @Override
    public void onEnable() {

        PacketEvents.getAPI().init();

        PacketUtils.getNextId();
        ModelTemplate modelTemplate = new ModelTemplate.Builder().build();

        NamespacedKey namespacedKey = new NamespacedKey("frog", "test");

        ModelTemplateManager.getInstance().addModelTemplate(namespacedKey, modelTemplate);

        getServer().getPluginManager().registerEvents(new Evet(), this); // model saving and loading


        new BukkitRunnable() {
            @Override
            public void run() {

                ModelManager.getInstance().tickAllModels();
            }

        }.runTaskTimerAsynchronously(OutmodedEngine.getInstance(), 0, 1);


    }

    @Override
    public void onDisable() {
        PacketEvents.getAPI().terminate();
    }


    public static OutmodedEngine getInstance() {
        return getPlugin(OutmodedEngine.class);
    }
}
