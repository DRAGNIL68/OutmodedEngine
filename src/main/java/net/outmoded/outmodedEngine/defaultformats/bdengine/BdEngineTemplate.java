package net.outmoded.outmodedEngine.defaultformats.bdengine;

import com.google.common.collect.ImmutableMap;
import net.outmoded.outmodedEngine.defaultformats.blockbench.AnimationTemplate;
import net.outmoded.outmodedEngine.defaultformats.blockbench.NodeTemplate;
import net.outmoded.outmodedEngine.defaultformats.blockbench.VariantTemplate;
import net.outmoded.outmodedEngine.template.ModelTemplate;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class BdEngineTemplate implements ModelTemplate {
    @Override
    public @NotNull ImmutableMap<String, VariantTemplate> getVariantsMap() {
        return null;
    }

    @Override
    public @NotNull ImmutableMap<String, AnimationTemplate> getAnimationsMap() {
        return null;
    }

    @Override
    public @NotNull ImmutableMap<UUID, NodeTemplate> getNodesMap() {
        return null;
    }

    @Override
    public boolean isPersistent() {
        return false;
    }
}
