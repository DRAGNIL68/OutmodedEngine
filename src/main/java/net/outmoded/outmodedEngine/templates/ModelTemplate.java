package net.outmoded.outmodedEngine.templates;

import com.google.common.collect.ImmutableMap;
import org.jspecify.annotations.NonNull;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

// ALL data in this class should be immutable.
public class ModelTemplate implements ModelTemplateInterface{
    private final boolean isPersistent;
    private final ConcurrentHashMap<UUID, NodeTemplate> nodes = new ConcurrentHashMap<>();

    private final ConcurrentHashMap<String, VariantTemplate> variants = new ConcurrentHashMap<>();

    private final ConcurrentHashMap<String, AnimationTemplate> animations = new ConcurrentHashMap<>();

    public ModelTemplate(boolean isPersistent, String json){
        this.isPersistent = isPersistent;

        //TODO: this is where the JSON should be converted into data.
        // this is done INTERNALLY so hou cant fuck up anything async
        // and yes I do have APS (async paranoia syndrome)
    }

    /**
     * this an immutable view
     */
    public @NonNull ImmutableMap<String, VariantTemplate> getVariants(){
        return ImmutableMap.copyOf(variants);
    }

    /**
     * this an immutable view
     */
    public @NonNull ImmutableMap<String, AnimationTemplate> getAnimations(){
        return ImmutableMap.copyOf(animations);
    }

    /**
     * this an immutable view
     */
    public @NonNull ImmutableMap<UUID, NodeTemplate> getNodes(){
        return ImmutableMap.copyOf(nodes);
    }

    public boolean isPersistent() {
        return isPersistent;
    }
}
