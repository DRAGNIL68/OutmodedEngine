package net.outmoded.outmodedEngine.live;

import com.google.common.collect.ImmutableList;
import net.outmoded.outmodedEngine.interfaces.BoneRenderer;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * handles all the {@link net.outmoded.outmodedEngine.interfaces.BoneRenderer} that your project may have
 */
public final class RenderHandler {
    private final CopyOnWriteArrayList<BoneRenderer> boneRenderers = new CopyOnWriteArrayList<>();

    public void registerBoneRenderer(BoneRenderer boneRenderer) {
        boneRenderers.add(boneRenderer);
    }

    public void unregisterBoneRenderer(BoneRenderer boneRenderer) {
        boneRenderers.remove(boneRenderer);
    }

    public void clearBoneRenderers() {
        boneRenderers.clear();
    }

    public ImmutableList<BoneRenderer> getBoneRenderers() {
        return ImmutableList.copyOf(boneRenderers);
    }

    /**
     * renders all the bones in a model
     */
    public void render(){

    }

}
