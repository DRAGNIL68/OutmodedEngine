package net.outmoded.outmodedEngine.template;

import com.google.common.collect.ImmutableMap;
import net.outmoded.outmodedEngine.default_formats.blockbench.AnimationTemplate;
import net.outmoded.outmodedEngine.default_formats.blockbench.NodeTemplate;
import net.outmoded.outmodedEngine.default_formats.blockbench.VariantTemplate;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * make sure that state cannot be modified when implementing this
 */
public interface ModelTemplate {

    /**
     * this an immutable view
     */
    public @NotNull ImmutableMap<String, VariantTemplate> getVariants();

    /**
     * this an immutable view
     */
    public @NotNull ImmutableMap<String, AnimationTemplate> getAnimations();

    /**
     * this an immutable view
     */
    public @NotNull ImmutableMap<UUID, NodeTemplate> getNodes();

    /**
     * isPersistent decides if a model will be saved to disk
     * when chunk is unloaded.
     * true = saved | false = not saved
     */
    public boolean isPersistent();
}
