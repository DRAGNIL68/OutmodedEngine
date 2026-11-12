package net.outmoded.outmodedEngine.resourcepack;

import net.kyori.adventure.resource.ResourcePackCallback;
import net.outmoded.outmodedEngine.Co;
import net.outmoded.outmodedEngine.OutmodedEngine;
import net.outmoded.outmodedEngine.packer.ResourcePack;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

public class ResourcePackManager {
    private final CopyOnWriteArrayList<Callback> callbacks = new CopyOnWriteArrayList<>();
    private final ResourcePack resourcePack = new CustomResourcePack();

    private ResourcePackManager() {}

    private static class SingletonHelper {
        private static final ResourcePackManager SINGLETON_INSTANCE = new ResourcePackManager();
    }

    public static ResourcePackManager getInstance() {
        return SingletonHelper.SINGLETON_INSTANCE;
    }



    /**
     * addition of content runs on main thread
     */
    public void generate() {
        resourcePack.clear(); // I would rather people don't register data to this before me

        callbacks.forEach(callback -> { callback.trigger(resourcePack); });

        resourcePack.generate(OutmodedEngine.getInstance().getDataFolder()+"/generated.zip", 9, true);

        resourcePack.clear(); // clears data from memory. Can be called early as ResourcePack#generate() makes a snapshot of the data in it.
    }

    /**
     * called when the pack is ready for content to be added to it
     */
    public void registerCallback(Callback callback){
        callbacks.add(callback);
    }
    public void unregisterCallback(Callback callback){
        callbacks.remove(callback);
    }

    public static interface Callback{
        public void trigger(ResourcePack resourcePack);
    }

    private static class CustomResourcePack extends ResourcePack{

        // overrides the log method so PaperMc does not complain
        @Override
        public void log(String string){
            Co.warn(string);
        }
    }
}
