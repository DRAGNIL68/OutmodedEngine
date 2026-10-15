package net.outmoded.outmodedEngine.live;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.outmoded.outmodedEngine.default_formats.blockbench.NodeTemplate;
import net.outmoded.outmodedEngine.template.ModelTemplate;
import net.outmoded.outmodedEngine.template.ModelTemplateManager;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * handles all the {@link BoneRenderer} that your project may have
 */
public final class RenderHandler {

    private final ConcurrentHashMap<String, BoneRenderer> boneRenderers = new ConcurrentHashMap<>();

    public void registerBoneRenderer(String id, BoneRenderer boneRenderer) {
        boneRenderers.put(id, boneRenderer);
    }

    public void unregisterBoneRenderer(String id) {
        boneRenderers.remove(id);
    }

    public void clearBoneRenderers() {
        boneRenderers.clear();
    }

    public ImmutableList<BoneRenderer> getBoneRenderersList() {
        return ImmutableList.copyOf(boneRenderers.values());
    }

    public ImmutableMap<String, BoneRenderer> getBoneRenderers() {
        return ImmutableMap.copyOf(boneRenderers);
    }

    /**
     * runs all {@link BoneRenderer} attached to a model in order of insertion
     */
    public void render(Model model){

        if (model == null && !model.isActive())
            return;

        ModelTemplate modelTemplate = ModelTemplateManager.getInstance().getModelTemplate(model.getTemplateKey());

        if (modelTemplate == null)
            return;

        ImmutableMap<UUID, Node> nodes = model.getNodes();
        ImmutableMap<UUID, NodeTemplate> nodeTemplates = modelTemplate.getNodesMap();

        nodeTemplates.forEach(  (uuid, nodeTemplate) -> {

            Node node = nodes.get(uuid); // can be null


            for (BoneRenderer renderer : boneRenderers.values()){
                renderer.process(nodeTemplate, node, model);
            }

        });
        //Nodes...

    }

}
