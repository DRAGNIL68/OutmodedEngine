package net.outmoded.outmodedEngine.live;


import net.outmoded.outmodedEngine.interfaces.AnimationController;
import net.outmoded.outmodedEngine.DefaultController;
import net.outmoded.outmodedEngine.annotations.AsyncSafe;
import net.outmoded.outmodedEngine.annotations.NotAsyncSafe;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;


import java.util.UUID;

public class Model {
    private final UUID uuid;
    private volatile Location location;
    private final NamespacedKey modelTemplateKey;
    private volatile AnimationController animationController;

    volatile String currentVariant = "default"; //TODO finish

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

    public void tick(){
        animationController.tick();
    }

    public UUID getUuid() {return uuid;}

    public NamespacedKey getModelTemplateKey() {return modelTemplateKey;}

    @AsyncSafe
    public static class Builder {
        private AnimationController animationController = null;
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

        public Builder overrideAnimationController(AnimationController animationController){
            this.animationController = animationController;
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

            if (animationController == null)
                model.animationController = new DefaultController(model);

            return model;
        }
    }
}
