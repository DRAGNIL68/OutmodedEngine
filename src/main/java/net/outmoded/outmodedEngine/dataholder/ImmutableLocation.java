package net.outmoded.outmodedEngine.dataholder;

import net.outmoded.outmodedEngine.annotations.AsyncSafe;
import net.outmoded.outmodedEngine.annotations.NotAsyncSafe;
import org.bukkit.Location;


// do I need this? probably not is Location async safe? fuck knows
/**
 * simple class to sore a copy of a location that is async safe
 */
@AsyncSafe
public record ImmutableLocation(String worldName, double x, double y, double z, float pitch, float yaw) {

    /**
     * run this on the main thread you are reading from the bukkit api
     */
    @NotAsyncSafe
    public static ImmutableLocation fromLocation(Location location) {
        return new ImmutableLocation(
                location.getWorld().getName(),
                location.getX(),
                location.getY(),
                location.getZ(),
                location.getPitch(),
                location.getYaw());
    }

}


