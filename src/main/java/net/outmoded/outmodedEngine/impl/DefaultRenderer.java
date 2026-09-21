package net.outmoded.outmodedEngine.impl;

import com.github.retrooper.packetevents.protocol.entity.type.EntityTypes;
import com.github.retrooper.packetevents.util.Quaternion4f;
import com.github.retrooper.packetevents.util.Vector3d;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerEntityMetadata;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerSpawnEntity;
import net.outmoded.outmodedEngine.live.BoneRenderer;
import net.outmoded.outmodedEngine.live.Model;
import net.outmoded.outmodedEngine.live.Node;
import net.outmoded.outmodedEngine.packets.PacketBuilder;
import net.outmoded.outmodedEngine.packets.PacketUtils;
import net.outmoded.outmodedEngine.templates.ModelTemplate;
import net.outmoded.outmodedEngine.templates.NodeTemplate;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class DefaultRenderer implements BoneRenderer {
    private final int id = PacketUtils.getNextId();
    private final Model model;

    public DefaultRenderer(Model model){
        this.model = model;
    }

    // an example of how to render bones
    @Override
    public void process(NodeTemplate nodeTemplate, Node node, Model model) {
        Location location = model.getLocation();

        List<Player> list = new ArrayList<>(Bukkit.getOnlinePlayers());


        WrapperPlayServerSpawnEntity serverSpawnEntity = new PacketBuilder.Entity(EntityTypes.ITEM_DISPLAY)
                .entityId(id)
                .location(new Vector3d(location.getX(), location.getY(), location.getZ()))
                .build();

        Quaternionf qf = nodeTemplate.getLeftRotation();
        Vector3f f = nodeTemplate.getTranslation();
        Vector3f scale = nodeTemplate.getScale();

        WrapperPlayServerEntityMetadata wrapperPlayServerEntityMetadata = new PacketBuilder.Update(id)
                .itemstack(PacketUtils.convertItemstack(new ItemStack(Material.ACACIA_BOAT)))

                .leftRotation(new Quaternion4f(qf.x, qf.y, qf.z, qf.w))
                .translation(new com.github.retrooper.packetevents.util.Vector3f(f.x, f.y, f.z))
                .scale(new com.github.retrooper.packetevents.util.Vector3f(scale.x, scale.y, scale.z))

                .build();

        PacketUtils.sendPacketToPlayers(serverSpawnEntity, list);
        PacketUtils.sendPacketToPlayers(wrapperPlayServerEntityMetadata, list);
    }

}
