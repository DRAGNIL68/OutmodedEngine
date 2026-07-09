package net.outmoded.outmodedEngine.live;


import net.outmoded.outmodedEngine.AnimationController;
import net.outmoded.outmodedEngine.templates.ModelTemplate;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;


import javax.annotation.concurrent.NotThreadSafe;
import java.util.UUID;

@NotThreadSafe
public class Model {
    private final UUID uuid;
    private Location location;
    private final NamespacedKey modelTemplateKey;


    private Model(Builder builder) {
        this.uuid = builder.uuid;
        this.modelTemplateKey = builder.modelTemplateKey;
        this.location = builder.location;
    }

    public UUID getUuid() {return uuid;}

    public NamespacedKey getModelTemplateKey() {return modelTemplateKey;}


    public static class Builder {
        boolean register = true;
        AnimationController animationController = null;
        UUID uuid = null;
        NamespacedKey modelTemplateKey = null;
        Location location = null;

        public Builder(@NotNull NamespacedKey modelTemplateKey){
            this.modelTemplateKey = modelTemplateKey;
        }

        /**
         * enforce a specific uuid, will fail if a model with this uuid exists
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

        /**
         * if not set will place the model at 0,0,0 in the world returned by
         * Bukkit.getWorlds().getFirst()
         * @param location
         * @return
         */
        public Builder location(Location location){
            this.location = location;
            return this;
        }

        public Model build() {
            if (modelTemplateKey == null)
                throw new IllegalArgumentException("modelTemplateKey is null");

            if (uuid == null)
                uuid = UUID.randomUUID();
            else if (ModelManager.getInstance().hasModel(uuid)){
                throw new IllegalArgumentException("model with same uuid already exists");
            }

            if (location == null) { //TODO: this is not definitive
                World world = Bukkit.getWorlds().getFirst();
                location = new Location(world, 0 , 0, 0);

            }


            Model model = new Model(this);

            if (!register)
                ModelManager.getInstance().registerModel(model);


            return model;
        }
    }




}
