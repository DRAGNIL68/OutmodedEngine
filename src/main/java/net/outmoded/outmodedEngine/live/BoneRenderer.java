package net.outmoded.outmodedEngine.live;

import net.outmoded.outmodedEngine.default_formats.blockbench.NodeTemplate;

/**
 * handles how a bone in a model appears to the world
 * this is run each tick.
 * This is greatly inspired by how GLSL shaders work.
 */
public interface BoneRenderer {

    public void process(NodeTemplate nodeTemplate, Node node, Model model);
}
