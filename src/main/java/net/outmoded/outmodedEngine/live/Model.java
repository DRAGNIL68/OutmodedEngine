package net.outmoded.outmodedEngine.live;


import com.google.common.collect.ImmutableList;
import net.outmoded.outmodedEngine.impl.DefaultRenderer;
import net.outmoded.outmodedEngine.annotations.AsyncSafe;
import net.outmoded.outmodedEngine.annotations.NotAsyncSafe;
import net.outmoded.outmodedEngine.templates.ModelTemplate;
import net.outmoded.outmodedEngine.templates.ModelTemplateManager;
import net.outmoded.outmodedEngine.templates.NodeTemplate;
import net.outmoded.outmodedEngine.templates.VariantTemplate;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.K;
import org.jetbrains.annotations.NotNull;


import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BiConsumer;

public class Model {
    private final UUID uuid;
    private volatile Location location;
    private final NamespacedKey modelTemplateKey;
    private volatile BoneRenderer boneRenderer;

    // TODO: not finished
    private final ConcurrentHashMap<UUID, Node> nodes = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Integer> animations = new ConcurrentHashMap<>(); // animation id : position

    volatile String currentVariant = "default"; //TODO finish

    private CopyOnWriteArrayList<Player> hiddenList = new CopyOnWriteArrayList<>(); // if player is in list they cant see this model

    private Model(Builder builder, UUID uuid) {
        this.uuid = uuid;
        this.modelTemplateKey = builder.modelTemplateKey;
        this.location = builder.location;

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

    /**
     * can be used async just don't touch the world stuff
     */
    @AsyncSafe
    public Location getLocation() {
        return location.clone();
    }


    /**
     * register a model to the model manager
     */
    @NotAsyncSafe
    public void register() throws RuntimeException{

        if (ModelManager.getInstance().hasModel(uuid)){
            throw new RuntimeException("model with same uuid already exists");
        }

        ModelManager.getInstance().registerModel(this);
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

        for (Map.Entry<UUID, NodeTemplate> entry : modelTemplate.getNodes().entrySet()) {
            Node node = nodes.get(entry.getKey());
            boneRenderer.process(entry.getValue(), node, this); // not sure if I like this
        }
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

        /**
         * should be run on main thread
         */
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
        public Model build() throws IllegalArgumentException{
            if (modelTemplateKey == null)
                throw new IllegalArgumentException("modelTemplateKey is null");

            if (location == null) { //TODO: this is not definitive
                throw new IllegalArgumentException("model location cannot be null!");

            }

            Model model;
            if (uuid == null) model = new Model(this, UUID.randomUUID());
            else model = new Model(this, uuid);

            if (boneRenderer == null)
                model.boneRenderer = new DefaultRenderer(model);

            return model;
        }
    }
}
