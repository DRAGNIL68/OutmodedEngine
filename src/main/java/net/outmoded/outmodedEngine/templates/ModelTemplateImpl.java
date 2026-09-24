package net.outmoded.outmodedEngine.templates;

import com.google.common.collect.ImmutableMap;
import org.jspecify.annotations.NonNull;

import java.util.UUID;

public class ModelTemplateImpl implements ModelTemplateInterface{


    @Override
    public @NonNull ImmutableMap<String, VariantTemplate> getVariants() {
        return null;
    }

    @Override
    public @NonNull ImmutableMap<String, AnimationTemplate> getAnimations() {
        return null;
    }

    @Override
    public @NonNull ImmutableMap<UUID, NodeTemplate> getNodes() {
        return null;
    }

    @Override
    public boolean isPersistent() {
        return false;
    }
}
