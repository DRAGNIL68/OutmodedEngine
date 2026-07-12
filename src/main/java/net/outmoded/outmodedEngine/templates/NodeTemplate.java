package net.outmoded.outmodedEngine.templates;

import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.Arrays;

public class NodeTemplate{
    private final String name;
    private final String nodeType;

    private final Vector3f translation;
    private final Quaternionf leftRotation;
    private final Vector3f scale;
    private final Float[] position;

    private List<>

    public NodeTemplate(String name, String nodeType, Vector3f translation, Quaternionf leftRotation, Vector3f scale, Float[] position){
        this.name = name;
        this.nodeType = nodeType;
        this.translation = translation;
        this.leftRotation = leftRotation;
        this.scale = scale;
        this.position = position;
    }

    public NodeTemplate(NodeTemplate nodeTemplate){
        this.name = nodeTemplate.name;
        this.nodeType = nodeTemplate.nodeType;
        this.translation = nodeTemplate.getTranslation();
        this.leftRotation = nodeTemplate.getLeftRotation();
        this.scale = nodeTemplate.getScale();
        this.position = nodeTemplate.getPosition();

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
