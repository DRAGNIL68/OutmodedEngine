package net.outmoded.outmodedEngine.templates;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class AnimationTemplate {
    // [1][0] would be frame 1 node 0
    private final UUID uuid;
    private final String name;
    private final List<List<NodeTemplate>> frames;

    /**
     *
     * @param uuid uuid of animation
     * @param name name of animation
     * @param frameData frame data
     */
    public AnimationTemplate(UUID uuid, String name, List<List<NodeTemplate>> frameData) {
        if (uuid == null || name == null || frameData == null)
            throw new NullPointerException("AnimationTemplate is corrupted");

        this.uuid = uuid;
        this.name = name;

        List<List<NodeTemplate>> data = new ArrayList<>();

        for (List<NodeTemplate> nodeTemplates : frameData){
            data.add(ImmutableList.copyOf(nodeTemplates));
        }
        this.frames = ImmutableList.copyOf(data);
    }

    public UUID getUuid(){ return uuid; }
    public String getName(){ return name; }

    /**
     * the returned list is immutable
     */
    public List<NodeTemplate> getFrame(int index){
        return frames.get(index);
    }

    public int frameCount(){
        return frames.size();
    }

}
