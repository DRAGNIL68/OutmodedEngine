package net.outmoded.outmodedEngine.database;

/**
 * called when a model is loaded form save data
 */
// the reason this is not a bukkit even is I would like it to be platform-agnostic
public interface ModelLoadCallback {
    public void event(Object listener);
}
