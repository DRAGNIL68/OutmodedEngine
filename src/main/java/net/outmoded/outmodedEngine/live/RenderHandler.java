package net.outmoded.outmodedEngine.live;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.outmoded.outmodedEngine.default_formats.blockbench.NodeTemplate;
import net.outmoded.outmodedEngine.template.ModelTemplate;
import net.outmoded.outmodedEngine.template.ModelTemplateManager;

import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * handles all the {@link BoneRenderer} that your project may have
 */
public final class RenderHandler {

    private final ConcurrentLinkedDeque<BoneRenderer> boneRenderers = new ConcurrentLinkedDeque<>();

    /**
     * first BoneRenderer in is executed last.
     * I recommend you use that one to send the display packet
     */
    public void addBoneRenderer(BoneRenderer boneRenderer) {
        boneRenderers.add(boneRenderer);
    }

    public void clearBoneRenderers() {
        boneRenderers.clear();
    }

    public ImmutableList<BoneRenderer> getBoneRenderersList() {
        return ImmutableList.copyOf(boneRenderers);
    }

    public ImmutableList<BoneRenderer> getBoneRenderersSnapshot() {
        return ImmutableList.copyOf(boneRenderers);
    }

    /**
     * runs all {@link BoneRenderer} attached to a model in order of insertion
     */
    public synchronized void render(Model model){

        if (model == null)
            return;

        if (!model.isActive())
            return;

        ModelTemplate modelTemplate = ModelTemplateManager.getInstance().getModelTemplate(model.getTemplateKey());

        if (modelTemplate == null)
            return;

        ImmutableMap<UUID, Node> nodes = model.getNodes();
        ImmutableMap<UUID, NodeTemplate> nodeTemplates = modelTemplate.getNodesMap();

        nodeTemplates.forEach( (uuid, nodeTemplate) -> {

            Node node = nodes.get(uuid); // can be null

            for (BoneRenderer renderer : boneRenderers){
                renderer.process(nodeTemplate, node, model);
            }
        });
    }
}
