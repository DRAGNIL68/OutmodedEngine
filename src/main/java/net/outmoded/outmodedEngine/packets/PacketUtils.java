package net.outmoded.outmodedEngine.packets;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.protocol.item.ItemStack;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.wrapper.PacketWrapper;
import io.github.retrooper.packetevents.util.SpigotConversionUtil;
import io.github.retrooper.packetevents.util.SpigotReflectionUtil;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PacketUtils {

    private PacketUtils(){}

    public static int getNextId(){
        return SpigotReflectionUtil.generateEntityId();
    }

    /**
     * should run async
     */
    public static void sendPacketToPlayers(PacketWrapper<?> packetWrapper, List<Player> players){
        List<Player> copy = new ArrayList<>(players);
        for (Player player : copy){
            if (player == null)
                continue;

            User user = PacketEvents.getAPI().getPlayerManager().getUser(player);

            if (user == null)
                continue;

            user.sendPacket(packetWrapper);
        }
    }

    public static ItemStack convertItemstack(org.bukkit.inventory.ItemStack itemStack){
        return SpigotConversionUtil.fromBukkitItemStack(itemStack);

    }
}
