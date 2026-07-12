package net.outmoded.outmodedEngine.templates;

import com.github.retrooper.packetevents.protocol.item.ItemStack;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class VariantTemplate {
    ConcurrentHashMap<UUID, ItemStack> variantMap = new ConcurrentHashMap<>(); // node uuid:packet itemstack

}
