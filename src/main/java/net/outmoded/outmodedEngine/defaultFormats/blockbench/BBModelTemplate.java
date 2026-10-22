package net.outmoded.outmodedEngine.defaultFormats.blockbench;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.ImmutableMap;
import net.outmoded.outmodedEngine.Co;
import net.outmoded.outmodedEngine.template.*;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.jspecify.annotations.NonNull;

import java.io.IOException;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;



// ALL data in this class should be immutable.
public class BBModelTemplate implements net.outmoded.outmodedEngine.template.ModelTemplate {
    private final boolean isPersistent;

    private final ConcurrentHashMap<UUID, NodeTemplate> nodesMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, VariantTemplate> variantsMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, AnimationTemplate> animationsMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, String> texturesMap = new ConcurrentHashMap<>(); //name:base64

    public BBModelTemplate(String json){
        this.isPersistent = true;

        //TODO: this is were the JSON should be converted into data.
        // this is done INTERNALLY so hou cant fuck up anything async
        // and yes I do have APS (async paranoia syndrome)


        // check JSON schema here <--
        buildTemplate(json);
    }

    /**
     * Is an immutable view
     */
    public @NonNull ImmutableMap<String, VariantTemplate> getVariantsMap(){
        return ImmutableMap.copyOf(variantsMap);
    }

    /**
     * Is an immutable view
     */
    public @NonNull ImmutableMap<String, AnimationTemplate> getAnimationsMap(){
        return ImmutableMap.copyOf(animationsMap);
    }

    /**
     * Is an immutable view
     */
    public @NonNull ImmutableMap<UUID, NodeTemplate> getNodesMap(){
        return ImmutableMap.copyOf(nodesMap);
    }

    public boolean isPersistent() {
        return isPersistent;
    }


    // called once to create the template
    private void buildTemplate(String json){
        JsonNode jsonNode;
        try {
            jsonNode = TemplateUtils.readTree(json);
        } catch (IOException e) {
            throw new RuntimeException("template building error: " + e);
        }


        {
            // model settings
            JsonNode settings = jsonNode.get("settings");

            String namespacedId = settings.get("namespacedId").asText();
            String credit = settings.get("credit").asText();

            JsonNode userProperties = settings.get("user_properties"); // <- important
            Co.info("template data");
            Co.warn("settings:");
            Co.info("namespacedId: "+namespacedId);
            Co.info("credit: "+credit);

        }

        {
            Co.warn("textures:");
            JsonNode textures = jsonNode.get("textures");

            Iterator<String> iterator = textures.fieldNames();
            iterator.forEachRemaining(name -> {
                Co.info("texture name: "+name);
                texturesMap.put(name, textures.asText());
            });
        }

        {
            // default model structure
            JsonNode structure = jsonNode.get("structure");
            Co.warn("bones");
            for (JsonNode node : structure) { // uuid


                String type = node.get("type").asText();
                String name = node.get("name").asText();
                UUID uuid = UUID.fromString(node.get("uuid").asText());
                Co.info("bone uuid: "+uuid);
                Co.info("bone type: "+type);
                Co.info("bone name: "+name);

                JsonNode transform = node.get("transform");


                TypeReference<Float[]> jacksonTypeReference = new TypeReference<>() {};

                try {
                    // editor has some data problems rn
                    Float[] pos = TemplateUtils.mapper().treeToValue(transform.get("position"), jacksonTypeReference); // does not need to be changed in any way
                    Float[] leftList = TemplateUtils.mapper().treeToValue(transform.get("left_rotation"), jacksonTypeReference); // left rotation
                    Float[] translationList = TemplateUtils.mapper().treeToValue(transform.get("translation"), jacksonTypeReference);
                    Float[] scaleList = TemplateUtils.mapper().treeToValue(transform.get("scale"), jacksonTypeReference);
                    Quaternionf leftRot = new Quaternionf(leftList[0], leftList[1], leftList[2], leftList[3]);

                    Vector3f translation = new Vector3f(); // cant do this yet

                    Vector3f scale = new Vector3f(scaleList[0], scaleList[1], scaleList[2]);

                    NodeTemplate nodeTemplate = new NodeTemplate(
                            name,
                            type,
                            translation,
                            leftRot,
                            scale,
                            pos
                    );

                    nodesMap.put(UUID.fromString(node.get("uuid").asText()), nodeTemplate);

                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        }


        {
            // animation data
            JsonNode animations = jsonNode.get("animations");
        }

        {
            // variants and model data abd textures
            JsonNode texture_variants = jsonNode.get("texture_variants");
        }

    }
}
