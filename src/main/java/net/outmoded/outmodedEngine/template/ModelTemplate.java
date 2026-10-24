package net.outmoded.outmodedEngine.template;

import com.google.common.collect.ImmutableMap;
import net.outmoded.outmodedEngine.defaultformats.blockbench.AnimationTemplate;
import net.outmoded.outmodedEngine.defaultformats.blockbench.NodeTemplate;
import net.outmoded.outmodedEngine.defaultformats.blockbench.VariantTemplate;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * make sure that state cannot be modified when implementing this
 */
public interface ModelTemplate {

    /**
     * this an immutable view
     */
    public @NotNull ImmutableMap<String, VariantTemplate> getVariantsMap();

    /**
     * this an immutable view
     */
    public @NotNull ImmutableMap<String, AnimationTemplate> getAnimationsMap();

    /**
     * this an immutable view
     */
    public @NotNull ImmutableMap<UUID, NodeTemplate> getNodesMap();

    /**
     * isPersistent decides if a model will be saved to disk
     * when chunk is unloaded.
     * true = saved | false = not saved
     */
    public boolean isPersistent();
}
