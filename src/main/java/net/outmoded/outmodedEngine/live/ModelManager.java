package net.outmoded.outmodedEngine.live;

import net.outmoded.outmodedEngine.OutmodedEngine;
import net.outmoded.outmodedEngine.templates.ModelTemplate;
import org.apache.logging.log4j.util.InternalApi;
import org.bukkit.NamespacedKey;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ModelManager {
    private final ConcurrentHashMap<UUID, Model> modelHashMap = new ConcurrentHashMap<>();

    private ModelManager() {}

    private static class SingletonHelper {
        private static final ModelManager SINGLETON_INSTANCE = new ModelManager();
    }


    public static ModelManager getInstance() {
        return ModelManager.SingletonHelper.SINGLETON_INSTANCE;
    }

    public void registerModel(Model model) throws IllegalArgumentException{

        if (model == null)
            throw new IllegalArgumentException("model cannot be null!");

        modelHashMap.put(model.getUuid(), model);
        OutmodedEngine.getInstance().getLogger().warning("registering new model");
    }

    public boolean registerModelIfAbsent(Model model) throws IllegalArgumentException{
        if (model == null)
            throw new IllegalArgumentException("model cannot be null!");

        return modelHashMap.putIfAbsent(model.getUuid(), model) != null;
    }






    public void removeModel(UUID uuid){
        modelHashMap.remove(uuid);
    }

    public boolean hasModel(UUID uuid){
        return modelHashMap.containsKey(uuid);
    }

    public Model getModel(UUID uuid){
        if (uuid == null)
            return null;

        return modelHashMap.get(uuid);

    }

    /**
     * this should be run async
     */
    @InternalApi
    public synchronized void tickAllModels(){
        for (Model model : modelHashMap.values()){
            model.tick();
        }
    }

}
