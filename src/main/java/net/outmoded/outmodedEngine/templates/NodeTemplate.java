package net.outmoded.outmodedEngine.templates;

import com.github.retrooper.packetevents.protocol.item.ItemStack;
import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.Hash;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class NodeTemplate{

    private final String name;
    private final String nodeType;

    private final Vector3f translation;
    private final Quaternionf leftRotation;
    private final Vector3f scale;
    private final Float[] position;

    public NodeTemplate(String name, String nodeType, Vector3f translation, Quaternionf leftRotation, Vector3f scale, Float[] position){
        this.name = name;
        this.nodeType = nodeType;
        this.translation = translation;
        this.leftRotation = leftRotation;
        this.scale = scale;
        this.position = position;
    }


    public String getName() {return name;}

    public String getNodeType() {return nodeType;}

    public Vector3f getTranslation(){
        return new Vector3f(translation);
    }

    public Quaternionf getLeftRotation(){
        return new Quaternionf(leftRotation);
    }

    public Vector3f getScale(){
        return new Vector3f(scale);
    }

    public Float[] getPosition() {return Arrays.copyOf(position, position.length);}
}
