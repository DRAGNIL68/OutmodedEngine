package net.outmoded.outmodedEngine.live;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

// central store of all packet displays
// that are operated by the OutmodedEngine
public class NodeRegister {
    private final ConcurrentHashMap<UUID, CopyOnWriteArrayList<Node>> register = new ConcurrentHashMap<>();

    public CopyOnWriteArrayList<Node> getNodes(UUID uuid){
        CopyOnWriteArrayList<Node> original = register.get(uuid);
        if (original == null)
            return new CopyOnWriteArrayList<>();

        return new CopyOnWriteArrayList<>(original);
    }

    public void remove(UUID uuid) {
        register.remove(uuid);
    }

    public void setNodes(UUID uuid, CopyOnWriteArrayList<Node> nodes){
        register.put(uuid, new CopyOnWriteArrayList<>(nodes));
    }

}
