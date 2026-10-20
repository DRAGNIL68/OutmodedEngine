package net.outmoded.outmodedEngine.live;

import com.google.common.collect.ImmutableMap;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface Model {

    /**
     * should call bone renderer here
     */
    public void tick();

    public @NotNull UUID getUuid();

    public @NotNull Location getLocation();

    public void setLocation(@NotNull Location location);

    public @NotNull RenderHandler getRenderHandler();

    public @NotNull ImmutableMap<UUID, Node> getNodes();

    public @NotNull NamespacedKey getTemplateKey();

    /**
     * Is used to decide if a model should be ticked
     */
    public boolean isActive();

    /**
     * creates the model in the world
     * and also deals with spawning in packet displays etc
     */
    public void spawn(Location location);

    /**
     * removes the
     */
    public void remove();

    public void setPersistence();

    public boolean isPersistent();

}
