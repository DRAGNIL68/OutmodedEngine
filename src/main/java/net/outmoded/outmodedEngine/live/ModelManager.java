package net.outmoded.outmodedEngine.live;

import net.outmoded.outmodedEngine.OutmodedEngine;
import net.outmoded.outmodedEngine.default_formats.blockbench.live.BBModel;
import org.apache.logging.log4j.util.InternalApi;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

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

    public void init(){
        new BukkitRunnable() {
            @Override
            public void run() {

                /*
                    TODO: IMPORTANT: fix loop sync
                    currently the loop will just keep getting executed even if the last tick did not finish.
                    This can lead to all sorts of strange bugs that will haunt me if not fixed.

                    My fix idea, I am going to try and use a dynamic tick system that can execute the animation loop multiple times per tick
                    if needed to keep sync with the server. <- probably going to use this one

                    Or i could just add skipped ticks to the current frame time of every model,
                    even if that would probably cause problems with custom BoneRenders
                 */
                ModelManager.getInstance().tickAllModels();

            }

        }.runTaskTimerAsynchronously(OutmodedEngine.getInstance(), 0, 1);

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
    public void queueFreeModel(UUID uuid){
        queue.add(uuid);
    }

    private void queueFree(){
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
