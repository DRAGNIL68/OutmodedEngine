package net.outmoded.outmodedEngine.database;

import java.util.concurrent.CopyOnWriteArrayList;

public class DatabaseManager {
    CopyOnWriteArrayList<ModelLoadCallback> listeners = new CopyOnWriteArrayList<>();

    private DatabaseManager() {}

    private static class SingletonHelper {
        private static final DatabaseManager SINGLETON_INSTANCE = new DatabaseManager();
    }

    public static DatabaseManager getInstance() {
        return DatabaseManager.SingletonHelper.SINGLETON_INSTANCE;
    }


    /**
     * used to register callbacks for model loading
     * @param listener
     */
    public void callback(ModelLoadCallback listener){
        listeners.add(listener);
    }

    private void triggerListeners(){
        listeners.forEach( listener -> {
            listener.event(null);
        });
    }
}
