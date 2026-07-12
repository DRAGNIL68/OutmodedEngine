package net.outmoded.outmodedEngine;

import net.outmoded.outmodedEngine.live.Model;
import net.outmoded.outmodedEngine.live.ModelManager;
import net.outmoded.outmodedEngine.templates.ModelTemplateManager;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class Evet implements Listener {

    @EventHandler
    public void event(PlayerJoinEvent event){
        event.getPlayer().getLocation();

        NamespacedKey namespacedKey = new NamespacedKey("frog", "test");

        new BukkitRunnable() {
            @Override
            public void run() {
                Model model = new Model.Builder(namespacedKey, event.getPlayer().getLocation()).build();
                model.register();
                ModelManager.getInstance().tickAllModels();
            }

        }.runTaskAsynchronously(OutmodedEngine.getInstance());



    }
}
