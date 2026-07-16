package net.outmoded.outmodedEngine;

import com.github.retrooper.packetevents.protocol.entity.type.EntityTypes;
import com.github.retrooper.packetevents.util.Vector3d;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerEntityMetadata;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerSpawnEntity;
import net.outmoded.outmodedEngine.interfaces.AnimationController;
import net.outmoded.outmodedEngine.live.Model;
import net.outmoded.outmodedEngine.packets.PacketBuilder;
import net.outmoded.outmodedEngine.packets.PacketUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class DefaultController implements AnimationController {
    private final int id = PacketUtils.getNextId();
    private final Model model;

    public DefaultController(Model model){
        this.model = model;
    }



    @Override
    public void tick() {

        List<Player> list = new ArrayList<>(Bukkit.getOnlinePlayers());

        WrapperPlayServerSpawnEntity serverSpawnEntity = new PacketBuilder.Entity(EntityTypes.ITEM_DISPLAY)
                .entityId(id)
                .location(new Vector3d(100, 100, 100))
                .build();


        WrapperPlayServerEntityMetadata wrapperPlayServerEntityMetadata = new PacketBuilder.Update(id).itemstack(PacketUtils.convertItemstack(new ItemStack(Material.ACACIA_BOAT))).build();

        PacketUtils.sendPacketToPlayers(serverSpawnEntity, list);
        PacketUtils.sendPacketToPlayers(wrapperPlayServerEntityMetadata, list);
    }
}
