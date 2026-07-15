package net.outmoded.outmodedEngine.pack.jsonObjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Model extends Writable {
    private final Map<String, Object> model = new HashMap<>();

    public Model(String modelNamespaced){
        super(modelNamespaced.substring(modelNamespaced.lastIndexOf('/') + 1) + ".json");
        model.put("type", "minecraft:model");
        model.put("model", modelNamespaced);
        ArrayList<Tint> tints = new ArrayList<>();
        tints.add(new Tint());
        model.put("tints", tints);

    }



}
