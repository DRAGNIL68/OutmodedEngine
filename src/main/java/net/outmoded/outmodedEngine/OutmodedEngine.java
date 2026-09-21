package net.outmoded.outmodedEngine;

import com.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import net.outmoded.outmodedEngine.live.Model;
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

        // this is here to start some code not very smart
        ModelTemplateManager.getInstance();
        ModelManager.getInstance();
        // ####################################


//        PacketUtils.getNextId();
//        ModelTemplate modelTemplate = new ModelTemplate.Builder().build();
//
//        NamespacedKey namespacedKey = new NamespacedKey("frog", "test");
//
//        ModelTemplateManager.getInstance().registerModelTemplate(namespacedKey, modelTemplate);




    }

    @Override
    public void onDisable() { PacketEvents.getAPI().terminate(); }


    public static OutmodedEngine getInstance() {
        return getPlugin(OutmodedEngine.class);
    }
}
