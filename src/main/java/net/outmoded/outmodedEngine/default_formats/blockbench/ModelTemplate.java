package net.outmoded.outmodedEngine.default_formats.blockbench;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.ImmutableMap;
import net.outmoded.outmodedEngine.template.*;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.jspecify.annotations.NonNull;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;



// ALL data in this class should be immutable.
public class ModelTemplate implements net.outmoded.outmodedEngine.template.ModelTemplate {
    private final boolean isPersistent;

    private final ConcurrentHashMap<UUID, NodeTemplate> nodes = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, VariantTemplate> variants = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, AnimationTemplate> animations = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, String> textures = new ConcurrentHashMap<>(); //name:base64

    public ModelTemplate(boolean isPersistent, String json){
        this.isPersistent = isPersistent;

        //TODO: this is were the JSON should be converted into data.
        // this is done INTERNALLY so hou cant fuck up anything async
        // and yes I do have APS (async paranoia syndrome)


        // check JSON schema here <--
        buildTemplate(json);
    }

    /**
     * Is an immutable view
     */
    public @NonNull ImmutableMap<String, VariantTemplate> getVariants(){
        return ImmutableMap.copyOf(variants);
    }

    /**
     * Is an immutable view
     */
    public @NonNull ImmutableMap<String, AnimationTemplate> getAnimations(){
        return ImmutableMap.copyOf(animations);
    }

    /**
     * Is an immutable view
     */
    public @NonNull ImmutableMap<UUID, NodeTemplate> getNodes(){
        return ImmutableMap.copyOf(nodes);
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

            JsonNode namespacedId = settings.get("namespacedId");
            JsonNode credit = settings.get("credit");

            JsonNode userProperties = settings.get("user_properties"); // <- important
        }

        {
            // animation data
            JsonNode animations = jsonNode.get("animations");
            for (JsonNode node : animations) {


            }

        }

        {
            // default model structure
            JsonNode structure = jsonNode.get("structure");

            for (JsonNode node : structure) { // uuid

                String type = node.get("type").asText();
                String name = node.get("name").asText();

                JsonNode transform = node.get("transform");


                TypeReference<Float[]> jacksonTypeReference = new TypeReference<>() {};

                try {
                    // editor has some data problems rn
                    Float[] pos = TemplateUtils.mapper().treeToValue(node.get("name"), jacksonTypeReference); // does not need to be changed in any way
                    Float[] leftList = TemplateUtils.mapper().treeToValue(node.get("name"), jacksonTypeReference); // left rotation
                    Float[] translationList = TemplateUtils.mapper().treeToValue(node.get("name"), jacksonTypeReference);
                    Float[] scaleList = TemplateUtils.mapper().treeToValue(node.get("name"), jacksonTypeReference);

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

                    nodes.put(UUID.fromString(node.get("uuid").asText()), nodeTemplate);

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
