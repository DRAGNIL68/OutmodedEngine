package net.outmoded.outmodedEngine.live;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

import java.util.List;
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

    public @NotNull List<Node> getNodes();

    public @NotNull NamespacedKey getTemplateKey();

}
