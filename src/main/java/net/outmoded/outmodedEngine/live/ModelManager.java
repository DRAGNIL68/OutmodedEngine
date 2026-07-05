package net.outmoded.outmodedEngine.live;

import net.outmoded.outmodedEngine.templates.ModelTemplate;

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

    public void spawnModel(UUID uuid, ModelTemplate modelTemplate){

        if (uuid == null || modelTemplate == null)
            throw new NullPointerException();

        // load model from template

        //modelHashMap.put(uuid, model);
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

}
