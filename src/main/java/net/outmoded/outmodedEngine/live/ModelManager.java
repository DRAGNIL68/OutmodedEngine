package net.outmoded.outmodedEngine.live;

import net.outmoded.outmodedEngine.OutmodedEngine;
import net.outmoded.outmodedEngine.defaultFormats.blockbench.live.BBModel;
import org.apache.logging.log4j.util.InternalApi;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public final class ModelManager {
    private final ConcurrentHashMap<UUID, BBModel> modelHashMap = new ConcurrentHashMap<>();

    private ModelManager() {
        init();
    }

    private static class SingletonHelper {
        private static final ModelManager SINGLETON_INSTANCE = new ModelManager();
    }

    public static ModelManager getInstance() {
        return ModelManager.SingletonHelper.SINGLETON_INSTANCE;
    }

    private final AtomicBoolean isRunning = new AtomicBoolean(false);

    public void init(){


        new BukkitRunnable() {
            @Override
            public void run() {
                if (!isRunning.get()){
                    new TickRunnable(isRunning).runTaskAsynchronously(OutmodedEngine.getInstance());
                }
            }

        }.runTaskTimer(OutmodedEngine.getInstance(), 0, 1);

        new BukkitRunnable() {
            @Override
            public void run() {

//                for (BBModel model : ModelManager.getInstance().get)

            }

        }.runTaskTimer(OutmodedEngine.getInstance(), 6000, 6000); // 6000 ticks = 5m

    }

    public void registerModel(@NotNull("model cannot be null") BBModel model){

        modelHashMap.put(model.getUuid(), model);
    }

    public boolean registerModelIfAbsent(@NotNull("model cannot be null") BBModel model){
        return modelHashMap.putIfAbsent(model.getUuid(), model) != null;
    }

    private ConcurrentLinkedQueue<UUID> queue = new ConcurrentLinkedQueue<>();

    /**
     * removes model at the end of animation tick
     */
    public void queueFree(UUID uuid){
        queue.add(uuid);
    }

    /**
     * adds a model to a queue to be removed
     */
    private void queueFreeExecute(){
        while (queue.peek() != null){
            modelHashMap.remove(queue.poll()); // removes uuid from hashmap
        }
    }


    public boolean hasModel(UUID uuid){
        return modelHashMap.containsKey(uuid);
    }

    public BBModel getModel(UUID uuid){
        return modelHashMap.get(uuid);
    }

    public ConcurrentHashMap<UUID, BBModel> getModels(){
        return new ConcurrentHashMap<>(modelHashMap);
    }

    /**
     * this should be run async
     */
    @InternalApi
    public void tickAllModels(){
        for (Model model : modelHashMap.values()){
            model.getRenderHandler().render(model);
        }
    }

}
