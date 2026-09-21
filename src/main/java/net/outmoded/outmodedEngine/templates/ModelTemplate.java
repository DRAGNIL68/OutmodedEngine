package net.outmoded.outmodedEngine.templates;

import com.google.common.collect.ImmutableMap;
import net.outmoded.outmodedEngine.OutmodedEngine;
import net.outmoded.outmodedEngine.live.ModelManager;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

// ALL data in this class should be immutable.
public class ModelTemplate {
    private final boolean isPersistent;
    private final ConcurrentHashMap<UUID, NodeTemplate> nodes = new ConcurrentHashMap<>();

    private final ConcurrentHashMap<String, VariantTemplate> variants = new ConcurrentHashMap<>();

    private final ConcurrentHashMap<String, AnimationTemplate> animations = new ConcurrentHashMap<>();

    private ModelTemplate(Builder builder){
        isPersistent = builder.isPersistent;

        //TODO: this is where the JSON should be converted into data.
        // this is done INTERNALLY so hou cant fuck up anything async
        // and yes I do have APS (async paranoia syndrome)


    }



    /**
     * this an immutable view
     */
    public ImmutableMap<String, VariantTemplate> getVariants(){
        return ImmutableMap.copyOf(variants);
    }

    /**
     * this an immutable view
     */
    public ImmutableMap<String, AnimationTemplate> getAnimations(){
        return ImmutableMap.copyOf(animations);
    }

    /**
     * this an immutable view
     */
    public ImmutableMap<UUID, NodeTemplate> getNodes(){
        return ImmutableMap.copyOf(nodes);
    }

    public boolean isPersistent() {
        return isPersistent;
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
