package net.outmoded.outmodedEngine.database;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class DatabaseManager {
    CopyOnWriteArrayList<ModelLoadCallback> callbacks = new CopyOnWriteArrayList<>();
    private DatabaseManager() {}

    private static class SingletonHelper {
        private static final DatabaseManager SINGLETON_INSTANCE = new DatabaseManager();
    }

    public static DatabaseManager getInstance() {
        return DatabaseManager.SingletonHelper.SINGLETON_INSTANCE;
    }


    /**
     * used to register callbacks for model loading
     * @param callback
     */
    public void callback(ModelLoadCallback callback){
        callbacks.add(callback);
    }

    public void removeCallback(ModelLoadCallback callback){
        callbacks.remove(callback);
    }

    private void triggerListeners(){
        Iterator<ModelLoadCallback> snapshot = callbacks.iterator();

        snapshot.forEachRemaining(callback -> {
            callback.event(null);
        });
    }
}
