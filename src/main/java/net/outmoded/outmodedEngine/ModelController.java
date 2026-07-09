package net.outmoded.outmodedEngine;

public interface ModelController {
    /**
     * called when the model is created
     */
    public void onAction(ModelAction modelAction);



    public enum ModelAction {

        LOADED,
        DELETED
    }
}
