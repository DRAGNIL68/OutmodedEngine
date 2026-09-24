package net.outmoded.outmodedEngine.templates;

import com.google.common.collect.ImmutableMap;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * make sure that state cannot be modified when implementing this
 */
public interface ModelTemplateInterface {

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
