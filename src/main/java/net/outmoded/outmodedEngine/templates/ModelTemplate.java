package net.outmoded.outmodedEngine.templates;

import com.github.retrooper.packetevents.protocol.item.ItemStack;
import com.google.common.collect.ImmutableList;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ModelTemplate {
    private final ConcurrentHashMap<String, VariantTemplate> variants = new ConcurrentHashMap<>(); // Variant:PacketItemStack

    private ModelTemplate(Builder builder){
        // do something
    }


    public static class Builder {
        private boolean isPersistent = true;
        private String json = null;

        public Builder persistence(boolean b){
            isPersistent = b;
            return this;
        }

        //this.variants = ImmutableList.copyOf(variants);
        public Builder fromJson(String json){
            this.json = json;
            return this;
        }

        public ModelTemplate build() {
            return new ModelTemplate(this);
        }
    }

}
