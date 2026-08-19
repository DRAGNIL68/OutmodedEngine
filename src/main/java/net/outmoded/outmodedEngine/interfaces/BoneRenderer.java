package net.outmoded.outmodedEngine.interfaces;

/**
 * handles how a bone in a model appears to the world
 * this is run each tick.
 * This is greatly inspired by how GLSL shaders work.
 */
public interface BoneRenderer {
    public void process();
}
