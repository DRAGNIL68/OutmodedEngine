package net.outmoded.outmodedEngine.default_formats.blockbench.live;

import com.github.retrooper.packetevents.protocol.entity.type.EntityTypes;
import com.github.retrooper.packetevents.util.Quaternion4f;
import com.github.retrooper.packetevents.util.Vector3d;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerEntityMetadata;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerSpawnEntity;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.outmoded.outmodedEngine.Co;
import net.outmoded.outmodedEngine.Config;
import net.outmoded.outmodedEngine.annotations.AsyncSafe;
import net.outmoded.outmodedEngine.default_formats.blockbench.BBModelTemplate;
import net.outmoded.outmodedEngine.default_formats.blockbench.DefaultRenderer;
import net.outmoded.outmodedEngine.live.*;
import net.outmoded.outmodedEngine.packets.PacketBuilder;
import net.outmoded.outmodedEngine.packets.PacketUtils;
import net.outmoded.outmodedEngine.template.ModelTemplate;
import net.outmoded.outmodedEngine.template.ModelTemplateManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

// models should have 2 states in existence and not
// if it exists its nodes should exist
// if not well it does not exist, well it does not matter
public class BBModel implements Model{
    private final UUID uuid;
    public volatile Location location;
    private final NamespacedKey modelTemplateKey;
    private final RenderHandler renderHandler;

    // TODO: not finished
    private final ConcurrentHashMap<UUID, Node> nodes = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Animation> animations = new ConcurrentHashMap<>(); // animation id : position

    volatile String currentVariant = "default"; //TODO finish

    private CopyOnWriteArrayList<Player> hiddenList = new CopyOnWriteArrayList<>(); // if player is in list they cant see this model

    public BBModel(@NotNull UUID uuid, @NotNull NamespacedKey modelTemplate, @NotNull Location location) {
        this.uuid = uuid;
        this.modelTemplateKey = modelTemplate;
        this.location = location;
        this.renderHandler = new RenderHandler();
        this.renderHandler.addBoneRenderer(new DefaultRenderer());

        if (!ModelTemplateManager.getInstance().hasModelTemplate(modelTemplateKey)){
            throw new IllegalArgumentException("modelTemplate does not exist");
        }

        loadTemplate(modelTemplate);
    }

    public void loadTemplate(NamespacedKey modelTemplate) {
        ModelTemplate template = ModelTemplateManager.getInstance().getModelTemplate(modelTemplate);

        if (template == null) {
            if (ModelManager.getInstance().hasModel(uuid))
                ModelManager.getInstance().queueFree(uuid);

            throw new IllegalArgumentException("ModelTemplate has to exist");
        }

        if (!(template instanceof BBModelTemplate bbTemplate)) {
            throw new IllegalArgumentException("ModelTemplate not instance of BBModelTemplate");
        }

        animations.clear();

        bbTemplate.getNodesMap().forEach((uuid, nodeTemplate) -> {


            List<Player> list = new ArrayList<>(Bukkit.getOnlinePlayers());

            int id = PacketUtils.getNextId();
            WrapperPlayServerSpawnEntity serverSpawnEntity = new PacketBuilder.Entity(EntityTypes.ITEM_DISPLAY)
                    .entityId(id)
                    .location(new Vector3d(location.getX(), location.getY(), location.getZ()))
                    .build();

            Quaternionf qf = nodeTemplate.getLeftRotation();
            Vector3f f = nodeTemplate.getTranslation();
            Vector3f scale = nodeTemplate.getScale();

            WrapperPlayServerEntityMetadata wrapperPlayServerEntityMetadata = new PacketBuilder.Update(id)
                    .itemstack(PacketUtils.convertItemstack(new ItemStack(Material.ACACIA_BOAT)))
                    .leftRotation(new Quaternion4f(qf.x, qf.y, qf.z, qf.w))
                    .translation(new com.github.retrooper.packetevents.util.Vector3f(f.x, f.y, f.z))
                    .scale(new com.github.retrooper.packetevents.util.Vector3f(scale.x, scale.y, scale.z))
                    .build();

            PacketUtils.sendPacketToPlayers(serverSpawnEntity, list);
            PacketUtils.sendPacketToPlayers(wrapperPlayServerEntityMetadata, list);

            nodes.put(uuid, new Node(id, uuid));
        });
    }


    public void setCurrentVariant(@NotNull String currentVariant) {
        this.currentVariant = currentVariant;
    }

    @AsyncSafe
    public String getCurrentVariant() {
        return currentVariant;
    }

    @AsyncSafe
    public void setLocation(@NotNull Location location) {
        this.location = location.clone();
    }

    @Override
    public @NotNull RenderHandler getRenderHandler() {
        return renderHandler;
    }

    @Override
    public @NotNull ImmutableMap<UUID, Node> getNodes() {
        return ImmutableMap.copyOf(nodes);
    }

    @Override
    public @NotNull NamespacedKey getTemplateKey() {
        return modelTemplateKey;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void setActive(boolean b) {
        // do stuff
    }

    /**
     * called before the model is destroyed
     * deals with clearing entity display packets
     */
    @Override
    public void spawn(Location location) {

    }

    @Override
    public void remove() {

    }

    @Override
    public void setPersistence() {

    }

    @Override
    public boolean isPersistent() {
        return false;
    }


    /**
     * can be used async just don't touch the world stuff
     */
    @AsyncSafe
    public @NonNull Location getLocation() {
        return location.clone();
    }


    public void register() throws RuntimeException{
        boolean b = ModelManager.getInstance().registerModelIfAbsent(this);
        if (b)
            Co.info("registered model: "+modelTemplateKey.asString()+" uuid: "+uuid);

        if (Config.debugMode()){
            Co.warn("cant register model with same uuid: "+getUuid());
        }

    }

    /**
     * returns a list of all player who cant see this
     */
    public List<Player> getHiddenFrom(){
        return ImmutableList.copyOf(hiddenList);
    }

    public void removePlayer(Player player){ hiddenList.remove(player); }
    public void addPlayer(Player player){ hiddenList.add(player); }

    public @NonNull UUID getUuid() {return uuid;}

    public NamespacedKey getModelTemplateKey() {return modelTemplateKey;}

}

