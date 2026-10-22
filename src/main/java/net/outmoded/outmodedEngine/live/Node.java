package net.outmoded.outmodedEngine.live;

import com.github.retrooper.packetevents.protocol.item.ItemStack;

import java.util.UUID;

/**
 * stores data on all entity displays that make up a model
 */
public class Node {
    private final UUID uuid;
    // live id
    private final int id;
    // visible players

    public Node(int id, UUID uuid){
        this.id = id;
        this.uuid = uuid;
    }
    public int getEntityId(){ return id; } // temp
    public UUID getNodeUuid() { return uuid; }


    ItemStack currentVariant;
}
