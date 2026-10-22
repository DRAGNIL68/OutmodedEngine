package net.outmoded.outmodedEngine.defaultFormats.blockbench;

import net.outmoded.fastListenerRegistration.EventListener;
import net.outmoded.outmodedEngine.defaultFormats.blockbench.live.BBModel;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;
@EventListener
public class TestEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){

        NamespacedKey key = new NamespacedKey("test", "test");
        BBModel bbModel = new BBModel(UUID.randomUUID(), key, event.getPlayer().getLocation());
        bbModel.register();
    }

}
