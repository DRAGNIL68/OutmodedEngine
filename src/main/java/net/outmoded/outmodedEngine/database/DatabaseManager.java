package net.outmoded.outmodedEngine.database;

import java.util.concurrent.CopyOnWriteArrayList;

public class DatabaseManager {
    CopyOnWriteArrayList<ModelLoadListener> listeners = new CopyOnWriteArrayList<>();

    private DatabaseManager() {}

    private static class SingletonHelper {
        private static final DatabaseManager SINGLETON_INSTANCE = new DatabaseManager();
    }

    public static DatabaseManager getInstance() {
        return DatabaseManager.SingletonHelper.SINGLETON_INSTANCE;
    }


    public void registerListener(ModelLoadListener listener){
        listeners.add(listener);
    }

    private void triggerListeners(){
        listeners.forEach( listener -> {
            listener.event(null);
        });
    }
}
