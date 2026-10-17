package net.outmoded.outmodedEngine.default_formats.blockbench.live;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.outmoded.outmodedEngine.Co;
import net.outmoded.outmodedEngine.Config;
import net.outmoded.outmodedEngine.annotations.AsyncSafe;
import net.outmoded.outmodedEngine.default_formats.blockbench.DefaultRenderer;
import net.outmoded.outmodedEngine.live.*;
import net.outmoded.outmodedEngine.template.ModelTemplate;
import net.outmoded.outmodedEngine.template.ModelTemplateManager;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class BBModel implements Model{
    private final UUID uuid;
    public volatile Location location;
    private final NamespacedKey modelTemplateKey;
    private final RenderHandler renderHandler = new RenderHandler();

    // TODO: not finished
    private final ConcurrentHashMap<UUID, Node> nodes = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Integer> animations = new ConcurrentHashMap<>(); // animation id : position

    volatile String currentVariant = "default"; //TODO finish

    private CopyOnWriteArrayList<Player> hiddenList = new CopyOnWriteArrayList<>(); // if player is in list they cant see this model

    private BBModel(Builder builder, UUID uuid) {
        this.uuid = uuid;
        this.modelTemplateKey = builder.modelTemplateKey;
        this.location = builder.location;
        renderHandler.addBoneRenderer(new DefaultRenderer());
    }

    private BBModel(@NotNull UUID uuid, @NotNull NamespacedKey modelTemplate, @NotNull Location location) {
        this.uuid = uuid;
        this.modelTemplateKey = modelTemplate;
        this.location = location;

        if (!ModelTemplateManager.getInstance().hasModelTemplate(modelTemplateKey)){
            throw new IllegalArgumentException("modelTemplate does not exist");
        }
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
        return null;
    }

    @Override
    public @NotNull NamespacedKey getTemplateKey() {
        return modelTemplateKey;
    }

    @Override
    public boolean isActive() {
        return true;
    }


    /**
     * can be used async just don't touch the world stuff
     */
    @AsyncSafe
    public @NonNull Location getLocation() {
        return location.clone();
    }


    private void register() throws RuntimeException{
        boolean b = ModelManager.getInstance().registerModelIfAbsent(this);

        if (Config.debugMode()){
            Co.warn("cant register model with same uuid: "+getUuid());
        }

    }

    // this will run async
    public void tick(){


        ModelTemplate modelTemplate =
                ModelTemplateManager.getInstance().
                        getModelTemplate(modelTemplateKey); // should never be null

//        modelTemplate.getNodes().forEach(new BiConsumer<String, NodeTemplate>() { // debug
//            @Override
//            public void accept(UUID uuid, NodeTemplate nodeTemplate) {
//                boneRenderer.process(nodeTemplate);
//            }
//        });

        renderHandler.render(this);
//        for (Map.Entry<UUID, NodeTemplate> entry : modelTemplate.getNodes().entrySet()) {
//            Node node = nodes.get(entry.getKey());
//
//            boneRenderer.process(entry.getValue(), node, this); // not sure if I like this
//        }
    }

    /**
     * returns a list of all player who cant see this
     * @return
     */
    public List<Player> getHiddenFrom(){
        return ImmutableList.copyOf(hiddenList);
    }

    public void removePlayer(Player player){ hiddenList.remove(player); }
    public void addPlayer(Player player){ hiddenList.add(player); }

    public UUID getUuid() {return uuid;}

    public NamespacedKey getModelTemplateKey() {return modelTemplateKey;}

    @AsyncSafe
    public static class Builder {
        private BoneRenderer boneRenderer = null;
        private UUID uuid = null;
        private NamespacedKey modelTemplateKey = null;
        private Location location = null;


        public Builder(@NotNull NamespacedKey modelTemplateKey, @NotNull Location location){
            this.modelTemplateKey = modelTemplateKey;
            this.location = location;
        }

        /**
         * enforce a specific uuid
         * @param uuid
         * @return
         */
        public Builder enforceUuid(UUID uuid){
            this.uuid = uuid;
            return this;
        }

        public Builder overrideAnimationController(BoneRenderer boneRenderer){
            this.boneRenderer = boneRenderer;
            return this;
        }

        //
        public BBModel build() throws IllegalArgumentException{
            if (modelTemplateKey == null)
                throw new IllegalArgumentException("modelTemplateKey is null");

            if (location == null) { //TODO: this is not definitive
                throw new IllegalArgumentException("model location cannot be null!");
            }

            BBModel model;
            if (uuid == null) model = new BBModel(this, UUID.randomUUID());
            else model = new BBModel(this, uuid);

            return model;
        }
    }
}

