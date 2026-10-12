package net.outmoded.outmodedEngine.live;

import com.google.common.collect.ImmutableList;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * handles all the {@link BoneRenderer} that your project may have
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
     * runs all {@link BoneRenderer} attached to a model in order of insertion
     */
    public void render(){

    }

}
