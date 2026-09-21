package net.outmoded.outmodedEngine.live;

import net.outmoded.outmodedEngine.OutmodedEngine;
import org.apache.logging.log4j.util.InternalApi;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class ModelManager {
    private final ConcurrentHashMap<UUID, Model> modelHashMap = new ConcurrentHashMap<>();

    private ModelManager() {
        init();
    }

    private static class SingletonHelper {
        private static final ModelManager SINGLETON_INSTANCE = new ModelManager();
    }

    public static ModelManager getInstance() {
        return ModelManager.SingletonHelper.SINGLETON_INSTANCE;
    }

    public void init(){
        new BukkitRunnable() {
            @Override
            public void run() {

                ModelManager.getInstance().tickAllModels();

            }

        }.runTaskTimerAsynchronously(OutmodedEngine.getInstance(), 0, 1);

        new BukkitRunnable() {
            @Override
            public void run() {

//                for (Model model : ModelManager.getInstance().get)

            }

        }.runTaskTimer(OutmodedEngine.getInstance(), 6000, 6000); // 6000 ticks = 5m

    }

    public void registerModel(@NotNull("model cannot be null!") Model model) throws IllegalArgumentException{

        modelHashMap.put(model.getUuid(), model);
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
        return modelHashMap.get(uuid);
    }

    public ConcurrentHashMap<UUID, Model> getModels(){
        return new ConcurrentHashMap<>(modelHashMap);
    }

    /**
     * this should be run async
     */
    @InternalApi
    public void tickAllModels(){
        for (Model model : modelHashMap.values()){
            model.tick();
        }
    }

}
