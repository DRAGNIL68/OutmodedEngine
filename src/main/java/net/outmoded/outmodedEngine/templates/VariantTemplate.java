package net.outmoded.outmodedEngine.templates;

import com.github.retrooper.packetevents.protocol.item.ItemStack;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class VariantTemplate {
    private final ConcurrentHashMap<UUID, ItemStack> variantMap; // node uuid:packet itemstack

    private VariantTemplate(Builder builder){
        this.variantMap = new ConcurrentHashMap<UUID, ItemStack>(builder.variantMap);
    }

    public Collection<UUID> getNodes(){
        return variantMap.keySet();
    }

    public ItemStack getNodeVariant(UUID nodeUuid){
        return variantMap.get(nodeUuid).copy();
    }

    public static class Builder{
        private final ConcurrentHashMap<UUID, ItemStack> variantMap = new ConcurrentHashMap<>(); // node uuid:packet itemstack

        public Builder(){}

        public Builder addNodeVariant(UUID nodeUuid, ItemStack itemStack){
            ItemStack stack = itemStack.copy();
            return this;
        }

        public VariantTemplate build(){
            return new VariantTemplate(this);
        }

    }

}
