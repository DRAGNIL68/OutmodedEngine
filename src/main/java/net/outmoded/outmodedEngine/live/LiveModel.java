package net.outmoded.outmodedEngine.live;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface LiveModel {

    /**
     * should call bone renderer here
     */
    public void tick();

    public @NotNull UUID getUuid();

    public @NotNull Location getLocation();

    public void setLocation(@NotNull Location location);

    public @NotNull RenderHandler getRenderHandler();

    //public Node[] getNodes();
}
